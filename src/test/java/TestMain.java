import java.io.Serializable;

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
import redis.clients.jedis.Transaction;

import com.cache.service.HelloService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-context.xml"})
@Slf4j
public class TestMain extends AbstractJUnit4SpringContextTests  {
	
	@Autowired
	private HelloService helloService ;
	
	
	@Resource(name="redisTemplate")
	private ValueOperations<Serializable,Serializable> valueOperations ;
	
	
	@Autowired
	private JedisPool jedisPool ;
	
	
    @Test
    public void test(){
    	 String value = helloService.getMessage("a");
    	 System.out.println(value);
//    	 helloService.setMessage("a","345");
//    	 value = helloService.getMessage("a");
//    	 System.out.println(value);
    	 value = helloService.getMessage("b");
    	 System.out.println(value);
     log.info("test"); 
    	 
    }
		
    @Test
    public void test1(){
     Jedis jedis = jedisPool.getResource() ;
     
    }
    
    
    public boolean lock3(String key){
    	Jedis jedis = jedisPool.getResource() ;
    	  long nano = System.nanoTime();
          try {
              while ((System.nanoTime() - nano) < 1000) {
                  jedis.watch(key);
                  // 开启watch之后，如果key的值被修改，则事务失败，exec方法返回null
                  String value = jedis.get(key);
                  if (value == null || value.equals("UNLOCK")) {
                      Transaction t = jedis.multi();
                      t.set(key,"123");
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
    
}
