### jenkins的源码管理配置大同小异, 不管是gitee还是github都差不多, 主要是构建运行这一步, 这里记录一下

### step1: 构建时需要使用jdk17
export JAVA_HOME=/usr/local/jdk/jdk17/

cd $WORKSPACE/api/user-api/

mvn clean package -Dmaven.test.skip=true

cd $WORKSPACE

### 构建一个新镜像, 并且设置tag
### $DOCKER_REPOSITORY_URL这个变量自己在系统中设置一下, 可以是阿里云或其他的仓库地址, 且需要在集群执行 docker login

docker build -f $WORKSPACE/doc/docker/$JOB_NAME-dockerfile -t $DOCKER_REPOSITORY_URL$JOB_NAME:$BUILD_NUMBER .

### 推送到仓库

#### 带版本号的先推
docker push $DOCKER_REPOSITORY_URL$JOB_NAME:$BUILD_NUMBER

#### 再推个latest版本的
docker tag $DOCKER_REPOSITORY_URL$JOB_NAME:$BUILD_NUMBER $DOCKER_REPOSITORY_URL$JOB_NAME:latest

docker push $DOCKER_REPOSITORY_URL$JOB_NAME:latest


### 构建后删除镜像

if docker ps -a --format '{{.Names}}' | grep -q "^$JOB_NAME-*"; then
  docker rm $(docker container ls -aq --filter "name=^$JOB_NAME")
fi

images=$(docker image ls --filter "reference=$DOCKER_REPOSITORY_URL$JOB_NAME*" --format "{{.Repository}}:{{.Tag}}")

for image in $images
do
    docker rmi $image
done

echo "All images have been deleted"