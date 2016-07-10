package daily.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
	
	Logger logger = LoggerFactory.getLogger("FILE");

	public void test(){
		logger.info("test------");
	}
	
	public static void main(String args[]){
		String  s = "s,a,n,,,";
		String [] array = s.split(",");
		System.out.println(array.length);
		
		String a = null ;
		
	}
}
