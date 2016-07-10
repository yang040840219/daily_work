package daily.jvm;

import java.util.concurrent.CopyOnWriteArrayList;

public class TestCopyWriteList {
	
	
	public static void main(String[] args) {
		
		CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<Integer>();
		
		list.addIfAbsent(1) ;
		list.addIfAbsent(2);
		list.addIfAbsent(3);
		
		for(Integer t:list){
			if(t == 2){
				list.remove(t);
			}
			System.out.println(t);
		}
		
		System.out.println("----");
		
		for(Integer t:list){
			System.out.println(t);
		}
		
		
	}

}
