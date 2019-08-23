/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.usermenagmentsystem.web.config;

import com.marina.usermenagmentsystem.data.audit.repository.AuditRepository;
import com.marina.usermenagmentsystem.data.audit.repository.impl.DocumentAuditRepositoryImpl;
import com.marina.usermenagmentsystem.service.AuditService;
import com.marina.usermenagmentsystem.service.CompanyService;
import com.marina.usermenagmentsystem.service.DocumentFieldService;
import com.marina.usermenagmentsystem.service.DocumentService;
import com.marina.usermenagmentsystem.service.PositionService;
import com.marina.usermenagmentsystem.service.TemplateFieldService;
import com.marina.usermenagmentsystem.service.TemplateService;
import com.marina.usermenagmentsystem.service.impl.CompanyServiceImpl;
import com.marina.usermenagmentsystem.service.impl.DocumentFieldServiceImpl;
import com.marina.usermenagmentsystem.service.impl.DocumentServiceImpl;
import com.marina.usermenagmentsystem.service.impl.PositionServiceImpl;
import com.marina.usermenagmentsystem.service.impl.TemplateFieldServiceImpl;
import com.marina.usermenagmentsystem.service.impl.TemplateServiceImpl;
import com.marina.usermenagmentsystem.service.impl.PersonServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.marina.usermenagmentsystem.service.PersonService;
import com.marina.usermenagmentsystem.service.impl.AuditServiceImpl;
import java.util.Locale;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/**
 *
 * @author MARINA
 */
@Configuration
@ComponentScan(basePackages = {
    "com.marina.usermenagmentsystem.data",
    "com.marina.usermenagmentsystem.service",
    "com.marina.usermenagmentsystem.web",
    "com.marina.usermenagmentsystem.config",
    "com.marina.usermenagmentsystem.security",
    "com.marina.usermenagmentsystem.security.config",
    "com.marina.usermenagmentsystem.security.database",
    "com.marina.usermenagmentsystem.security.database.config",
    "com.marina.usermenagmentsystem.security.controller",
    "com.marina.usermenagmentsystem.security.service",
    "com.marina.usermenagmentsystem.security.listener",
    "com.marina.usermenagmentsystem.security.event",
    "com.marina.usermenagmentsystem.data.listener",
    "com.marina.usermenagmentsystem.data.audit.repository",
    "com.marina.usermenagmentsystem.data.audit.repository.impl"
})
public class ApplicationConfig {

    @Bean
    public PersonService personService() {
        return new PersonServiceImpl();
    }

    @Bean
    public CompanyService companyService() {
        return new CompanyServiceImpl();
    }

    @Bean
    public DocumentFieldService documentFieldService() {
        return new DocumentFieldServiceImpl();
    }

    @Bean
    public DocumentService documentService() {
        return new DocumentServiceImpl();
    }

    @Bean
    public PositionService positionService() {
        return new PositionServiceImpl();
    }

    @Bean
    public TemplateFieldService templateFieldService() {
        return new TemplateFieldServiceImpl();
    }

    @Bean
    public TemplateService templateService() {
        return new TemplateServiceImpl();
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/messages/validation",
                "classpath:/messages/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(0);
        return messageSource;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10000000);
        return multipartResolver;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return cookieLocaleResolver;
    }

    @Bean
    public AuditService auditService() {
        return new AuditServiceImpl();
    }
//    @Bean
//    public StandardServletMultipartResolver multipartResolver() {
//        return new StandardServletMultipartResolver();
//    }
}
