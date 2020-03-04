package old.geot.config;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.ruyo.rest.geot.entity.*;
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
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        entityManagerFactoryRef = "EntityManagerFactoryBean",
        transactionManagerRef = "hibernateTransactionManager",
        basePackages = "com.ruyo.rest.geot.dao",
        basePackageClasses = {
                Chofer.class,
                Cliente.class,
                Empresa.class,
        }
)

@EntityScan(
        basePackages = "com.ruyo.rest.geot.entity",
        basePackageClasses = {
                Chofer.class,
                Cliente.class,
                Empresa.class,
        }
)

@EnableTransactionManagement
public class AppConfigDB extends HikariConfig {

    private final HikariDataSource hikariDataSource;

    @Autowired
    public AppConfigDB(HikariDataSource hikariDataSource) {
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
    @Primary
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(hikariDataSource);
        sessionFactory.setPackagesToScan("com.ruyo.rest.geot.entity");
        sessionFactory.setAnnotatedClasses(Cliente.class);
        sessionFactory.setHibernateProperties(hikariDataSource.getDataSourceProperties());
        return sessionFactory;
    }


    @Bean(value = "hibernateTransactionManager")
    public HibernateTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory().getObject());
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public HibernateTemplate hibernateTemplate() {
        return new HibernateTemplate(sessionFactory().getObject());
    }

    @Bean(value = "EntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean EntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(hikariDataSource);
        em.setPackagesToScan("com.ruyo.rest.geot.entity");
        em.setPersistenceUnitName("geot Persistence Unit");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        vendorAdapter.setPrepareConnection(true);
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }

    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> filterRegistrationBean() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        filter.setForceRequestEncoding(true);
        filter.setForceResponseEncoding(true);
        FilterRegistrationBean<CharacterEncodingFilter> registrationBean = new FilterRegistrationBean<CharacterEncodingFilter>();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public HttpPutFormContentFilter httpPutFormContentFilter() {
        HttpPutFormContentFilter filter = new HttpPutFormContentFilter();
        filter.setCharset(Charset.forName("UTF-8"));
        return filter;
    }

    @Bean
    public FilterRegistrationBean<RequestContextFilter> requestContextFilter() {
        final FilterRegistrationBean<RequestContextFilter> filterRegBean = new FilterRegistrationBean<RequestContextFilter>();
        filterRegBean.setFilter(new RequestContextFilter());
        filterRegBean.addUrlPatterns("*");
        filterRegBean.setEnabled(true);
        filterRegBean.setName("RequestContextFilter");
        return filterRegBean;
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

    @Bean
    public Module parameterNamesModule() {
        return new ParameterNamesModule(JsonCreator.Mode.PROPERTIES);
    }

}


