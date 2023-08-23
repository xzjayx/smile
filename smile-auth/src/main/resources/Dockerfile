# 基础镜像使用java
FROM openjdk:8-jre
# 作者可要可不要
MAINTAINER xz
# VOLUME 指定临时文件目录为/tmp，在主机/home/docker/auth目录下创建了一个临时文件并链接到容器的/tmp
VOLUME /tmp
# 设置时区
RUN echo "Asia/Shanghai" > /etc/timezone

COPY *.jar /opt/tools/smile-auth.jar

ENTRYPOINT ["java", "-jar", "-Dfile.encoding=UTF-8","/opt/tools/smile-auth.jar", "--spring.profiles.active=prod"]

#暴露8100端口作为微服务
EXPOSE 8100
