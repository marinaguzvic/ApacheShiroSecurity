/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.apacheshirosecurity.database.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author MARINA
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.marina.apacheshirosecurity.database",
        entityManagerFactoryRef = "securityEntityManagerFactory",
        transactionManagerRef = "securityTransactionManager"
        )
//Configure the base packages that are scanned when Spring Data JPA creates implementations for our repository interfaces.
@PropertySources({
    @PropertySource("classpath:repository_config.properties")
})
//Enabling Annotation-Driven Transaction Management
@EnableTransactionManagement
//@ComponentScans(value = {
//    @ComponentScan("com.marina.usermenagmentsystem.database"),
//    @ComponentScan("com.marina.usermenagmentsystem.security.service")})
public class PersistanceConfig {
    
    @Autowired
    Environment env;
    
    
    //First we create LocalContatinerEntityManager factory bean because it creates EntityManagerFactory
    //Then we configure datasource and hibernate specific implementation of the JpaVendorAdapter interface
    //We also configure the packages that are scanned for entity classes here
    
    @Bean(name = "securityEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean securityEntityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(securityDataSource());
        em.setPackagesToScan(new String[]{"com.marina.apacheshirosecurity.database.model"});
        
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }
    
    //Here we set the properties for a MySql database
    @Bean
    public DataSource securityDataSource(){
         BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("database.driver"));
        dataSource.setUrl(env.getProperty("security.database.url"));
        dataSource.setUsername(env.getProperty("database.user"));
        dataSource.setPassword(env.getProperty("database.password"));
        return dataSource;
    }

    //Now we need to configure transaction manager bean. He takes entity manager factory as an argument
    @Bean("securityTransactionManager")
    public PlatformTransactionManager securityTransactionManager(EntityManagerFactory emf){
        
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(securityEntityManagerFactory().getObject());
        return transactionManager;
    }
//    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "validate");
        return properties;
    }
}
