package com.currency.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration(proxyBeanMethods = false)
public class DatabaseInitialization {

    @Autowired
    void initializeDatabase(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource[] scripts = new Resource[] { resourceLoader.getResource("schema.sql")};
        new ResourceDatabasePopulator(scripts).populate(connectionFactory).block();
    }

}
