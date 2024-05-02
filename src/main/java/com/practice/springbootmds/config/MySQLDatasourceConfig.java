package com.practice.springbootmds.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
@Configuration
public class MySQLDatasourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSourceProperties mySQLConfig(){
        return new DataSourceProperties();
    }

    @Bean(name = "mySQLDatasource")@Primary
    public DataSource mySQLDatasource(){
        // first-method
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUsername(mySQLConfig().getUsername());
//        dataSource.setPassword(mySQLConfig().getPassword());
//        dataSource.setUrl(mySQLConfig().getUrl());
//        dataSource.setDriverClassName(mySQLConfig().getDriverClassName());

        // second method
        return mySQLConfig().initializeDataSourceBuilder()
                .build();
    }
}
