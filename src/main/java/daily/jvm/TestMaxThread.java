package daily.jvm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestMaxThread {
    
	public static void main(String []args) throws Exception{
		
		Map<String,Country> map = new HashMap<String,Country>();
		
		for(int i=0;i< 1000000;i++){
			TimeUnit.MILLISECONDS.sleep(500);
			Country c = new Country("1",1);
			
			map.put(i+"",c);
			
		}
		
		
	}
	 
}
