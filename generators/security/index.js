'use strict';
const BaseGenerator = require('../base-generator');
const constants = require('../constants');
const _ = require('lodash');

module.exports = class extends BaseGenerator {

    constructor(args, opts) {
        super(args, opts);
        this.configOptions = this.options.configOptions || {};


    }

    get initializing() {
        this.logSuccess('Generating JPA entity, repository, service and controller');
        return {
            validateEntityName() {
                const context = this.context;
                //this.env.error("The entity name is invalid");
            }
        }
    }

    /*get prompting() {
        return prompts.prompting;
    }*/

    configuring() {
        this.configOptions = Object.assign({}, this.configOptions, this.config.getAll());
        this.configOptions.basePath = this.options['base-path'];
        this.configOptions.entityName = 'Auth';
        this.configOptions.entityVarName = 'auth';
        this.configOptions.security = true;
        this.configOptions.tableName = _.lowerCase(this.configOptions.entityName)+'s';
        this.config.set('security', true);
        Object.assign(this.configOptions, constants);
       
    }

    writing() {
        this._generateAppCode(this.configOptions);
        this._generateDbMigrationConfig(this.configOptions)
        this._copyMavenWrapper(this.configOptions);
        this._generateMavenPOMXml(this.configOptions);
    }

    end() {
        //TODO; Disabling this temporarily to fix test failures.
        //this._formatCode(this.configOptions);
    }

    _generateAppCode(configOptions) {
        const mainJavaTemplates = [
            {src: 'security/WebSecurityConfig.java', dest: 'security/WebSecurityConfig.java'},
            {src: 'security/services/UserDetailsImpl.java', dest: 'security/services/UserDetailsImpl.java'},
            {src: 'security/services/UserDetailsServiceImpl.java', dest: 'security/services/UserDetailsServiceImpl.java'},
            {src: 'security/jwt/AuthEntryPointJwt.java', dest: 'security/jwt/AuthEntryPointJwt.java'},
            {src: 'security/jwt/AuthTokenFilter.java', dest: 'security/jwt/AuthTokenFilter.java'},
            {src: 'security/jwt/JwtTokenUtil.java', dest: 'security/jwt/JwtTokenUtil.java'},
            {src: 'security/jwt/JwtUtils.java', dest: 'security/jwt/JwtUtils.java'},

            {src: 'inbound/controllers/JwtAuthenticationController.java', dest: 'inbound/controllers/JwtAuthenticationController.java'},
            {src: 'inbound/controllers/RegistrationController.java', dest: 'inbound/controllers/RegistrationController.java'},

            {src: 'entities/Entity.java', dest: 'entities/'+configOptions.entityName+'.java'},//ok

            {src: 'outbound/repositories/UserRepository.java', dest: 'outbound/repositories/'+configOptions.entityName+'Repository.java'},
            {src: 'outbound/RepositoryAdapter.java', dest: 'outbound/'+configOptions.entityName+'RepositoryAdapter.java'},

            {src: 'dto/Entity.java', dest: 'dto/'+configOptions.entityName+'DTO.java'},
            {src: 'dto/JwtRequest.java', dest: 'dto/JwtRequest.java'},
            {src: 'dto/JwtResponse.java', dest: 'dto/JwtResponse.java'},
            {src: 'mapper/Entity.java', dest: 'mapper/'+configOptions.entityName+'Mapper.java'},
            {src: 'mapper/Converter.java', dest: 'mapper/Converter.java'},

            {src: 'ports/out/Port.java', dest: 'ports/out/'+configOptions.entityName+'RepositoryPort.java'},
        ];
        this.generateMainJavaCode(configOptions, mainJavaTemplates);

    }

    _generateDbMigrationConfig(configOptions) {

        if(configOptions.dbMigrationTool === 'flywaydb') {
            this._generateFlywayMigration(configOptions)
        }
    }

    _generateFlywayMigration(configOptions) {
        const counter = configOptions[constants.KEY_FLYWAY_MIGRATION_COUNTER] + 1;
        let vendor = configOptions.databaseType;
        if(vendor === "mariadb") {
            vendor = "mysql";
        }

        this.fs.copyTpl(
            this.templatePath('app/src/main/resources/db/migration/flyway/V1__security.sql'),
            this.destinationPath('src/main/resources/db/migration/h2/V'+counter+'__create_security_table.sql'),
            configOptions
        );
        
        this.fs.copyTpl(
            this.templatePath('app/src/main/resources/db/migration/flyway/V1__security.sql'),
            this.destinationPath('src/main/resources/db/migration/'+vendor+
                '/V'+counter+'__create_security_table.sql'),
            configOptions
        );
       
        const flywayMigrantCounter = {
            [constants.KEY_FLYWAY_MIGRATION_COUNTER]: counter
        };
        this.config.set(flywayMigrantCounter);
    }

    _copyMavenWrapper(configOptions) {
        const commonMavenConfigDir = '../../common/files/maven/';

        ['mvnw', 'mvnw.cmd'].forEach(tmpl => {
            this.fs.copyTpl(
                this.templatePath(commonMavenConfigDir + tmpl),
                this.destinationPath(tmpl)
            );
        });

        this.fs.copyTpl(
            this.templatePath(commonMavenConfigDir + 'gitignore'),
            this.destinationPath('.gitignore')
        );

        this.fs.copy(
            this.templatePath(commonMavenConfigDir + '.mvn'),
            this.destinationPath('.mvn')
        );

    }

    _generateMavenPOMXml(configOptions) {
        this.fs.delete(this.destinationPath('pom.xml'))
        const mavenConfigDir = '../../common/files/maven/';
        this.fs.copyTpl(
            this.templatePath(mavenConfigDir + 'pom.xml'),
            this.destinationPath('pom.xml'),
            configOptions
        );
    }

};
