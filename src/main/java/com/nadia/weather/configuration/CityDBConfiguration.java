package com.nadia.weather.configuration;


import com.nadia.weather.repository.city.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = CityRepository.class,
        entityManagerFactoryRef = "cityDSFactory", transactionManagerRef = "cityDSTransactionManager")
public class CityDBConfiguration {
    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix="spring.datasource.db2")
    public DataSourceProperties cityDSProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource cityDS( ){
        return cityDSProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean cityDSFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(cityDS());
        factory.setPackagesToScan(new String[] {
                "com.nadia.weather.entity.city"
        });
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        factory.setJpaProperties(jpaProperties);
        return factory;

    }


    @Bean
    public PlatformTransactionManager cityDSTransactionManager() {
        EntityManagerFactory factory =cityDSFactory().getObject();
        return new JpaTransactionManager(factory);
    }


}
