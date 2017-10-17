package daily.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Existential {
	
	public static List<String> list = new ArrayList<>(Arrays.asList("Java", "sucks"));

	public static void t1() {
		List a = list;
		for(Object x:a){
			System.out.println(x);
		}
	}

	public static void t2() {
		List<?> b = list;
		for(Object x:b){
			System.out.println(x);
		}
	}

	public static void main(String[] args) {
		t1();
		t2();
	}

}