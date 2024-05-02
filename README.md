# multiple-datasources-app
This is springboot application which communicate two different datasources

* here i am using mysql and postgresQL database as two datasources
* main points to noitce are
  1. @ConfigurationProperties
  2. @EnableJpaProperties
     eg.
     @EnableJpaRepositories(
        entityManagerFactoryRef = "mySQLEntityManager",
        basePackages = "com.practice.springbootmds.repository.mySQL",
        transactionManagerRef = "mySQLTransactionManager"
      )
  3. bean of DataSourcesProperties , DataSources class
     e.g.
      @Bean
      @ConfigurationProperties("spring.datasource.mysql")
      public DataSourceProperties mySQLConfig(){
          return new DataSourceProperties();
      }
     this spring.datasource.mysql should match with your application.properties file
  
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
      
  5. Custom EntityManager bean -> LocalContainerEntityManagerFactoryBean
  6. Need to define entities in the separate package according to datasource because later we need to define entity packages to datasource.
  7. Need to define entityManager , base package for repository , transactionManager in @EnableJpaRepositories annotation
  8. Need to create bean for transaction-manager separately for each datasource. -> platformTransactionManager

    eg.
    @Bean
    PlatformTransactionManager mySQLTransactionManager(
            @Qualifier("mySQLEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean){
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }
