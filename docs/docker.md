## docker开发环境搭建
本文档是记录docker初步开发环境的搭建，相关Dockerfile和Docker compose搭建为后续启动，如果不用docker搭建也可以直接本地安装启动或者LINUX搭建也可以。

### 1 Windows下创建Centos7环境
    https://xzjayx.github.io/post/ef04e708.html
### 2 在Centos7下安装docker
    1. 查看centos7 版本 cat /etc/redhat-release
    2. 卸载之前的旧版本（如有）
        2.1 sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
        2.2 yum remove docker-ce docker-ce-cli containerd.io
            rm -rf /var/lib/docker
            rm -rf /var/lib/containerd
        2.3 删除安装包 yum remove docker-ce   删除镜像，容器，配置文件等内容 rm -rf /var/lib/docker
        2.4 可以先find -name docker   find / -name docker 然后删除相关docker https://blog.csdn.net/wangerrong/article/details/126750198
    3. 安装docker
        3.1 需要先安装依赖  yum -y install gcc   &&   yum -y install gcc-c++  &&  yum install -y yum-utils
        3.2 设置stable镜像仓库，默认是dockerhub官方镜像仓库，但有能网速不行这里可修改为阿里云镜像(可选)
            yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo #官方镜像
            yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo # aliyun镜像
            yum makecache fast  # 更新yum软件包索引  
        3.3 安装docker-ce   yum -y install docker-ce docker-ce-cli containerd.io  这一步有可能出错  
            1:docker-ce-cli-20.10.20-3.el7.x86_64: [Errno 256] No more mirrors to try 运行下面命令可解决
            2:yum clean all &&  yum makecache  然后再重新运用上面代码
    4. 配置docker开机自启
        systemctl enable docker ##开机启动
        systemctl list-unit-files|grep enabled  ##查看有多少开启自启动项： 
### 3.docker自定义网络
    @see https://www.yiibai.com/docker/network_create.html
    docker自定义一个bridge的网络，为下面相关容器都在新建的网络下允许 
    docker network create -d bridge smile-dev
     
### 4.docker安装Redis(6.0.8)
    1. docker pull redis:6.0.8    拉取redis镜像
    2. mkdir /home/redis    创建文件夹为了被挂载配置互通有无
    3. 进入第二步的文件路径下创建一个默认redis的配置文件，这样这边改了容器直接生效（重启）
        vi redis.conf 复制/docs/redis_6.0.8.conf文件的内容
    4. 可以修改一些Redis默认的配置,后续如果想给改redis直接修改配置文件重启即可
        4.1 (可选) 开启Redis密码 requirepass 123 
        4.2 (必须) 允许redis外客户端连接  注释掉 # bind 127.0.0.1 
        4.3 (必须) daemonize no，将daemonize yes注释起来或者 daemonize no设置，因为该配置和docker run中-d参数冲突，会导致容器一直启动失败
        4.4 (可选) 开启redis数据持久化  appendonly yes 
    5. docker run  -p 6379:6379 --network smile-dev --restart=always --name redis_6.0.8 --privileged=true  \
        -v /home/redis/redis.conf:/etc/redis/redis.conf  \
        -v /home/redis/data:/data  \
        -d redis:6.0.8 redis-server /etc/redis/redis.conf
### 5.docker安装MySQL(8.0.31)
    1. docker pull mysql:${version} 如果不指定版本默认是最新版 
    2. 通过镜像Run容器container 
        docker run -d -p 3306:3306 --network smile-dev --restart=always --privileged=true  \ 
        -v /home/mysql/log:/var/log/mysql  -v /home/mysql/data:/var/lib/mysql  \  
        -v /home/mysql/conf:/etc/mysql/conf.d  -e MYSQL_ROOT_PASSWORD=root  \ 
        --name mysql_8.0.31 mysql:8.0.31
    3. 进入容器内部 docker exec -it mysql_8.0.31 /bin/bash
        mysql -u root -p  输入上述root设置密码 use mysql  之后修改密码和设置远程连接
        ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '1qaz2w@!';
        FLUSH PRIVILEGES;
        ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '1qaz2wsx@!';
    4. （可选）由于上述第二部已经挂载了磁盘/home/mysql/conf，如果后续需要定制化MySQL可以新建 my.cnf文件和docker的Mysql容器互通有无。
### 6.docker安装Nacos(1.4.2)Standalon模式dev环境
    @see https://blog.csdn.net/weixin_37701609/article/details/126641535
    1. docker search nacos
    2. docker pull nacos/nacos-server 1.4.2
    3. 创建外部容器目录挂载  mkdir -p /home/nacos
    4. 在第三步骤下进入目录下新建application.properties 并且复制/docs/nacos_1.4.2.properties文件的内容
    5. docker run --name nacos1.4.2 --network smile-dev  --env MODE=standalon --privileged=true  \ 
        -v /home/nacos/application.properties:/home/nacos/conf/application.properties   \ 
        --restart=always -d -p 8848:8848 nacos/nacos-server:1.4.2

    
    