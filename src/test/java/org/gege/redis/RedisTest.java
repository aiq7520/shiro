package org.gege.redis;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.gege.utils.JedisUtils;
import org.junit.Test;

/**
 * 测试redis
 */
public class RedisTest {
//	Jedis jedis = new Jedis("192.168.19.128",6379);
	String filename = "C:\\Users\\lenove1\\Desktop\\8uftp\\a.txt";
	/**
	 * redis的链接测试
	 */
	@Test
	public void testPing(){
		//连接本地的 Redis 服务
		System.out.println(JedisUtils.jedis.get("key1"));
		JedisUtils.jedis.close();
	}
	
	/**
	 * 将用户信息加载到redis中
	 * @throws IOException 
	 */
	@Test
	public void cacheUserToRedis() throws IOException{
		List<String> lines  = FileUtils.readLines(new File(filename),  "UTF-8");
		Map<String,String> hash = new HashMap<String,String>();
		for (String username : lines) 
			hash.put(username,"123456");
		JedisUtils.jedis.hmset("users", hash);
	}
}
