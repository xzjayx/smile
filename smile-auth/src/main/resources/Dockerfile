# 基础镜像使用java
FROM java:8
# 作者
MAINTAINER xz
# VOLUME 指定临时文件目录为/tmp，在主机/home/docker/auth目录下创建了一个临时文件并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为zzyy_docker.jar
ADD smile-auth-1.0-SNAPSHOT.jar smile-auth.jar
# 运行jar包
RUN bash -c 'touch /smile-auth.jar'
ENTRYPOINT ["java","-jar","/smile-auth.jar"]
#暴露8100端口作为微服务
EXPOSE 8100
