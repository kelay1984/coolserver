package com.topda.redis;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service("redisDao")
public class RedisDaoSupport<T extends Serializable> implements RedisDAO<T> {
	@Resource
	private RedisTemplate<String, T> stringobjRedisTemplate;

	public void save(String key, T data) {
		ValueOperations<String, T> valueOper = stringobjRedisTemplate
				.opsForValue();
		valueOper.set(key, data);
	}

	public void save(String k, T v, long timeout) {
		ValueOperations<String, T> valueOper = stringobjRedisTemplate
				.opsForValue();
		valueOper.set(k, v, timeout, TimeUnit.SECONDS);
	}

	public T getData(String keystr) {
		ValueOperations<String, T> valueOper = stringobjRedisTemplate
				.opsForValue();
		return valueOper.get(keystr);
	}

	public void save(Map<String, T> kvmap) {
		ValueOperations<String, T> valueOper = stringobjRedisTemplate
				.opsForValue();
		Iterator<String> iterator = kvmap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			T value = kvmap.get(key);
			valueOper.set(key, value);
		}
	}

	public void save(Map<String, T> kvmap, long timeout) {
		ValueOperations<String, T> valueOper = stringobjRedisTemplate
				.opsForValue();
		Iterator<String> iterator = kvmap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			T value = kvmap.get(key);
			valueOper.set(key, value, timeout, TimeUnit.SECONDS);
		}
	}

	public List<T> getData(Set<String> keys) {
		ValueOperations<String, T> valueOper = stringobjRedisTemplate
				.opsForValue();
		return valueOper.multiGet(keys);
	}

	public RedisTemplate<String, T> getStringobjRedisTemplate() {
		return stringobjRedisTemplate;
	}

	public void setStringobjRedisTemplate(
			RedisTemplate<String, T> stringobjRedisTemplate) {
		this.stringobjRedisTemplate = stringobjRedisTemplate;
	}

	public void delete(String key) {
		stringobjRedisTemplate.delete(key);
	}

	public void delete(List<String> keys) {
		stringobjRedisTemplate.delete(keys);
	}
	
	

}
