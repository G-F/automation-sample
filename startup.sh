#! /bin/bash

export M3_HOME=/opt/apache-maven-3.2.3
M3=$M3_HOME/bin
export PATH=$M3:$PATH

mvn -version
cd /vagrant

mvn install package -Dtest.skip=true
mvn dependency:copy-dependencies

cp /vagrant/target/selenium-sample-0.0.1-SNAPSHOT.jar /opt/spark/sample.jar
cp -pr /vagrant/target/dependency /opt/spark/

cd /opt/spark/
java -jar /opt/spark/sample.jar &
