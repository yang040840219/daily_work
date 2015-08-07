package com.m;

import java.text.NumberFormat;

public class TestFun {
	
	
	public static double sigmoid(int a){
		 
		double v = 1.0 / (1 + Math.exp(-a)) ;
		return v ;
		
	}
	
	public static void main(String []args){
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);

		System.out.println(nf.format(1233122333.2));
	}

}
