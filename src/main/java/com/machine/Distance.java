package com.machine;

public class Distance
{
	   public static int getDistance(String s1, String s2)
	   {
		   int len1 = s1.length();
		   int len2 = s2.length();
		   
		   int[][] d = new int[len1+1][len2+1];
		   int i=0, j=0;
		   for(i=0; i<=len1; i++){
			   d[i][0] = i;
		   }
			  
		   for(j=0; j<=len2; j++){
			   d[0][j] = j;
		   }
			   
			for (i = 1; i < len1+1; i++){
				for (j = 1; j < len2+1; j++)
				{
					int cost = 1;
					if(s1.charAt(i-1) == s2.charAt(j-1))
					{
						cost = 0;
					}
					int delete = d[i - 1][j] + 1;
					int insert = d[i][j - 1] + 1;
					int substitution = d[i - 1][j - 1] + cost;
					d[i][j] = min(delete, insert, substitution);
					System.out.println("i:"+ i + " j:" + j + " s1:"+ s1.substring(0,i) + " s2:" + s2.substring(0,j) + " delete:" + delete + " insert:" + insert + " substitution:" + substitution + " d[i][j]:" + d[i][j]);
			   }
			}
			
			System.out.println("--------------");
			for(int ix=0;ix<d.length;ix++){
				for(int jx=0;jx<d[ix].length;jx++){
					System.out.print(d[ix][jx] + "\t");
				}
				System.out.println();
			}
			System.out.println("--------------");
			
			return (d[len1][len2]);
	   }
	   
	   public static int min(int d,int i,int s)
	   {   
		    int temp = 0;
		    if(d>i)
		    	temp = i;
		    else
		    	temp = d;
		    return s<temp?s:temp;
		}
   
   public static void main(String args[])
   {
	   String s1= "kitten";
	   //String s1 = "sitting";
	   String s2 = "sitting";
	   
	   System.out.println(Distance.getDistance(s1, s2));
   }
}
