package com.eduprimehub.alpha.configs;

import com.eduprimehub.alpha.properties.AlphaProperties;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * DataSource configuration class
 */
@Configuration
@Slf4j
@EnableJpaRepositories(
        basePackages = {"com.eduprimehub.alpha.repositories"}
)
@EntityScan("com.eduprimehub.alpha.models.entities")
@EnableTransactionManagement
@EnableJpaAuditing
public class DataSourceConfiguration {

    /**
     * project's properties Class' object contains all the properties from the application.yml
     */
    @Autowired
    private AlphaProperties alphaProperties;


    /**
     * Return a custom configured {@link HikariDataSource} instance
     * create Primary DataSource using the database name
     *
     * @return {@link HikariDataSource} implementation
     */
    @Bean
    @ConfigurationProperties("alpha.data-source")
    @Primary
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();

        final String PRIMARY_DATABASE_NAME = alphaProperties.getPrimaryDatabaseName();

        if (StringUtils.isEmpty(PRIMARY_DATABASE_NAME)) {
            throw new IllegalStateException("databaseName cannot be empty/null");
        }

        log.info("initializing custom primary data source with database name {}", PRIMARY_DATABASE_NAME);
        dataSource.addDataSourceProperty("databaseName", PRIMARY_DATABASE_NAME);
        return dataSource;
    }
}
