### jenkins的源码管理配置大同小异, 不管是gitee还是github都差不多, 主要是构建运行这一步, 这里记录一下

### 构建时需要使用jdk17
export JAVA_HOME=/usr/local/jdk/jdk17/

### 停止之前启动的服务, 通过stop命令停止容器, 并不断循环检测, 避免直接杀死导致其他问题
docker container stop $(docker container ls -aq --filter "name=^$JOB_NAME")

for CONTAINER_ID in $(docker container ls -aq --filter "name=^$JOB_NAME")
do
    while [ "$(docker inspect -f '{{.State.Running}}' $CONTAINER_ID)" = "true" ]; do
        sleep 5;
    done
done

echo "All containers with name starting with '$JOB_NAME' have been stopped."

### 删除之前构建的镜像
docker container rm $(docker container ls -aq --filter "name=^$JOB_NAME")

images=$(docker image ls --filter "reference=$JOB_NAME*" --format "{{.Repository}}:{{.Tag}}")

for image in $images
do
    docker rmi $image
done

echo "All images have been deleted"

cd $WORKSPACE/user-service/user-web/

mvn clean package -Dmaven.test.skip=true

cd $WORKSPACE

### 构建一个新镜像, 并且设置tag

docker build -f $WORKSPACE/doc/docker/$JOB_NAME-dockerfile -t $JOB_NAME:$BUILD_NUMBER .

### 启动容器 1. 指定ip 2. 将日志挂载出来 3. 传递jvm和应用参数

docker run --name $JOB_NAME-$BUILD_NUMBER -p 9001:9001 --network rootnet --ip 172.19.0.6 -v /usr/local/jenkins/user-web/logs/:/data/logs/user-web/ -e JAVA_OPTS="-Xms256m -Xmx512m" -e APP_OPTS="-Dspring.profiles.active=prod -Dspring.cloud.nacos.discovery.server-addr=172.19.0.5 -Dspring.cloud.nacos.discovery.namespace=19a0fa32-ed2e-40f1-a1e1-aae8c81d8cf8" -d $JOB_NAME:$BUILD_NUMBER

echo "This build has been started"