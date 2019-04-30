package com.zxelec.cache.config;

import java.net.UnknownHostException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
/**
 * 整理redis
 * @author liu.yongquan
 *
 */
@Configuration
public class CacheConfig {
	
//	@Bean
//	public RedisTemplate<Object, Object> redisTemplate(
//			RedisConnectionFactory redisConnectionFactory)
//			throws UnknownHostException {
//		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
//		new Jackson2JsonRedisSerializer<T>(Object)
//		template.setDefaultSerializer(serializer);
//		template.setConnectionFactory(redisConnectionFactory);
//		return template;
//	}
}	
