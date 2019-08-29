/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.usermenagmentsystem.web.config;

import com.marina.apacheshirosecurity.config.ApacheShiroConfig;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author MARINA
 */
public class АppliactionInitializer implements WebApplicationInitializer {

    private String TMP_FOLDER = "/tmp";
    private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ApplicationConfig.class);
        rootContext.register(ApacheShiroConfig.class);
        sc.addListener(new ContextLoaderListener(rootContext));
        
        //Adding Shiro
        FilterRegistration.Dynamic shiroFilter = sc.addFilter("shiroFilterFactoryBean", DelegatingFilterProxy.class);
        shiroFilter.setInitParameter("targetFilterLifecycle", "true");
        shiroFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
        //
        
        AnnotationConfigWebApplicationContext dispatcherWebContext = new AnnotationConfigWebApplicationContext();
        dispatcherWebContext.register(WebConfig.class);
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER,
                MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);

        ServletRegistration.Dynamic dispatcher = sc.addServlet("mvc", new DispatcherServlet(dispatcherWebContext));
        dispatcher.setMultipartConfig(multipartConfigElement);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }

}
