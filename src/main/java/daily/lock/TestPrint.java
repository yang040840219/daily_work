package daily.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestPrint {
	
	public static int i = 1 ;
	
   public static void main(String[] args) {
	   
	  final ReentrantLock lock = new ReentrantLock();
	   
	  final Condition condition1 = lock.newCondition();
	   
	  final Condition condition2 = lock.newCondition();
	   
	   // thread1  1,2,3,7,8,9
	   // thread2   4,5,6
	   
	   Thread t1 = new Thread(new Runnable(){

		@Override
		public void run() {
			lock.lock();
			try{
				while(i<4){
					System.out.println(Thread.currentThread().getName() + ":" + i);
					i = i + 1 ;
				}
				condition2.signal();
			}finally{
				lock.unlock();
			}
			
			lock.lock();
			try{
				while(i<7){
					condition1.await();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
			
			lock.lock();
			try{
				while(i<=9){
					System.out.println(Thread.currentThread().getName() + ":" + i);
					i++ ;
				}
			}finally{
				lock.unlock();
			}
			
			
		}
		   
	   }) ;
	   t1.setName("thread-1");
	   t1.start();
	   
	   Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				lock.lock();
				try{
					while(i < 4){
						try {
							condition2.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}finally{
					lock.unlock();
				}
				
				lock.lock();
				try{
					while(i<=6){
						System.out.println(Thread.currentThread().getName() + ":" + i);
						i++ ;
					}
					condition1.signal();
				}finally{
					lock.unlock();
				}
				
			}
			   
		   }) ;
	       t2.setName("thread-2");
		   t2.start();
	   
   }

}
