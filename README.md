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

The schema I decided to use is : One table named **log_files** with the following attributes :

1. id (int, Primary Key). An id that will help in incident report (e.g. Take a look at log number 12). In a realistic system it is more easy to examine incident number 12 than examine an incident that happened in 18/02/2017 18:32:45.

2. date (timestamp). The exact date and time the log was produced.

3. ip (text). In a realistic system there are a lot of computers sending logs,so ip helps us see which computer was the one that created the log.

4. message (text). The log text.

5. version (int). Version number for logging event schema.

There is also a **compile.sh** bash file.

In order to create the binary folders it is important to make compile.sh executable and run it:

1. **chmod u+x compile.sh**

2. **bash compile.sh**

After this in the project folder there will be left **thrift-client**, **thrift-server** and **kafka-consumer** folders together with logs.thrift file and cassandra-schema folder.

Folders **thrift-client**, **thrift-server**, and **kafka-consumer** include everything needed to execute them.

In order to execute each one, cd in the folder and run the bash file **start.sh** in the way described before.

In order for everything to work as intented is is import to have zookeeper server running, kafka server running on **topic named "logfiles"**, cassandra single node cluster up and running.
