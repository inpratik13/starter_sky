/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.core.db;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.vyantech.sky.core.db.dao" })
public class DBSpringConfig {

	@Bean
	public DataSource dataSource(DBConfig config) {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(config.getDriverClassName());
		ds.setUrl(config.getDbURL());
		ds.setUsername(config.getUsername());
		ds.setPassword(config.getPassword());

		return ds;
	}

	@Bean
	public LocalSessionFactoryBean localSessionFactory(DataSource ds,
			DBConfig config) {

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(ds);
		sessionFactory.setPackagesToScan("com.vyantech.sky.core.db.model");
		sessionFactory.setHibernateProperties(config.getHibernateProperties());
		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		return new HibernateTransactionManager(s);
	}

}
