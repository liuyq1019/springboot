package com.zxelec.cache.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.zxelec.cache.bean.Employee;

@Mapper
public interface EmployeeMapper {
	
	@Select("select * from employee where id = #{id}")
	public Employee getEmployeeById(Integer id);
	
	@Delete("delete from employee where id = #{id}")
	public void delEmp(Integer id);
}

