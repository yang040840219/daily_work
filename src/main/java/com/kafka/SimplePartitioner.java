package com.kafka;

import java.util.List;
import java.util.Random;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.internals.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;


public class SimplePartitioner extends Partitioner  {
	
	Random random = new Random();

	@Override
	public int partition(ProducerRecord<byte[], byte[]> record, Cluster cluster) {
		  List<PartitionInfo> partitions = cluster.partitionsForTopic(record.topic());
		  return random.nextInt(partitions.size()-1);
		  
	}
	

}
