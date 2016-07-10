package daily.jvm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class TestHashMap {


    /**
     * @author Arpit Mandliya
     */
    public static void main(String[] args) {
           
        final Country india=new Country("India",1000);
        Country japan=new Country("Japan",10000);
           
        Country france=new Country("France",2000);
        Country russia=new Country("Russia",20000);
           
        ConcurrentHashMap<Country,String> countryCapitalMap=new ConcurrentHashMap<Country,String>();
//        countryCapitalMap.put(india,"Delhi");
//        countryCapitalMap.put(japan,"Tokyo");
//        countryCapitalMap.put(france,"Paris");
//        countryCapitalMap.put(russia,"Moscow");
//           
//        Iterator<Country> countryCapitalIter=countryCapitalMap.keySet().iterator();//put debug point at this line
//        while(countryCapitalIter.hasNext()){
//            Country countryObj=countryCapitalIter.next();
//            String capital=countryCapitalMap.get(countryObj);
//            System.out.println(countryObj.getName()+"----"+capital);
//        }
        
        
        System.out.println(countryCapitalMap.putIfAbsent(india,"Delhi")); // 没有对应的 key 返回 null 
        System.out.println(countryCapitalMap.putIfAbsent(india,"aaa")); // 有对应的key 返回原值
        System.out.println(countryCapitalMap.get(india));
    	
    	
        
    	new Thread(new Runnable() {
  	  
  	     @Override
  	     public void run() {
  	    	 	System.out.println(india.getSynName());
  	     }
  	}).start();
    	
    	try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	new Thread(new Runnable() {
    	  	  
     	     @Override
     	     public void run() {
     	    	    System.out.println("popu");
     	    	 	System.out.println(india.getSynPopulation());
     	     }
     	}).start();
        
    	
//    	final List<String> myList = new ArrayList<String>();
//    	for(int i=0;i<50;i++){
//    		myList.add(i + "");
//    	}
//    	 
//    	new Thread(new Runnable() {
//    	  
//    	     @Override
//    	     public void run() {
//    	          for (String string : myList) { // 通过 Iterator 
//    	               System.out.println("遍历集合 value = " + string);
//    	               try {
//    	                    Thread.sleep(100);
//    	               } catch (InterruptedException e) {
//    	                    e.printStackTrace();
//    	               }
//    	          }
//    	     }
//    	}).start();
//    	 
//    	new Thread(new Runnable() {
//    	  
//    	     @Override
//    	     public void run() {
//    	       
//    	       for (Iterator<String> it = myList.iterator(); it.hasNext();) {
//    	           String value = it.next();
//    	        
//    	           if (value.equals( "3")) {
//    	        	        System.out.println("删除元素 value = " + value);
//    	                it.remove();
//    	           }
//    	        
//    	           try {
//    	                    Thread.sleep(100);
//    	               } catch (InterruptedException e) {
//    	                    e.printStackTrace();
//    	               }
//    	      }
//    	     }
//    	}).start();
//   
    }
	
}
