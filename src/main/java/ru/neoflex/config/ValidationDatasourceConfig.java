package ru.neoflex.config;

import jakarta.persistence.EntityManagerFactory;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "validationEntityManagerFactory",
        transactionManagerRef = "validationTransactionManager",
        basePackages = {"ru.neoflex.repositories.validation"})
public class ValidationDatasourceConfig {
    @Bean(name = "validationProperties")
    @ConfigurationProperties("spring.datasource.validation")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "validationDatasource")
    public DataSource datasource(@Qualifier("validationProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "validationLiquibaseProperties")
    @ConfigurationProperties(prefix = "spring.liquibase.validation")
    public LiquibaseProperties liquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean(name = "validationLiquibase")
    public SpringLiquibase liquibase() {
        return springLiquibase(datasource(dataSourceProperties()), liquibaseProperties());
    }

    @Bean(name = "validationEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder,
             @Qualifier("validationDatasource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("ru.neoflex.models.validation")
                .persistenceUnit("validation").build();
    }

    @Bean(name = "validationTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("validationEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private static SpringLiquibase springLiquibase(@Qualifier("validationDatasource") DataSource dataSource,
                                                   @Qualifier("validationLiquibaseProperties") LiquibaseProperties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setContexts(properties.getContexts());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setChangeLogParameters(properties.getParameters());
        liquibase.setRollbackFile(properties.getRollbackFile());
        return liquibase;
    }
}
