package com.thebo.framework.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简单缓存实现
 * @author yinyafei
 * 将访问的频繁的数据放入缓存当中
 * 由于mybatis二级缓存不可控,联询及集群环境等会有问题
 * 所以在业务层编写可控的缓存
 * 后期可采用redis或memcache等实现
 * 2015.10.30
 */
public class SimpleCacheManager implements Cache{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static Map<String, String> cacheMap = new ConcurrentHashMap<String, String>();// 缓存容器
	
	private static Map<String, Object> cacheObjectMap = new ConcurrentHashMap<String, Object>();// 缓存容器

	private static SimpleCacheManager simpleCacheManager;
	
	public static SimpleCacheManager getInstance() {
		if (simpleCacheManager == null) {
			cacheMap.clear();
			simpleCacheManager = new SimpleCacheManager();
			simpleCacheManager.timerRefresh();
			// new UidMappingCache().loadAllData();
			new CommonCfgCache().loadAllData();
		}
		return simpleCacheManager;
	}

	public void putValue(String key, String value) {
		cacheMap.put(key, value);
	}

	public String getValue(String key) {
		return cacheMap.get(key);
	}

	public void removeValue(String key) {
		cacheMap.remove(key);
	}
	
	public void putObject(String key, String Object) {
		cacheObjectMap.put(key, Object);
	}

	public Object getObject(String key) {
		return cacheObjectMap.get(key);
	}

	public void removeObject(String key) {
		cacheObjectMap.remove(key);
	}

	public void clearCache() {
		cacheMap.clear();
		cacheObjectMap.clear();
	}

	public void refreshCache() {
		clearCache();
//		new UidMappingCache().loadAllData();
		new CommonCfgCache().loadAllData();
	}
	
	/**
	 *  定时刷新缓存
	 *  集群环境,每台服务器自行刷新
	 */
	private void timerRefresh() {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 1);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// 重新加载缓存
				refreshCache();
			}
		}, calendar.getTime(), 86400 * 1000); // 隔天刷新

		logger.debug("-------------- 执行缓存刷新{}", new Date());
	}
}
