package com.ruyo.rest.config;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.ruyo.rest.entity.*;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.filter.RequestContextFilter;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "EntityManagerFactoryBean",
        transactionManagerRef = "hibernateTransactionManager",
        basePackages = {
                "com.ruyo.rest.dao"
        },
        basePackageClasses = {
                Cliente.class,
                Empresa.class,
                Celular.class,
                Chofer.class,
                ChoferVehiculo.class,
                Coordenada.class,
                Gps.class,
                Rol.class,
                Vehiculo.class
        }
)

@EntityScan(
        basePackages = "com.ruyo.rest.entity",
        basePackageClasses = {
                Cliente.class,
                Empresa.class,
                Celular.class,
                Chofer.class,
                ChoferVehiculo.class,
                Coordenada.class,
                Gps.class,
                Rol.class,
                Vehiculo.class
        }
)

@PropertySource({"classpath:application.properties"})
@EnableTransactionManagement
public class AppConfig extends HikariConfig {

    private final HikariDataSource hikariDataSource;

    @Autowired
    public AppConfig(HikariDataSource hikariDataSource) {
        this.hikariDataSource = hikariDataSource;
    }

    @Bean
    public Connection getConnection() {

        Connection connection = null;
        try {
            connection = hikariDataSource.getConnection();
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return connection;
    }


    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(hikariDataSource);
        sessionFactory.setPackagesToScan("com.ruyo.rest.entity");
        sessionFactory.setAnnotatedClasses(Cliente.class);
        sessionFactory.setAnnotatedClasses(Empresa.class);
        sessionFactory.setAnnotatedClasses(Celular.class);
        sessionFactory.setAnnotatedClasses(Chofer.class);
        sessionFactory.setAnnotatedClasses(ChoferVehiculo.class);
        sessionFactory.setAnnotatedClasses(Coordenada.class);
        sessionFactory.setAnnotatedClasses(Gps.class);
        sessionFactory.setAnnotatedClasses(Rol.class);
        sessionFactory.setAnnotatedClasses(Vehiculo.class);
        sessionFactory.setHibernateProperties(hikariDataSource.getDataSourceProperties());
        return sessionFactory;
    }


    @Bean(value = "hibernateTransactionManager")
    public HibernateTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory().getObject());
    }

    @Bean(value = "EntityManagerFactoryBean")@Primary
    public LocalContainerEntityManagerFactoryBean EntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(hikariDataSource);
        em.setPackagesToScan("com.ruyo.rest.entity");
        em.setPersistenceUnitName("geot Persistence Unit");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        vendorAdapter.setPrepareConnection(true);
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }

//
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public HibernateTemplate hibernateTemplate() {
        return new HibernateTemplate(sessionFactory().getObject());
    }

    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> filterRegistrationBean() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        filter.setForceRequestEncoding(true);
        filter.setForceResponseEncoding(true);
        FilterRegistrationBean<CharacterEncodingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }


    @Bean
    public Module datatypeHibernateModule() {
        return new Hibernate5Module();
    }

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
        b.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return b;
    }


}


