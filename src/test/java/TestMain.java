import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

import com.cache.service.HelloService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring-context.xml" })
@Slf4j
public class TestMain extends AbstractJUnit4SpringContextTests {

	@Autowired
	private HelloService helloService;

	@Resource(name = "redisTemplate")
	private ValueOperations<Serializable, Serializable> valueOperations;

	@Autowired
	private JedisPool jedisPool;

	@Test
	public void test() {
		String value = helloService.getMessage("a");
		System.out.println(value);
		// helloService.setMessage("a","345");
		// value = helloService.getMessage("a");
		// System.out.println(value);
		value = helloService.getMessage("b");
		System.out.println(value);
		log.info("test");

	}

	@Test
	public void testPipline() {
		Jedis jedis = jedisPool.getResource();
		Pipeline pipline = jedis.pipelined();
		pipline.set("mykey", "123");
		pipline.get("mykey");
		List<Object> values = pipline.syncAndReturnAll();
		for (Object o : values) {
			System.out.println(o);
		}
	}

	@Test
	public void testMulti() {
		Jedis jedis = jedisPool.getResource();
		Transaction tx = jedis.multi();
		tx.set("mykey", "123");
		tx.set("mykey", "456");
		List<Object> values = tx.exec();
		for (Object o : values) {
			System.out.println(o);
		}
	}
	
	

	public boolean lock3(String key) {
		Jedis jedis = jedisPool.getResource();
		long nano = System.nanoTime();
		try {
			while ((System.nanoTime() - nano) < 1000) {
				jedis.watch(key);
				String value = jedis.get(key);
				if (value == null || value.equals("UNLOCK")) {
					Transaction t = jedis.multi();
					t.set(key, "123");
					if (t.exec() != null) {
						return true;
					}
				}
				jedis.unwatch();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Test
	public void timestamp(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(1433088000000l);
	    System.out.println(calendar.getTime());
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    try {
			Date d = sdf.parse("2015-06-01 20:25");
			calendar.setTime(d);
			System.out.println(calendar.getTimeInMillis());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	@Test
	public void regex(){
		String s = "<property><name>hive.query.string</name><value>select * from 123 </value>" ;
		String rgx = "<property><name>hive.query.string</name><value>.*</value>";
		Pattern p = Pattern.compile(rgx);
		Matcher m = p.matcher(s);
		System.out.println(m.matches());
		
	}
	
	
	
}
