package com.nyuen.dxtest.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;

/**
 * 工具类 - 缓存管理. <br>
 * 处理缓存的添加、更新、删除.
 */
public class CachedManager {

	/** LOG. */
	private final static Log log = LogFactory.getLog(CachedManager.class);

	private final static String DEFAULT_CACHE = "defaultcache";

	/*0 toke未过期  1 token不存在  2 token过期*/
	private final static String [] FLAG = {"0","1","2"};
	
	/** 缓存Manager实例. */
	private static CacheManager manager;
	static {
		try {
			manager = CacheManager.getInstance();
			if (null == manager) {
				manager = CacheManager.create();
			}
		} catch (Exception e) {
			log.error("Initialize cache manager faild!", e);
		}
	}

	/**
	 * 从缓存中获取对象.
	 * 
	 * @param cache_name
	 *            缓存名称，如ehcache.xml中 <br>
	 *            产品实体配置的name：com.handou.ehcache.Product
	 * @param key
	 *            一般采用缓存对象的主键（具有唯一性的字符串）
	 * @return 缓存对象
	 */
	public static Serializable getObjectCache(String cache_name,
			Serializable key) {
		Cache cache = getCache(cache_name);
		if (cache != null) {
			try {
				Element elem = cache.get(key);
				if (elem != null && !cache.isExpired(elem))
					return elem.getValue();
			} catch (Exception e) {

				log.error("Getcache(" + cache_name + ") of " + key

				+ "failed.", e);
			}
		}

		return null;
	}
	
	/**
	 * 从缓存中获取对象.
	 * 
	 *            缓存名称，如ehcache.xml中 <br>
	 *            产品实体配置的name：com.hydom.ehcache.Product
	 * @param key
	 *            一般采用缓存对象的主键（具有唯一性的字符串）
	 * @return 缓存对象
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T getObjectCache(Class<T> clazz, Serializable key) {
		return (T)getObjectCache(clazz.getName(), key);
	}

	/**
	 * 把对象放入缓存中
	 * 
	 * @param cache_name
	 *            缓存名称，如ehcache.xml中 <br>
	 *            产品实体配置的name：com.hydom.ehcache.Product
	 * @param key
	 *            一般采用缓存对象的主键（具有唯一性的字符串）
	 * @param value
	 *            需要加入缓存的实体对象，如new <br>
	 *            一个的com.hydom.ehcache.Product对象
	 */
	public synchronized static void putObjectCache(String cache_name,
			Serializable key, Serializable value) {
		Cache cache = getCache(cache_name);
		if (cache != null) {
			try {
				cache.remove(key);
				Element elem = new Element(key, value);
				cache.put(elem);
			} catch (Exception e) {
				log.error("putcache(" + cache_name + ") of " + key + "failed.", e);
			}
		}
	}
	
	/**
	 * 通过缓存名称获取缓存.
	 * @param cacheName - 缓存名称
	 * @return cache - 缓存
	 */
	public final static Cache getCache(Object cacheName) {
		return manager.getCache(cacheName.toString());
	}

//	/**
//	 * 缓存集合里面所有的元素.
//	 * @param cacheName
//	 * @param list
//	 * @param fieldName
//	 */
//	public static void cacheAll(String cacheName, Collection<?> list, String fieldName) {
//		Cache cache = getCache(cacheName);
//		if (cache == null) {
//			log.warn("Do not find cache, named by [" + cacheName + "]");
//			return;
//		}
//
//		for (Object o : list) {
//			Object key = ReflectionUtil.getFieldValue(o, fieldName);
//			if (null == key) {
//				log.warn("Could not find value [" + fieldName + "] in " + o.getClass());
//				continue;
//			}
//
//			Element element = new Element(key, o);
//			cache.put(element);
//		}
//	}

	public static Object get(String cacheName, Object key) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			Element element = cache.get(key);
			if (element != null) {
				return element.getObjectValue();
			}
		}
		return null;
	}

	/**
	 * 验证token是否过期
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static String validateTokenCache(String cacheName, Object key){
		Cache cache = manager.getCache(cacheName);
		if(cache != null){
			Element element = cache.get(key);
			System.out.println("element = "+element);
			//如果token存在
			if(element != null){
				boolean isExpired = cache.isExpired(element);
				System.out.println(isExpired);
				if(!isExpired)
					return FLAG[0];//token未过期
				//如果过期
				else{
					cache.remove(key);
					return FLAG[2];//token过期
				}
			}else{
				//token不存在(过期)
				return FLAG[1];
			}

		}
		return "";
	}

	public static void put(String cacheName, Object key, Object value) {
		Cache cache = manager.getCache(cacheName);
		System.out.println("cache = "+cache);
		if (cache != null) {
			cache.put(new Element(key, value));
		}
	}




	/**
	 * 从缓存中移出
	 */
	public static void remove(String cacheName, Serializable key) {
		Ehcache cache = getCache(cacheName);
		cache.remove(key);
	}

	public static void put(String key, Object value) {
		Ehcache ehcache = manager.getEhcache(DEFAULT_CACHE);
		Element element = new Element(key, value);

		ehcache.put(element);
	}

	public static <T> T get(String key, Class<T> clazz) {
		Ehcache ehcache = manager.getEhcache(DEFAULT_CACHE);
		Element element = ehcache.get(key);
		if (element != null) {
			return (T) element.getObjectValue();
		}

		return null;
	}

	public static void remove(Object key) {
		Ehcache ehcache = manager.getEhcache(DEFAULT_CACHE);
		ehcache.remove(key);
	}

}
