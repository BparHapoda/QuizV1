package com.example.quiz.intDataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import javax.naming.Context;
import java.io.IOException;
import java.util.Properties;


@WebListener
public class dataSourceInit implements ServletContextListener {
    HikariDataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce){
        ServletContext context=sce.getServletContext();
        Properties properties=new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HikariConfig config=new HikariConfig();
        config.setUsername(properties.getProperty("db.user"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.pool")));
        config.setDriverClassName(properties.getProperty("db.driver"));
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setPassword(properties.getProperty("db.password"));

        this.dataSource=new HikariDataSource(config);
context.setAttribute("dataSource",dataSource);





    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dataSource.close();
    }

}
