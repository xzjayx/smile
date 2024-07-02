## 启动
### 环境配置 docs文件下
1. 首先启动Nacos server注册中心 1.4.2 默认账户密码Nacos
2. 网关和鉴权等等需要用户的role-->资源url 需要启动redis,用于auth授权服务器 gateway网关资源服务器
3. 安装MYSQL数据库，这里是直接安装之后会用docker方式安装部署
    + 安装Linux Centos7环境Vagrant    https://xzjayx.github.io/post/ef04e708.html
    + 安装Linux 环境下的MySQL  https://xzjayx.github.io/post/fc8f90a3.html
    建议用docker直接导入镜像安装 见/docs/docker.md，也可以用docker-compose安装Mysql，redis等基础依赖
4. 依次用docker安装MySQL,Redis,并且配置相关DB
    