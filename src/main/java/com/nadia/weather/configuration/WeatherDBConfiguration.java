package com.nadia.weather.configuration;

import com.nadia.weather.repository.weather.WeatherProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(basePackageClasses = WeatherProviderRepository.class,
        entityManagerFactoryRef = "weatherDSFactory", transactionManagerRef = "weatherDSTransactionManager")
public class WeatherDBConfiguration {
    @Autowired
    private Environment env;

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties weatherDSProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource weatherDS() {
        return weatherDSProperties().initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean weatherDSFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(weatherDS());
        factory.setPackagesToScan(new String[]{
                "com.nadia.weather.entity.weather"
        });
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        factory.setJpaProperties(jpaProperties);

        return factory;

    }

    @Primary
    @Bean
    public PlatformTransactionManager weatherDSTransactionManager() {
        EntityManagerFactory factory = weatherDSFactory().getObject();
        return new JpaTransactionManager(factory);
    }


}
