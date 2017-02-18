import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

public class Client {

	public static void main(String[] args) {

		TTransport transport;

		try {
			//Connect to Server
            		transport = new TSocket("localhost", 7911);
            		transport.open();
			System.out.println("Connected to Server in Port 7911");

            		TProtocol protocol = new TBinaryProtocol(transport);
            		CreateLogFile.Client client = new CreateLogFile.Client(protocol);

			//Client calls CreateLogFIle Method from Server and Exits Application
            		client.create();
            		transport.close();
        	}

		catch (TTransportException e) {
            		e.printStackTrace();
        	}

		catch (TException e) {
            		e.printStackTrace();
        	}
    	}
}

