import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class Cassandra_Schema {

	//Create Instances
	Session session;
	Cluster cluster;

	//Function to Connect to Cassandra
	public void connect() {
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		session = cluster.connect();
    	}

	//Function to Create Keyspace & Table
	public void createSchema() {
		//Create the Keyspace
		session.execute("CREATE KEYSPACE pollfish  WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 3 };");

		//Create Table
		session.execute("CREATE TABLE pollfish.log_files (id int PRIMARY KEY, version int, ip text, date timestamp, message text);");
	}

	//Main Function
	public static void main(String[] args) {
		Cassandra_Schema schema = new Cassandra_Schema();
		schema.connect();

		schema.createSchema();
		System.out.println("Keyspace & Table Created!");
		System.exit(0);
	}

}
