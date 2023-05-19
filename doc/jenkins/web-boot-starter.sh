export JAVA_HOME=/usr/local/jdk/jdk17/
cd $WORKSPACE/parent/framework/web-boot-starter
mvn clean install -Dmaven.test.skip=true
