package daily.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestCountDownLatch {
	
	public static void main(String [] args) throws Exception {
		final CountDownLatch latch = new CountDownLatch(1);
		for(int i=0;i<5;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						latch.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getId());
				}
			}).start();
		}
		
		TimeUnit.SECONDS.sleep(10);
		latch.countDown();
		
	}

}
