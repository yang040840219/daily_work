package daily.lock;

import java.util.concurrent.CountDownLatch;

import scala.util.Random;

public class TestCountDown {

	public static CountDownLatch countDownLatch = new CountDownLatch(10);
	
	
	public static void main(String[] args) {
	
		Thread t = new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					countDownLatch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("begin");
			}
			
		});
		t.start();
		
		for(int i=0;i<10;i++){
			Thread tmpThread = new Thread(new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Random random = new Random();
						Thread.sleep(random.nextInt(10) * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					countDownLatch.countDown();
					System.out.println(Thread.currentThread().getName() + " ready!");
				}
			});
			tmpThread.setName("thread-" + i);
			tmpThread.start();
		}
		
	}
	
}
