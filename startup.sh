#! /bin/bash
cp /vagrant/target/selenium-sample-0.0.1-SNAPSHOT.jar /opt/spark/sample.jar
cp -pr /vagrant/target/dependency /opt/spark/

cd /opt/spark/
java -jar /opt/spark/sample.jar &