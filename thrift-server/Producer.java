import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class Producer extends Thread {

	private final KafkaProducer<Integer, String> producer;

	//Kafka Topic
	private final String topic = "logfiles";

	//Constructor for Producer
	public Producer() {
        	Properties props = new Properties();
        	props.put("bootstrap.servers", "localhost:9092");
        	props.put("client.id", "DemoProducer");
        	props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        	props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        	producer = new KafkaProducer<>(props);

    	}

	public void main(String data) {

		try {
                  	producer.send(new ProducerRecord<>(topic, 1 , data)).get();

                }
		catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
                }
	}
}
