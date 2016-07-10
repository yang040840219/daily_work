package daily.jvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class TestThreadLocal {
	
	public static void main(String[] args) throws Exception{
		
//		ExecutorService executorService = Executors.newCachedThreadPool();
//		
//		for(int i=0;i<4;i++){
//			executorService.execute(new DemoTask()); // 多个线程
//		}
//		executorService.awaitTermination(200, TimeUnit.SECONDS);
		
		DemoTask demoTask = new DemoTask();
		
		System.out.println("nextId:"+demoTask.getNextid());
		System.out.println("val:"+demoTask.getVal());
		System.out.println("threadLocal:"+demoTask.getThreadLocal());
		
		demoTask.setVal(2);
		demoTask.getThreadLocal();
		demoTask.getAndIncrementNextId();
		demoTask.setThreadLocal(100); // 在当前线程中修改
		
		DemoTask demoTask1 = new DemoTask();
		
		System.out.println("nextId:"+demoTask1.getNextid()); // 类变量在 DemoTask 中nextId.getAndIncrement() , 所以值变成 1
		System.out.println("val:"+demoTask1.getVal());
		System.out.println("threadLocal:"+demoTask1.getThreadLocal()); // 在同一个线程中，所以值没变
	}

}
