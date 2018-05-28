package com.sinotech.config;

import java.util.Properties;

import com.sinotech.util.FileOperation;

public class ConfigConsts {

	public static Properties properties=FileOperation.readConfigProperties("redis.properties");
	
	/**渠道层代付接口路径*/
	public static final String URL_CHANNEL_PAYAGENT=properties.getProperty("payagent_http_send_channel");
	/**核心层代付回调接口路径*/
	public static final String URL_CORE_PAYAGENT_CALLBACK=properties.getProperty("payagent_http_callback_core");
	//Redis服务地址
	public static final String REDIS_ADDR = properties.getProperty("redis.host");//"10.100.3.167";
	//Redis的端口号
	public static final int REDIS_PORT = Integer.valueOf(properties.getProperty("redis.port"));//6379;
    //Redis访问密码
	public static final String REDIS_AUTH = properties.getProperty("redis.auth");//"";
    //Redis可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	public static final int REDIS_MAX_ACTIVE = Integer.valueOf(properties.getProperty("redis.max_active"));//1024;
    //Redis控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	public static final int REDIS_MAX_IDLE = Integer.valueOf(properties.getProperty("redis.max_idle"));//200;
    //Redis等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	public static final int REDIS_MAX_WAIT = Integer.valueOf(properties.getProperty("redis.max_wait"));//10000;
	public static final int REDIS_TIMEOUT = Integer.valueOf(properties.getProperty("redis.timeout"));//10000;
    //Redis在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	public static final boolean REDIS_TEST_ON_BORROW = Boolean.valueOf(properties.getProperty("redis.test_on_borrow"));//true;
	
	/** 系统超时时间【毫秒】 ，当前设为10分钟*/
	public final static long SYSTEM_TIMEOUT = 180000L;
	//http请求
	public static final int HTTP_GET = 0;
	public static final int HTTP_POST = 1;
	public static final int HTTP_POST_GOTYE = 2;
	public static final int HTTP_PUT = 4;
	public static final int HTTP_DELETE = 5;
}
