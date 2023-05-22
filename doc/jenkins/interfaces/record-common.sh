export JAVA_HOME=/usr/local/jdk/jdk17/
cd $WORKSPACE/record-service/record-common/
mvn clean install -Dmaven.test.skip=true

### 构建后操作选择触发构建record-interfaces