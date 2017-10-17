package daily.test;

import java.util.ArrayList;
import java.util.List;

public class TestGeneric {
	
	public static void main(String[] args) {
		
//		Apple[] apples = new Apple[1];
//		Fruit[] fruits = apples;
//		fruits[0] = new Orange(); 
		
		//List<? super Fruit> fruits = new ArrayList<Object>();
		
		// fruits.add(new Apple());
		// fruits.add(new Orange()) ;
		
		// List<? extends Fruit> fruits = new ArrayList<Apple>();
		// fruits.add(new Apple());
		
		
		List<Apple> apples = new ArrayList<Apple>();
		List<? extends Fruit> fruits = apples;
		// fruits.add(new Orange());
		
//		List<? extends Fruit> fruits = new ArrayList<Apple>();
//		
//		// fruits.add(new Apple()) ;
//		
//		Fruit apple = fruits.get(0) ;
//		
//		for(Fruit fruit:fruits){
//			System.out.println(fruit);
//		}
	}

}
