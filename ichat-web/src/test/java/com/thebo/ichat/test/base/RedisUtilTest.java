package com.thebo.ichat.test.base;

import com.thebo.framework.cache.RedisUtil;
import org.junit.Test;

/**
 * Created by HB on 16/1/19.
 */
public class RedisUtilTest {

//    <constructor-arg name="host" value="10.211.55.16" />
//    <constructor-arg name="port" value="6379" />
//    <constructor-arg name="timeout" value="3000" />
//    <constructor-arg name="password" value="hubble" />

    @Test
    public void testRedis(){
        RedisUtil ru = new RedisUtil("10.211.55.16", 6379, 3000, "hubble");
        ru.set("key1", "hello Redis");

        System.out.println(ru.get("key1"));
    }
}
