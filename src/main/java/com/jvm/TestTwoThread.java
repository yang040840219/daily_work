package com.jvm;

import java.util.concurrent.TimeUnit;

public class TestTwoThread {
   
	static class PrintLetterThread implements Runnable{
		private static final String[] letters = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n"} ;
		
		private Object o  ;
		
		public PrintLetterThread(Object o){
			this.o = o ;
		}
		
		@Override
		public void run() {
			int i = 0 ;
			int count = 0 ;
			while(true){
				    if(i >= letters.length){
				    	   synchronized (o) {
							o.notify();
						}
				    	   break;
				    }
					System.out.print(letters[i]);
					i++ ;
					count ++ ;
					if(count == 2){
						try {
							count = 0 ;
								synchronized (o) {
									o.notify();
									o.wait();
								}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
			}
           			 
		}
		
	}
	
	static class PrintDigitThread implements Runnable{
		private static final Integer[] digits = new Integer[]{1,2,3,4,5,6,7};
		
		private Object o ;
		
		public PrintDigitThread(Object o){
			this.o = o ;
		}
		
		@Override
		public void run() {
			int i = 0 ;
			while(true){
				    if(i == digits.length){
				    	 synchronized (o) {
				    		 o.notify();
						  }
				    	  break;
				    }
				    
					System.out.print(digits[i]);
					
					i++ ;
					
					try {
							synchronized (o) {
								o.notify();
								o.wait();
								
							}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
			}
           			 
		}
		
	}
	
	
	public static void main(String[] args) throws Exception{
		
		Object o = new Object();
		
		Thread t1 = new Thread(new PrintLetterThread(o));
		Thread t2 = new Thread(new PrintDigitThread(o));
		
		t1.start();
		TimeUnit.SECONDS.sleep(2);
		t2.start();
		
	}
	
}
