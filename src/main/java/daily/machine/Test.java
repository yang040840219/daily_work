package daily.machine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

public class Test {
	public static void main(String[] args) {
		// double[][] array = {{1.,2.,3},{4.,5.,6.},{7.,8.,10.}};
		// Matrix A = new Matrix(array);
		// Matrix b = Matrix.random(3,1);
		// b.print(3,1);
		// Matrix x = A.solve(b);
		// x.print(3,1);

//		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	    List<Date> list = getTimeSegment(2014, 7, 1);
//	    for(Date date : list){
//	        System.out.println(fmt.format(date));
//	    }
		
		Calendar calendar = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = null ;
//		try {
//			date = sdf.parse("2015-05-05 23:58:20");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		calendar.setTime(date);
//		int minute = calendar.get(Calendar.MINUTE);
//		int a = minute % 5 ;
//		int b = minute / 5 ;
//		if(a != 0){
//			calendar.set(Calendar.MINUTE,(b+1)*5);
//		}
//		
//		System.out.println(calendar.getTime());
		
		calendar.setTimeInMillis(1427189400000l);
		System.out.println(calendar.getTime());
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		
	
		System.out.println(list.subList(1,6));
	
		
//		double lat = 22.589891 ;
//	    double lng = 113.957294	;
//		int grid = BuildModel.getCityGridId(6, lat, lng);
//		System.out.println(grid);
	}
	
	public static List<Date> getTimeSegment(int year, int month, int day){
	    Calendar cal = Calendar.getInstance();
	    cal.set(year, month-1, day, 0, 0, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    long startTime = cal.getTimeInMillis();
	    cal.set(year, month-1, day, 23, 59, 59);
	    long endTime = cal.getTimeInMillis();
	    final int seg = 5*60*1000;//五分钟
	    ArrayList<Date> result = new ArrayList<Date>((int)((endTime-startTime)/seg+1));
	    for(long time = startTime;time<=endTime;time+=seg){
	        result.add(new Date(time));
	    }
	    return result;
	}

	public boolean is2Far() {
		String[] statusArray = { "22", "21", "11", "12" };
		String hstatus = "1|2|2|3|2|2|-1";
		String[] array = hstatus.split("\\|");
		if (array != null && array.length >= 3) {
			int length = array.length;
			String current = array[length - 3] + array[length - 2];
			System.out.println(current);
			if (ArrayUtils.contains(statusArray, current)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
