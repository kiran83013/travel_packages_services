package com.travel.travtronics.config;

import java.util.HashMap;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.travel.travtronics.packages", entityManagerFactoryRef = "packagesEntityManager", transactionManagerRef = "packagesTransactionManager")
public class PersitencePackagesConfiguration {

	@Autowired
	private Environment env;

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean packagesEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(packagesDataSource());
		em.setPackagesToScan("com.travel.travtronics.packages");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Primary
	@Bean
	public DataSource packagesDataSource() {

		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig
				.setDriverClassName(Objects.requireNonNull(env.getProperty("packages.datasource.driver-class-name")));
		hikariConfig.setJdbcUrl(env.getProperty("packages.datasource.url"));
		hikariConfig.setUsername(env.getProperty("packages.datasource.username"));
		hikariConfig.setPassword(env.getProperty("packages.datasource.password"));
		// Set HikariCP properties
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setMinimumIdle(5);
		hikariConfig.setIdleTimeout(60000);
		hikariConfig.setConnectionTimeout(20000);
		hikariConfig.setMaxLifetime(1800000);
		hikariConfig.setLeakDetectionThreshold(30000);

		return new HikariDataSource(hikariConfig);

	}

	@Primary
	@Bean
	public PlatformTransactionManager packagesTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(packagesEntityManager().getObject());
		return transactionManager;
	}

}
