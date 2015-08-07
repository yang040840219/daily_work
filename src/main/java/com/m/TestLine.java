package com.m;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import scala.util.Random;

public class TestLine {
   public static void main(String []args) throws IOException{
	   
	   String path = "/Users/yxl/data/mllib/simple_data" ;
	   BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path)));
	   
	   // y = 2x1 + 3x2 + 1 
	   Random r = new Random();
	  for(int i=0;i<10000;i++){
		  double s = r.nextInt(2000);
		  double n = (s - 0.5) * Math.sqrt(12.0 * (1.0/3.0)) ;
		  System.out.println(s + "  " + n);
		  double v = 2 * n + 3 * n + 3 ;
		 
		  
		  String line = v + " " + "1:" + n + " 2:"+ n + System.getProperty("line.separator");
		  bw.write(line);
		  bw.flush();
	  }
	  bw.close();
	   
   }
}
