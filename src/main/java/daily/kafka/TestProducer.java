package daily.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class TestProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers","linux1:9092");
		props.put("acks", "1");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("partitioner.class", "kafka.SimplePartitioner");


		String topic = "test" ;
		
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
		for (int i = 0; i < 1000; i++) {
			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,i + "",
					"value" + i%3);
			producer.send(record, new Callback() {
				public void onCompletion(RecordMetadata metadata, Exception e) {
					if (e != null)
						e.printStackTrace();
					System.out.println("The offset of the record we just sent is: " + metadata.offset());
				}
			});
		}

		producer.close();

	}
	
	
	
}
