package com.costamar.app.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.costamar.app.dao.AccountDao;
import com.costamar.app.dao.AccountDaoImpl;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	@Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
	
	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.sybase.jdbc3.jdbc.SybDriver");
		dataSource.setUrl("jdbc:xxxxx");
		dataSource.setUsername("xxx");
		dataSource.setPassword("xxx");
		return dataSource;
	}
	
	@Bean
	public AccountDao getAccountDao(){
		return new AccountDaoImpl(getDataSource());
	}
	
}
