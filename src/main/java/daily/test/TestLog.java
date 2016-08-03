package daily.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
	
	Logger logger = LoggerFactory.getLogger("FILE");

	public void test(){
		logger.info("test------");
	}
	
	public static void main(String args[]){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(1468218180000l);
		System.out.println(calendar.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dstring = sdf.format(calendar.getTime());
		System.out.println(dstring);
		
		
	}
}
