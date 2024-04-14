package com.travel.travtronics.config;

import java.util.HashMap;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.travel.travtronics.master", entityManagerFactoryRef = "masterEntityManager", transactionManagerRef = "masterTransactionManager")
public class PersistenceMasterConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean masterEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(masterDataSource());
		em.setPackagesToScan("com.travel.travtronics.master");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Bean
	public DataSource masterDataSource() {

		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(Objects.requireNonNull(env.getProperty("master.datasource.driver-class-name")));
		hikariConfig.setJdbcUrl(env.getProperty("master.datasource.url"));
		hikariConfig.setUsername(env.getProperty("master.datasource.username"));
		hikariConfig.setPassword(env.getProperty("master.datasource.password"));
		// Set HikariCP properties
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setMinimumIdle(5);
		hikariConfig.setIdleTimeout(60000);
		hikariConfig.setConnectionTimeout(20000);
		hikariConfig.setMaxLifetime(1800000);
		hikariConfig.setLeakDetectionThreshold(30000);

		return new HikariDataSource(hikariConfig);

	}

	@Bean
	public PlatformTransactionManager masterTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(masterEntityManager().getObject());
		return transactionManager;
	}
}
