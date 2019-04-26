package com.zxelec.cache;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.zxelec.cache.mapper.EmployeeMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01Cache1ApplicationTests {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private DruidStatProperties druidStatProperties;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Test
	public void contextLoads() {
		try {
			System.out.println(druidStatProperties.toString());
			System.out.println(dataSource.getClass());
			Connection conn = dataSource.getConnection();
			System.out.println(conn);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(employeeMapper.getEmployeeById(1));;
	}

}
