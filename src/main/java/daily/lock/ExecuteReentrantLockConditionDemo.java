package daily.lock;

public class ExecuteReentrantLockConditionDemo {
    public static void main(String[] args) {
        final ReentrantLockConditionDemo rd = new ReentrantLockConditionDemo();
        // 创建1个消费者线程
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                    	   for(int i=0;i<10;i++){
                    		   rd.get();
                    	   }
                      
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.setName("consume-" + 0);
            t.start();
        // 创建10个生产者线程
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    try {
                    	  for(int i=0;i<10;i++){
                    		  rd.put("bread");
                    	  }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }) ;
            t1.setName("product-" + 0);
            t1.start();
    }
}
