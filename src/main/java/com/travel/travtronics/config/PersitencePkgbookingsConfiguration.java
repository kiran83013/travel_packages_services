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
@EnableJpaRepositories(basePackages = "com.travel.travtronics.pkgbookings", entityManagerFactoryRef = "pkgbookingsEntityManager", transactionManagerRef = "pkgbookingsTransactionManager")
public class PersitencePkgbookingsConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean pkgbookingsEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(pkgbookingsDataSource());
		em.setPackagesToScan("com.travel.travtronics.pkgbookings");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Bean
	public DataSource pkgbookingsDataSource() {

		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig
				.setDriverClassName(Objects.requireNonNull(env.getProperty("pkgbookings.datasource.driver-class-name")));
		hikariConfig.setJdbcUrl(env.getProperty("pkgbookings.datasource.url"));
		hikariConfig.setUsername(env.getProperty("pkgbookings.datasource.username"));
		hikariConfig.setPassword(env.getProperty("pkgbookings.datasource.password"));
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
	public PlatformTransactionManager pkgbookingsTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(pkgbookingsEntityManager().getObject());
		return transactionManager;
	}

}
