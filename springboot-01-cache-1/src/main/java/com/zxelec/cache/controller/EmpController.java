package com.zxelec.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxelec.cache.bean.Employee;
import com.zxelec.cache.service.EmpService;

@RestController
public class EmpController {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private EmpService empService;

	@GetMapping("/emp/{id}")
	public Employee queryEmployeeById(@PathVariable("id") Integer id) {
		return empService.queryEmpById(id);
	}
	
	@GetMapping("/get/{id}")
	public Employee getById(@PathVariable("id") Integer id) {
		return empService.getEmpId(id);
	}
	
	@GetMapping("/del/{id}")
	public String delEmp(@PathVariable("id") Integer id) {
		empService.delEmpId(id);
		return "success";
	}
	
	@GetMapping("/getRedis/{key}")
	public String queryRedis(@PathVariable("key") String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}
	@GetMapping("/put/{id}")
	public Employee putRedis(@PathVariable("id") Integer id) {
		return empService.getRedisId(id);
	}
}
