package daily.jvm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestHashMap {

	/**
	 * @author Arpit Mandliya
	 */
	public static void main(String[] args) {
        
		
		final Map<String,String> map = new HashMap<String,String>();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0;i<100;i++){
					map.put(i+"", i+"");
				}
				
			}
		}).start();


		new Thread(new Runnable() {

			@Override
			public void run() {
				for(String key:map.keySet()){
					System.out.println(key);
				}
//				for(int i=0;i<100;i++){
//					map.remove(i+"");
//				}
			}
		}).start();

		// final List<String> myList = new ArrayList<String>();
		// for(int i=0;i<50;i++){
		// myList.add(i + "");
		// }
		//
		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// for (String string : myList) { // 通过 Iterator
		// System.out.println("遍历集合 value = " + string);
		// try {
		// Thread.sleep(100);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		// }
		// }).start();
		//
		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		//
		// for (Iterator<String> it = myList.iterator(); it.hasNext();) {
		// String value = it.next();
		//
		// if (value.equals( "3")) {
		// System.out.println("删除元素 value = " + value);
		// it.remove();
		// }
		//
		// try {
		// Thread.sleep(100);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		// }
		// }).start();
		//
		
		try {
			TimeUnit.SECONDS.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
