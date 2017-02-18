This is the final version of the project written in Java.

Technologies used:

1. JDK : 1.8

2. Thrift : 0.10.0

3. Kafka : 0.10.1.0

4. Cassandra : 2.2.8

5. Shell Bash 4

In the main folder there is all the source code of the project. 

File **logs.thrift** describes Log schema and is used to create files in the thrift-gen folder(Already Created)

Folder **thrift-gen** includes all generated files by thrift.

Folder **thrift-client** includes Java Class Client which connects to Server and sends random logs to it. Also it includes a bash script to run the Thrift Client.

Folder **thrift-server** includes Java Class Server, CreateLogHandler and Producer, every class needed in order to receive logs and store them in Kafka. Also there is a bash file to run Thrift Server.

Folder **kafka-consumer** includes Java Class Consumer which reads logs from Kafka and stores them in Cassandra. It also includes a bash file to run Kafka Consumer Server

In the folder **cassandra-schema** there is a bash script which creates the recquired keyspace and table for Cassandra (pollfish, log_files) in order to make things easy.

There is also a **compile.sh* bash file.

In order to create the binary folders it is important to make compile.sh executable and run it:
**chmod u+x compile.sh
bash compile.sh**

After this in the project folder there will be left **thrift-client**, **thrift-server** and **kafka-consumer** folders together with logs.thrift file and cassandra-schema folder.

Folders **thrift-client**, **thrift-server**, and **kafka-consumer** include everything needed to execute them.

In order to execute each one, cd in the folder and run the bash file **start.sh** in the way described before.

In order for everything to work as intented is is import to have zookeper server running, kafka server running running on **topic named "logfile"**, cassandra single node cluste up and running.
