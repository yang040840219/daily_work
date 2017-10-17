package com.binlog;

import java.io.IOException;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.BinaryLogClient.EventListener;
import com.github.shyiko.mysql.binlog.event.Event;

public class Test {
   public static void main(String[] args) throws Exception {
	   final BinaryLogClient client = new BinaryLogClient("localhost", 3306, "root", "root");
		client.registerEventListener(new EventListener() {

		    @Override
		    public void onEvent(Event event) {
		    		//System.out.println(event.getData());
		    	  System.out.println("-------------------------");
		    	   System.out.println(event) ;
		    		String fileName = client.getBinlogFilename() ;
		    		long position = client.getBinlogPosition() ;
		    		//System.out.println(fileName + ":" + position);
		    		System.out.println("-------------------------");
		    }
		});
		client.connect();
	
}
	
	
}
