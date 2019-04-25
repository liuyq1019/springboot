package com.zxelec.cache.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.zxelec.cache.properties.DruidDataSourceProperties;

@Configuration
@EnableConfigurationProperties({DruidDataSourceProperties.class})
public class DruidConfig {
	
	@Autowired
	private DruidDataSourceProperties druidDataSourceProperties;
	
	
	
	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean(destroyMethod = "close",initMethod = "init")
	public DataSource druid() {
		DruidDataSource datasource = new DruidDataSource();
//        datasource.setUrl(druidDataSourceProperties.getUrl());
//        datasource.setUsername(druidDataSourceProperties.getUsername());
//        datasource.setPassword(druidDataSourceProperties.getPassword());
//        datasource.setDriverClassName(druidDataSourceProperties.getDriverClassName());
//
//        //configuration
//        datasource.setInitialSize(druidDataSourceProperties.getInitialSize());
//        datasource.setMinIdle(druidDataSourceProperties.getMinIdle());
//        datasource.setMaxActive(druidDataSourceProperties.getMaxActive());
//        datasource.setMaxWait(druidDataSourceProperties.getMaxWait());
//        datasource.setTimeBetweenEvictionRunsMillis(druidDataSourceProperties.getTimeBetweenEvictionRunsMillis());
//        datasource.setMinEvictableIdleTimeMillis(druidDataSourceProperties.getMinEvictableIdleTimeMillis());
//        datasource.setValidationQuery(druidDataSourceProperties.getValidationQuery());
//        datasource.setTestWhileIdle(druidDataSourceProperties.isTestWhileIdle());
//        datasource.setTestOnBorrow(druidDataSourceProperties.isTestOnBorrow());
//        datasource.setTestOnReturn(druidDataSourceProperties.isTestOnReturn());
//        datasource.setPoolPreparedStatements(druidDataSourceProperties.isPoolPreparedStatements());
//        datasource.setMaxPoolPreparedStatementPerConnectionSize(druidDataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
//        try {
//            datasource.setFilters(druidDataSourceProperties.getFilters());
//            datasource.init();
//        } catch (SQLException e) {
//            System.err.println("druid configuration initialization filter: " + e);
//        }
//        datasource.setConnectionProperties(druidDataSourceProperties.getConnectionProperties());
        return datasource;
	}
	
	//配置druid监控
	//第一步配置servlet
	@Bean
	public ServletRegistrationBean statViewServlet() {
		ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
		Map<String,String> initParameters = new HashMap<>();
//		bean.setServlet(new StatViewServlet());
		initParameters.put("loginUsername", "admin");
		initParameters.put("loginPassword", "123456");
		bean.setInitParameters(initParameters);
		return bean;
	}
	//配置web的filter
	@Bean
	public FilterRegistrationBean webStatFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new WebStatFilter());
		Map<String,String> initParameters = new HashMap<>();
		initParameters.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		initParameters.put("profileEnable", "true");
		bean.setInitParameters(initParameters);
		bean.addUrlPatterns("/*");
		return bean;
	}
	
	
	

}
