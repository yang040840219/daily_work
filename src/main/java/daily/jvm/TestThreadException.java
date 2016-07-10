package daily.jvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class TestThreadException {
	
	static class ExceptionThread2 implements Runnable {
	    public void run() {
	        Thread t = Thread.currentThread();
	        //int i = 1/0 ;
	        Thread.yield();
	        try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
	    @Override
	    public void uncaughtException(Thread t, Throwable e) {
	    	 e.printStackTrace();
	    }
	}
	
	
	static class HandlerThreadFactory implements ThreadFactory {
	    @Override
	    public Thread newThread(Runnable r) {
	        System.out.println(this + " creating new Thread");
	        Thread t = new Thread(r);
	        System.out.println("created " + t + " ID:" + t.getId());
	        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
	        System.out.println("eh=" + t.getUncaughtExceptionHandler());
	        return t;
	    }
	}

	 public static void main(String[] args) {
		 Thread.setDefaultUncaughtExceptionHandler(
	                new MyUncaughtExceptionHandler());
	        ExecutorService exec = Executors.newSingleThreadExecutor();
	        for(int i=0;i<200;i++){
	        	  exec.submit(new ExceptionThread2());
	        }
	      
	       // exec.submit(new ExceptionThread2());
	    }

}
