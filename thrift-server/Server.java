import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.transport.TTransportException;

public class Server {

	private void start() {
        	try {
            		// Set Port
            		TServerSocket serverTransport = new TServerSocket(7911);

            		// Set Handler for the Calls
            		CreateLogHandler handler = new CreateLogHandler();
            		CreateLogFile.Processor<CreateLogFile.Iface> processor = new CreateLogFile.Processor<CreateLogFile.Iface>(handler);

            		TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

			//Just Some Info to See Everything is OK
            		System.out.println("Server Started on Port 7911");
            		server.serve();

        	}

		catch (TTransportException e) {
			e.printStackTrace();
        	}
    	}

	public static void main( String[] args ) {

		//Create and Start Server
		Server server = new Server();
        	server.start();
    	}
}

