package org.gege.utils;

import redis.clients.jedis.Jedis;

public class JedisUtils {
	public final static Jedis jedis = new Jedis(Utils.readProperity("redis.host"),Integer.valueOf(Utils.readProperity("redis.port")));
}
