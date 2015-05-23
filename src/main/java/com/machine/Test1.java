package com.machine;

import java.util.Random;

public class Test1 {
  public static void main(String []args){
	  
	  int k1 = 3 ;
	  int k2 = 5 ;
	  int b = 2 ;
	  
	  Random r = new Random();
	  for(int i=0;i<1000;i++){
		  int j = i+3 ;
		  //if(r.nextInt(5) ==3){
			  System.out.println(k1*i + k2*j+b +";" + i +";" + j );
		  //}
		  //else{
		 	//  System.out.println(k1*i + k2*j+b - r.nextInt(3) +";" + i + ";" + j );
		  //}
		  
		  
	  }
	  
	  
  }
}
