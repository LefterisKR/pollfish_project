import java.util.*;

import java.io.IOException;
import java.io.InputStream;

import java.lang.String;

import com.datastax.driver.core.*;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.errors.WakeupException;

public class Consumer implements Runnable {

	//Create Instances of Cassandra
	Cluster cluster;
	Session session;

	private KafkaConsumer<String, String> consumer;

	//Consumer Constructor & Properties
	public Consumer() {

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("zookeeper.connect", "localhost:2181");
		props.put("group.id", "logs");
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());

		this.consumer = new KafkaConsumer<>(props);
	}

	//Run Function
	@Override
	public void run() {

    		try {
			//Kafka Topic
			consumer.subscribe(Arrays.asList("logfiles"));

      			while (true) {

				//Consume Logs from Kafka
        			ConsumerRecords<String, String> records = consumer.poll(10);
        			for (ConsumerRecord<String, String> record : records) {

					//Break Result in Variables to Store
					String[] parts = record.value().split(", ");
					String version = parts[0];
					String ip = parts[1];
					String date = parts[2];
					String message = parts[3];

					// Connect to the Cluster and Keyspace pollfish
        				cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        				String keyspace = "pollfish";
					session = cluster.connect(keyspace);

					//Query to Insert into Cassandra
        				session.execute("INSERT INTO log_files (id, date, ip, message, version) VALUES (" + record.offset() + ", '" + date + "' ,'" + ip + "' ,'" + message + "' ," + version + ")" );
        			}
			}
    		}

		finally {
      			consumer.close();
    		}
  	}

	public void shutdown() {
		consumer.wakeup();
  	}

	public static void main(String[] args) {
        	Consumer consumer = new Consumer();
        	consumer.run();
        }

}
