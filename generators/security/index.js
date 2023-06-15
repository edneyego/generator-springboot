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
            {src: 'application/adapters/controllers/request/JwtRequest.java', dest: 'application/adapters/controllers/request/JwtRequest.java'},
            {src: 'application/adapters/controllers/response/JwtResponse.java', dest: 'application/adapters/controllers/response/JwtResponse.java'},
            
            {src: 'application/adapters/controllers/JwtAuthenticationController.java', dest: 'application/adapters/controllers/JwtAuthenticationController.java'},
            {src: 'application/adapters/controllers/RegistrationController.java', dest: 'application/adapters/controllers/RegistrationController.java'},
            

            {src: 'domain/adapters/services/ServiceImpl.java', dest: 'domain/adapters/services/'+configOptions.entityName+'ServiceImpl.java'},
            {src: 'domain/adapters/services/UserDetailsServiceImpl.java', dest: 'domain/adapters/services/UserDetailsServiceImpl.java'},
            {src: 'domain/adapters/services/UserDetailsImpl.java', dest: 'domain/adapters/services/UserDetailsImpl.java'},
            {src: 'domain/ports/interfaces/ServicePort.java', dest: 'domain/ports/interfaces/'+configOptions.entityName+'ServicePort.java'},
            {src: 'domain/ports/repositories/RepositoryPort.java', dest: 'domain/ports/repositories/'+configOptions.entityName+'RepositoryPort.java'},
            {src: 'domain/DomainModel.java', dest: 'domain/'+configOptions.entityName+'.java'},
            
            {src: 'infrastructure/adapters/entities/Entity.java', dest: 'infrastructure/adapters/entities/'+configOptions.entityName+'Entity.java'},
            {src: 'infrastructure/adapters/filter/AuthEntryPointJwt.java', dest: 'infrastructure/adapters/filter/AuthEntryPointJwt.java'},
            {src: 'infrastructure/adapters/filter/AuthTokenFilter.java', dest: 'infrastructure/adapters/filter/AuthTokenFilter.java'},
            {src: 'infrastructure/adapters/repositories/Repository.java', dest: 'infrastructure/adapters/repositories/'+configOptions.entityName+'Repository.java'},
            {src: 'infrastructure/adapters/repositories/SpringRepository.java', dest: 'infrastructure/adapters/repositories/Spring'+configOptions.entityName+'Repository.java'},
            {src: 'infrastructure/adapters/mappers/Mapper.java', dest: 'infrastructure/adapters/mappers/'+configOptions.entityName+'Mapper.java'},
            {src: 'infrastructure/config/utils/JwtUtils.java', dest: 'infrastructure/config/utils/JwtUtils.java'},
            {src: 'infrastructure/config/SwaggerConfig.java', dest: 'infrastructure/config/SwaggerConfig.java'},
            {src: 'infrastructure/config/WebSecurityConfig.java', dest: 'infrastructure/config/WebSecurityConfig.java'},
            
            
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
