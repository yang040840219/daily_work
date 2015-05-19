import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cache.service.HelloService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-context.xml"})
@ActiveProfiles("test")
@Slf4j
public class TestMain extends AbstractJUnit4SpringContextTests  {
	
	@Autowired
	private HelloService helloService ;
	
    @Test
    public void test(){
    	 System.out.println(helloService);
    	 String value = helloService.getMessage("a");
    	 System.out.println(value);
    	 helloService.setMessage("a","345");
    	 value = helloService.getMessage("a");
    	 System.out.println(value);
    	 value = helloService.getMessage("b");
    	 System.out.println(value);
    }
		
}
