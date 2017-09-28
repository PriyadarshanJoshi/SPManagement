package com.neosoft.sportsclubmanagement.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.neosoft.sportsclubmanagement.dao.UserDao;
import com.neosoft.sportsclubmanagement.dao.UserDaoImpl;
import com.neosoft.sportsclubmanagement.service.UserService;
import com.neosoft.sportsclubmanagement.service.UserServiceImpl;


@Configuration
@ComponentScan("com.neosoft.sportsclubmanagementdao")
@EnableTransactionManagement
public class AppConfig {

	@Bean(name="userDao")
	public UserDao getUserDao(SessionFactory sessionFactory)
	{
		return new UserDaoImpl(sessionFactory);
	}
	@Bean(name="userService")
	public UserService userService(UserDao userDao)
	{
		return new UserServiceImpl(userDao);
	}
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/SPORTS_CLUB_MANAGEMENT");
	    dataSource.setUsername("root");
	    dataSource.setPassword("ac3r");
	 
	    return dataSource;
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
	        SessionFactory sessionFactory) {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager(
	            sessionFactory);
	 
	    return transactionManager;
	}
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
	 
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	 
	    sessionBuilder.scanPackages("com.neosoft.sportsclubmanagement.model");
	    sessionBuilder.setProperty("hibernate.show_sql", "true");
	    sessionBuilder.setProperty("hibernate.hbm2ddl.auto","update");
	    sessionBuilder.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
	    sessionBuilder.addProperties(getHibernateProperties());

	 
	    return sessionBuilder.buildSessionFactory();
	}
	private Properties getHibernateProperties() {
	    Properties properties = new Properties();
	    return properties;
	}
}
