package kafka;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog4j {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void log(){
    	for(int i = 0;i < 100;i++){
    		logger.info("i:" + i);
    	}
    	
    	try {
			TimeUnit.SECONDS.sleep(120);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
      	for(int i = 0;i < 100;i++){
    		logger.info("i:" + i);
    	}
    	
    }
	
	public static void main(String[] args) {
		//new TestLog4j().log();

		Calendar calendar = Calendar.getInstance() ;
		calendar.setTimeInMillis(1497542400000l);
		System.out.println(calendar.getTime());
		
	}

}
