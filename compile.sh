#! /bin/bash

clear

#Just Some Info That Script Started
echo "Compilation Started..."

#Compile thrift-generated Files
javac -cp "thrift-gen/libs/*" thrift-gen/Log.java
javac -cp "thrift-gen/libs/*" thrift-gen/CreateLogFile.java

#Copy needed Files to Client & Server
cp -r thrift-gen/ ./thrift-server/
cp -r thrift-gen/ ./thrift-client/

#Compile Server
javac -cp "thrift-server/libs/*:thrift-server/thrift-gen:thrift-server/" thrift-server/Server.java

#Compile Client
javac -cp "thrift-client/libs/*:thrift-client/thrift-gen" thrift-client/Client.java

#Compile Kafka Consumer/Server
javac -cp "kafka-consumer/libs/*" kafka-consumer/Consumer.java

#Remove Thrift Generated Files from Project Folder
rm -rf thrift-gen

#Info That Everything Went OK!
echo "Compilation Finished!"
