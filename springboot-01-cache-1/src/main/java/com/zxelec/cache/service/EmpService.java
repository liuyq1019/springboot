package com.zxelec.cache.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zxelec.cache.bean.Employee;
import com.zxelec.cache.mapper.EmployeeMapper;

@Service
public class EmpService {
	
	private Logger logger = LogManager.getLogger(EmpService.class);
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	/**
	 * 将方法的运行结果进行缓存
	 * 	cacheNames/value:指定缓存名字
	 *  key：缓存数据的key，可以用它来指定，，默认是使用方法的参数
	 *  keygenerator：key的生成器，可以自己指定key的生成器组件id
	 *  			  kye/keygenerator:二选一
	 *  cachemanager:指定缓存管理器：或者cacheeResolver指定获取解析器
	 *  condition：指定符合条件的情况下进行缓存
	 *  unless：否定缓存：当unless指定的条件为true，方法的返回值就不会被缓存，可以获取到结果进行判断
	 *  sync:是否进行异步模式
	 *  
	 * 	原理
	 * 		1、自定配置类cacheautoconfiguration
	 *      2、缓存的配置类
	 *      org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
	 *      org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
	 *      org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
	 *      org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
	 *      org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
	 *      org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
	 *      org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration 
	 *      org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
	 *      org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
	 *      org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
	 *      org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames = "emp",key = "#id")
	public Employee queryEmpById(Integer id) {
		logger.info("查询@Cacheable数据:"+id);
		return employeeMapper.getEmployeeById(id);
	}
	
	/**
	 * @CachePut :调用方法同时更新缓存
	 * @return
	 */
	@CachePut(cacheNames = "emp",key = "#id")
	public Employee getEmpId(Integer id) {
		logger.info("查询@CachePut数据:"+id);
		return employeeMapper.getEmployeeById(id);
	}
	
	/**
	 * 	删除数据
	 * @cacheEvict：清除缓存
	 * 				value 存储地址
	 * 				key：指定要清除的数据
	 *              allEntries 清除全部数据
	 * 				beforeInvocatio = false 缓存的清除是否在方法之前执行
	 * 				默认代表是在方法之后执行	
	 * @param id
	 */
	@CacheEvict(value = "emp", 
			/* key = "#id" , */
			allEntries = true )
	public void delEmpId(Integer id) {
//		employeeMapper.delEmp(id);
		logger.info("删除@@CacheEvict数据:"+id);
	}
	
	
	
	public Employee getRedisId(Integer id) {
		Employee emp =  employeeMapper.getEmployeeById(id);
		String json = JSONObject.toJSONString(emp);
		logger.info("数据存储到redis:"+id);
		stringRedisTemplate.opsForValue().set("id_"+id, json);
		return emp;
	}
	
	
}
