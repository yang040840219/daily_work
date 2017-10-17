import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


public class Test2 {

	public static void main(String[] args) {
		Queue<String> queue = new LinkedBlockingQueue<String>() ;
		queue.offer("a") ;
		queue.offer("a") ;
		
		for(String tmp : queue){
			System.out.println(tmp);
		}
	}
	
}
