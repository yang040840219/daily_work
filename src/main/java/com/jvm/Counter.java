package com.jvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Counter {

	public static void main(String[] args) throws Exception{
		
		class ThreadCounter implements Runnable{
			@Override
			public void run() {
				for(int i=0;i<10;i++){
					System.out.println(NoneBlockingCounter.increment());
				}
			}
		}
		
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for(int i=0;i<5;i++){
			executorService.execute(new ThreadCounter());
		}
		
		executorService.awaitTermination(10,TimeUnit.SECONDS);
		
		executorService.shutdown();
	
		System.out.println(NoneBlockingCounter.getValue());
		
	
		
	}
	
}
