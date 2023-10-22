package ru.neoflex.account.config;

import jakarta.persistence.EntityManagerFactory;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "clientEntityManagerFactory",
        transactionManagerRef = "clientTransactionManager",
        basePackages = {"ru.neoflex.account.repositories.client"})
public class ClientDatasourceConfig {
    @Bean(name = "clientProperties")
    @ConfigurationProperties("spring.datasource.client")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "clientDatasource")
    public DataSource datasource(@Qualifier("clientProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();    }

    @Bean(name = "clientLiquibaseProperties")
    @ConfigurationProperties(prefix = "spring.liquibase.client")
    public LiquibaseProperties liquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean(name = "clientLiquibase")
    public SpringLiquibase liquibase() {
        return springLiquibase(datasource(dataSourceProperties()), liquibaseProperties());
    }

    @Primary
    @Bean(name = "clientEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
             @Qualifier("clientDatasource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("ru.neoflex.account.models.client")
                .persistenceUnit("client").build();
    }

    @Primary
    @Bean(name = "clientTransactionManager")
    @ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager transactionManager(@Qualifier("clientEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private static SpringLiquibase springLiquibase(@Qualifier("clientDatasource") DataSource dataSource,
                                                   @Qualifier("clientLiquibaseProperties") LiquibaseProperties properties) {
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
