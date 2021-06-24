package com.training.distance.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * The Spring configuration class for additional beans.
 */
@Configuration
//@EnableTransactionManagement
public class ApplicationConfiguration {

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/message/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    /**
     * @param messageSource
     * @return
     */
    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

    /**
     * Declare the transaction manager.
     * https://github.com/netgloo/spring-boot-samples/blob/065c82a9f7702de1c751f7980672c2eb0e6c33c1/spring-boot-mysql-jpa-hibernate/src/main/java/netgloo/configs/DatabaseConfig.java#L47
     */

//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager =
//                new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(
//                entityManagerFactory.getObject());
//        return transactionManager;
//    }

    /**
     * Declare the JPA entity manager factory.
     */
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean entityManagerFactory =
//                new LocalContainerEntityManagerFactoryBean();
//
////        entityManagerFactory.setDataSource(dataSource);
//
//        // Classpath scanning of @Component, @Service, etc annotated class
////        entityManagerFactory.setPackagesToScan(
////                env.getProperty("entitymanager.packagesToScan"));
//
//        // Vendor adapter
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
//
////        // Hibernate properties
////        Properties additionalProperties = new Properties();
////        additionalProperties.put(
////                "hibernate.dialect",
////                env.getProperty("hibernate.dialect"));
////        additionalProperties.put(
////                "hibernate.show_sql",
////                env.getProperty("hibernate.show_sql"));
////        additionalProperties.put(
////                "hibernate.hbm2ddl.auto",
////                env.getProperty("hibernate.hbm2ddl.auto"));
////        entityManagerFactory.setJpaProperties(additionalProperties);
//
//        return entityManagerFactory;
//    }
    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

//    @Autowired
//    private Environment env;
//
////    @Autowired
////    private DataSource dataSource;
//
//    @Autowired
//    private LocalContainerEntityManagerFactoryBean entityManagerFactory;
//
}