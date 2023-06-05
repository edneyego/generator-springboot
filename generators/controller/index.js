'use strict';
const BaseGenerator = require('../base-generator');
const constants = require('../constants');
const _ = require('lodash');

module.exports = class extends BaseGenerator {

    constructor(args, opts) {
        super(args, opts);
        this.configOptions = this.options.configOptions || {};

        this.argument("entityName", {
            type: String,
            required: true,
            description: "Entity name"
        });

        this.option('base-path', {
            type: String,
            desc: "Base URL path for REST Controller"
        })
    }

    async prompting() {
        this.answers = await this.prompt([
          {
            type: "confirm",
            name: "hasSecurity",
            message: "The project has security feature?"
          }
        ]);

        Object.assign(this.configOptions, this.answers);
    }

    get initializing() {
        this.logSuccess('Generating JPA entity, repository, service and controller');
        return {
            validateEntityName() {
                const context = this.context;
                console.log(`EntityName: ${this.options.entityName}, basePath: ${this.options.basePath}`);
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
        this.configOptions.entityName = this.options.entityName;
        //this.configOptions.hasSecurity = '';
        this.configOptions.entityVarName = _.camelCase(this.options.entityName);
        this.configOptions.tableName = _.snakeCase(this.options.entityName)+'s';
        this.configOptions.supportDatabaseSequences =
            this.configOptions.databaseType === 'h2'
            || this.configOptions.databaseType === 'postgresql';
        this.configOptions.formatCode = this.options.formatCode !== false
    }

    writing() {
        this._generateAppCode(this.configOptions);
        this._generateDbMigrationConfig(this.configOptions)
        this.log("Security answer", this.answers.hasSecurity);
    }

    end() {
        if(this.configOptions.formatCode !== false) {
            this._formatCode(this.configOptions);
        }
    }    

    _generateAppCode(configOptions) {
        const mainJavaTemplates = [
            {src: 'domain/entities/Entity.java', dest: 'domain/entities/'+configOptions.entityName+'.java'},
            {src: 'application/adapters/model/response/PagedResult.java', dest: 'application/adapters/model/response/PagedResult.java'},
            {src: 'domain/ports/repositories/RepositoryPort.java', dest: 'domain/ports/repositories/'+configOptions.entityName+'RepositoryPort.java'},
            {src: 'infrastructure/adapters/repositories/Repository.java', dest: 'infrastructure/adapters/repositories/'+configOptions.entityName+'Repository.java'},
            {src: 'domain/adapters/services/ServiceImpl.java', dest: 'domain/adapters/services/'+configOptions.entityName+'ServiceImpl.java'},
            {src: 'infrastructure/adapters/interface/ServicePort.java', dest: 'infrastructure/adapters/interface/'+configOptions.entityName+'ServicePort.java'},
            {src: 'application/adapters/controllers/Controller.java', dest: 'application/adapters/controllers/'+configOptions.entityName+'Controller.java'},
        ];
        this.generateMainJavaCode(configOptions, mainJavaTemplates);

        const testJavaTemplates = [
            {src: 'application/adapters/controllers/ControllerTest.java', dest: 'application/adapters/controllers/'+configOptions.entityName+'ControllerTest.java'},
            {src: 'application/adapters/controllers/ControllerIT.java', dest: 'application/adapters/controllers/'+configOptions.entityName+'ControllerIT.java'},
            {src: 'services/ServiceTest.java', dest: 'services/'+configOptions.entityName+'ServiceTest.java'},
        ];
        this.generateTestJavaCode(configOptions, testJavaTemplates);
    }

    _generateDbMigrationConfig(configOptions) {

        if(configOptions.dbMigrationTool === 'flywaydb') {
            this._generateFlywayMigration(configOptions)
        }

        if(configOptions.dbMigrationTool === 'liquibase') {
            this._generateLiquibaseMigration(configOptions);
        }
    }

    _generateFlywayMigration(configOptions) {
        const counter = configOptions[constants.KEY_FLYWAY_MIGRATION_COUNTER] + 1;
        let vendor = configOptions.databaseType;
        if(vendor === "mariadb") {
            vendor = "mysql";
        }
        const scriptTemplate = configOptions.supportDatabaseSequences ?
            "V1__new_table_with_seq.sql" : "V1__new_table_no_seq.sql";

        this.fs.copyTpl(
            this.templatePath('app/src/main/resources/db/migration/flyway/V1__new_table_with_seq.sql'),
            this.destinationPath('src/main/resources/db/migration/h2/V'+counter+'__create_'+configOptions.tableName+'_table.sql'),
            configOptions
        );
        this.fs.copyTpl(
            this.templatePath('app/src/main/resources/db/migration/flyway/'+scriptTemplate),
            this.destinationPath('src/main/resources/db/migration/'+vendor+
                '/V'+counter+'__create_'+configOptions.tableName+'_table.sql'),
            configOptions
        );
        const flywayMigrantCounter = {
            [constants.KEY_FLYWAY_MIGRATION_COUNTER]: counter
        };
        this.config.set(flywayMigrantCounter);
    }

    _generateLiquibaseMigration(configOptions) {
        const counter = configOptions[constants.KEY_LIQUIBASE_MIGRATION_COUNTER] + 1;
        const scriptTemplate = configOptions.supportDatabaseSequences ?
            "01-new_table_with_seq.xml" : "01-new_table_no_seq.xml";
        this.fs.copyTpl(
            this.templatePath('app/src/main/resources/db/migration/liquibase/changelog/'+scriptTemplate),
            this.destinationPath('src/main/resources/db/changelog/migration/0'+counter+'-create_'+configOptions.tableName+'_table.xml'),
            configOptions
        );
        const liquibaseMigrantCounter = {
            [constants.KEY_LIQUIBASE_MIGRATION_COUNTER]: counter
        };
        //const updatedConfig = Object.assign({}, this.config.getAll(), liquibaseMigrantCounter);
        this.config.set(liquibaseMigrantCounter);
    }
};
