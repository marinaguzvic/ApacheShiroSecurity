/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.usermenagmentsystem.data.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
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
        basePackages = "com.marina.usermenagmentsystem.data.repository",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
//Configure the base packages that are scanned when Spring Data JPA creates implementations for our repository interfaces.
@PropertySources({
    @PropertySource("classpath:repository_config.properties")
})
//Enabling Annotation-Driven Transaction Management
@EnableTransactionManagement
public class JPAConfig {

    @Autowired
    Environment env;

    //First we create LocalContatinerEntityManager factory bean because it creates EntityManagerFactory
    //Then we configure datasource and hibernate specific implementation of the JpaVendorAdapter interface
    //We also configure the packages that are scanned for entity classes here
    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.marina.usermenagmentsystem.data.model"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    //Here we set the properties for a MySql database
    @Primary
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("database.driver"));
        dataSource.setUrl(env.getProperty("database.url"));
        dataSource.setUsername(env.getProperty("database.user"));
        dataSource.setPassword(env.getProperty("database.password"));
        return dataSource;
    }

    //Now we need to configure transaction manager bean. He takes entity manager factory as an argument
    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Primary
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
//        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty(
                "org.hibernate.envers.audit_table_suffix", "_AUDIT_LOG");
        return properties;
    }

}
