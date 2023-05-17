export JAVA_HOME=/usr/local/jdk/jdk17/
cd $WORKSPACE/user-service/user-common/
mvn clean install -Dmaven.test.skip=true

### 构建后操作选择触发构建user-interfaces