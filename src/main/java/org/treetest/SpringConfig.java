package org.treetest;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.treetest.service.TreeService;
import org.treetest.service.TreeServiceImpl;

@SuppressWarnings("restriction")
@Configuration
//scheduler:
@EnableAsync
@EnableScheduling
//spring-data:
@EnableJpaRepositories
@EnableTransactionManagement
@PropertySource("classpath:config.properties")
public class SpringConfig {

	   private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	   private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	   private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	   private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
	   private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	   private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

	   private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	   
	@Resource
	   private Environment env;


	  @Bean
	  public DataSource dataSource() {	
/*		  EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		  return builder.setType(EmbeddedDatabaseType.HSQL).build();
*/		  
		  DriverManagerDataSource dataSource = new DriverManagerDataSource();
		  dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		  dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		  dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		  dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
/*		  dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		  dataSource.setUrl("jdbc:mysql://10.23.200.80:3306/fastlane_stats");
		  dataSource.setUsername("fastlane");
		  dataSource.setPassword("f@stl@ne1!");
*/		  return dataSource;

	  }
	
	  @Bean
	  public EntityManagerFactory entityManagerFactory() {	
		  HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		  vendorAdapter.setGenerateDdl(true);
	
		  LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		  factory.setJpaVendorAdapter(vendorAdapter);
		  factory.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
//		  factory.setPackagesToScan("org.fastlane.stats.model");
		  factory.setDataSource(dataSource());
		  factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		  factory.setJpaProperties(hibernateProperties());
		  factory.afterPropertiesSet();
	
	    return factory.getObject();
	  }
	
	  private Properties hibernateProperties() {
	    	Properties properties = new Properties();
	    	properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
	    	properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
//	    	properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
//	    	properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, "true");
	    	return properties;
	  }

	  
	  @Bean
	  public PlatformTransactionManager transactionManager() {	
		  JpaTransactionManager txManager = new JpaTransactionManager();
		  txManager.setEntityManagerFactory(entityManagerFactory());
		  return txManager;
	  }

	  @Bean 
	  public HibernateExceptionTranslator hibernateExceptionTranslator(){ 
		  return new HibernateExceptionTranslator(); 
	  }
	  
	  @Bean
	  public TreeService treeService(){
		  return new TreeServiceImpl();
	  }

}
