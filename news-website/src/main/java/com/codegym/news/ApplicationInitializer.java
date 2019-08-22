package com.codegym.news;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    /*protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class};
    }*/
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebSecurityConfig.class};
    }

    @Override
    /*protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }*/
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {ApplicationConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}