package com.practice.springbootmds.config;

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
        entityManagerFactoryRef = "postgresEntityManager",
        basePackages = "com.practice.springbootmds.repository.postgreSQL",
        transactionManagerRef = "postgresQLTransactionManager"
)
@EnableTransactionManagement
public class postgresJPAConfig {
    @Bean
    LocalContainerEntityManagerFactoryBean postgresEntityManager(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("postgresDatasource") DataSource dataSource) {

        return entityManagerFactoryBuilder.dataSource(dataSource)
                .packages("com.practice.springbootmds.entities.postgreSQL")
                .build();
    }


    @Bean
    PlatformTransactionManager postgresQLTransactionManager(
            @Qualifier("postgresEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean){
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }
}
