package com.map;

import java.util.Map;
import java.util.WeakHashMap;

public class TestWeakHashMap {  
	  public static void main(String[] args) throws InterruptedException {  
		  
		  /**
		   *       WeakHashMap是基于弱引用实现的哈希表，
		   *       与HashMap在操作上基本相同，WeakHashMap最大的特点是对于哈希表中的每个Key，
		   *       如果除了自身对Key有引用外，此Key没有其他引用，
		   *       那么该Key对应的键值对会被表自动移除，这个行为取决于垃圾回收器的动作，
		   *       只有在垃圾回收器清除该Key的弱引用之后，该键值对才会被自动移除。
		   */
	      Map<String, String> weakHashMap=new WeakHashMap<String,String>();    
	      String str=new String("test");  
	      weakHashMap.put(str,"1");  
	      System.out.println("size1:"+weakHashMap.size());  
	      str=null;  
	      System.gc();
	      Thread.sleep(2000);  
	      System.out.println("size2:"+weakHashMap.size());  
	  }  
	}  