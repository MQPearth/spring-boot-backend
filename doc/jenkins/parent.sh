export JAVA_HOME=/usr/local/jdk/jdk17/
cd $WORKSPACE/parent/
mvn clean install -Dmaven.test.skip=true
