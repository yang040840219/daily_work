package daily.test;

import java.util.Calendar;

public class TestDate {
	
	public static void main(String[] args) {
		
		long timestamp = 1489369727000l ;
		
		Calendar calendar = Calendar.getInstance() ;
		
		calendar.setTimeInMillis(timestamp);

		System.out.println(calendar.getTime());
	}

}
