package com.pavilion.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.net.URL;

public class EhcacheUtil {
    Logger logger= LoggerFactory.getLogger(EhcacheUtil.class);
    private static final String path = "ehcache.xml";
    private static final String cacheName="mtl";
    private CacheManager manager;

    private static EhcacheUtil ehCache;

    private EhcacheUtil(String path) {
        try {
            ClassPathResource classPathResource = new ClassPathResource(path);
            URL url = classPathResource.getURL();
            manager = CacheManager.create(url);
        }catch (Exception ex){
            logger.error("缓存创建失败:"+ex.toString());
            ex.printStackTrace();
        }
    }

    public static EhcacheUtil getInstance() {
        if (ehCache== null) {
            ehCache= new EhcacheUtil(path);
        }
        return ehCache;
    }

    public void put(String key, Object value) {
        Cache cache = manager.getCache(cacheName);
        Element element = new Element(key, value);
        cache.put(element);

        Object obj = cache.get(key);
    }

    public Object get(String key) {
        Cache cache = manager.getCache(cacheName);
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    public Cache getCache() {
        return manager.getCache(cacheName);
    }

    public void remove(String key) {
        Cache cache = manager.getCache(cacheName);
        cache.remove(key);
    }

    public void removeAll(){
        Cache cache = manager.getCache(cacheName);
        cache.removeAll();
    }
}
