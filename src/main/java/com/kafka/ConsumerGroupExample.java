package com.kafka;

 
public class ConsumerGroupExample {
  
    public static void main(String[] args) {
    	 Consumer consumerThread = new Consumer("test");
    	    consumerThread.start();
    }
}