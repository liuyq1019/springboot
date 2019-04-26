package com.zxelec.cache.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * druid数据源配置如果是非application.properties文件
 * 
 * @EnableConfigurationProperties
 * ({DruidDataSourceProperties.class}) 
   *  将内容注解进去、新版本【druid-spring-boot-starter】
   *    不需要进行指定DataSource，
 * @author liu.yongquan
 *  	注意：1、配置DateSource：数据源配置路径为 spring.datasource.url 
 *  							          spring.datasource.druid.url 没有区别	
 *       	Bean需要指定 @Bean(destroyMethod = "close",initMethod = "init")
 *       	否则只有 @Bean (*) property for user to setup 监控平台
 *     		2、不配置DateSource
 * 
 *  	注意： 1、  配置DateSource：数据源路径为spring.datasource.druid.url
 *  		 2、 不配置 DataSource  
 */
@Configuration
public class DruidConfig {
	
	@Bean(destroyMethod = "close",initMethod = "init")
	public DataSource druid() {
		DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
		return dataSource;
	}

	/**
	 * 注册Servlet信息， 配置监控视图
	 *
	 * @return
	 */
	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
				"/druid/*");
		// 登录查看信息的账号密码, 用于登录Druid监控后台
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "admin");
		// 是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable", "true");
		return servletRegistrationBean;
	}

	/**
	 * 注册Filter信息, 监控拦截器
	 */
	@Bean
	public FilterRegistrationBean webStatFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new WebStatFilter());
		Map<String, String> initParameters = new HashMap<>();
		initParameters.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		initParameters.put("profileEnable", "true");
		bean.setInitParameters(initParameters);
		bean.addUrlPatterns("/*");
		return bean;
	}
}
