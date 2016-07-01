package com.jvm;

import java.util.concurrent.atomic.AtomicInteger;

public class NoneBlockingCounter {
	
	private static AtomicInteger value = new AtomicInteger(0);
	
	public static int increment(){
		int v  ;
		do{
			v = value.get();
		}while(!value.compareAndSet(v,v+1));
		return v + 1 ;
	}
	
	public static int getValue(){
		return value.get();
	}
}

