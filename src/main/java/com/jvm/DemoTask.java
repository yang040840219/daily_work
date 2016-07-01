package com.jvm;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class DemoTask implements Runnable {

	
	private  int val = 0 ; // 实例变量
	
	private static final  AtomicInteger nextId = new AtomicInteger(0); // 类变量
	

	public static AtomicInteger getNextid() {
		return nextId;
	}
	
	public synchronized int getVal() {
		return val;
	}

	public synchronized void setVal(int val) {
		this.val = val ;
	}



	private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() { // ThreadLocal 变量

		@Override
		protected Integer initialValue() { // 只有在首次调动 get 的时候才会执行
			System.out.println("create new thread local");
			return nextId.getAndIncrement();
		}

	};

	private static final ThreadLocal<Date> threadLocalDate = new ThreadLocal<Date>() {

		@Override
		protected Date initialValue() {
			return new Date();
		}

	};
	
	public static final Integer getNextId(){
		return nextId.get();
	}
	
	public static final Integer getAndIncrementNextId(){
		return nextId.getAndIncrement();
	}
	
	public static final void setThreadLocal(int i){
		threadLocal.set(i);
	}

	public int getThreadLocal() {
		return threadLocal.get();
	}

	@Override
	public void run() {
		System.out.println("Starting Thread: "+ this.getThreadLocal()  +" : "+threadLocalDate.get()+"");
		try {
			TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread Finished: "+ this.getThreadLocal() +" : "+ threadLocalDate.get() +"");
	}

}
