package com.machine;

public class SimpleGradientDescent {
	
	public static void main(String[] args) {
		// y = 3x + 1
		int[] input = { 1, 2, 3, 4, 5, 6, 7 };
		int[] output = { 4, 7, 10, 13, 16, 19, 22 };
		double k = 1;
		double b = 1;
		double alpha = 0.01;
		for (int i = 0; i < 500; i++) {
			double tmp1 = 0;
			double tmp2 = 0;
			for (int x = 0; x < input.length; x++) {// 这样做每次需要遍历全部的样本量
				tmp1 = alpha * 1 / 3 * (k * input[x] + b - output[x]) * input[x];
				tmp2 = alpha * 1 / 3 * (k * input[x] + b - output[x]);
				k = k - tmp1;
				b = b - tmp2;
				
				double loss = 0 ;
				double sum = 0 ;
				for(int x1= 0 ; x1< input.length;x1++){
					 sum = sum + k * input[x1] + b - output[x1] ;
				}
				loss = 0.5 * sum * sum  ; 
				System.out.println(k + "     " + b + "     " + loss);
			}

		}
	}

}
