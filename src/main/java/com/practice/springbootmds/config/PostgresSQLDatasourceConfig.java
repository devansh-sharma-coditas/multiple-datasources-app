package com.practice.springbootmds.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class PostgresSQLDatasourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.pg")
    public DataSourceProperties postgresSQLConfig(){
        return new DataSourceProperties();
    }

    @Bean(name = "postgresDatasource")
    public DataSource postgresSQLDatasource(){
        // second method 
        return postgresSQLConfig().initializeDataSourceBuilder()
                .build();
    }
}
