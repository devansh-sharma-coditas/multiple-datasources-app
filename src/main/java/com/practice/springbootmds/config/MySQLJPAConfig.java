package com.practice.springbootmds.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
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
@EnableJpaRepositories(
        entityManagerFactoryRef = "mySQLEntityManager",
        basePackages = "com.practice.springbootmds.repository.mySQL",
        transactionManagerRef = "mySQLTransactionManager"
)
@EnableTransactionManagement
public class MySQLJPAConfig {
    @Bean
    LocalContainerEntityManagerFactoryBean mySQLEntityManager(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("mySQLDatasource") DataSource dataSource) {

        return entityManagerFactoryBuilder.dataSource(dataSource)
                .packages("com.practice.springbootmds.entities.mySQL")
                .build();
    }

    @Bean
    PlatformTransactionManager mySQLTransactionManager(
            @Qualifier("mySQLEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean){
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }
}
