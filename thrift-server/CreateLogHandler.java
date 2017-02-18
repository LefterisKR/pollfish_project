import java.util.Random;
import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CreateLogHandler implements CreateLogFile.Iface {

	//State Date Format & Chars of the Random Message
	private static final DateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	//Method to Create our Random Data
	public void create () throws org.apache.thrift.TException {

		//Builders for Message, Randomness and Current Date
		StringBuilder sb = new StringBuilder();
		Random r = new Random(System.currentTimeMillis());
		Date date = new Date();

		//Creating a 100 chars Log Message
		for (int i = 0; i < 100; i++) {
                	char c = chars[r.nextInt(chars.length)];
                        sb.append(c);
		}

		//Creating a Random IP & Adding to Variable
		String ip =  r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);

		//Getting Current System Time & Adding to Variable
		String time = dformat.format(date);

		//Adding Random Message to Variable
		String message = sb.toString();

		//Create Data to Store in Kafka (id, ip, time, message)
		String data = "1, " + ip + ", " + time + ", " + message;

		//Instance of Kafka Producer
		Producer producer = new Producer();
		producer.main(data);

	}
}
