# docker-compose部署相关
## 1. compose安装MYSQL单机版
mkdir -p /home/smile/mysql8
cd /home/smile/mysql8/
vi docker-compose.yml
```dockerfile
version: '3.8'
services:
  mysql:
    # 使用 MySQL 8.0.28 镜像
    image: mysql:8.0.28
    # 容器名称为 docker_mysql
    container_name: docker_mysql
    # 使用宿主机网络模式
    #network_mode: host
    networks:
      - smile-dev
    # 容器退出时自动重启
    restart: always
    # 防止被OOM kill, -1000为最低优先级
    oom_score_adj: -1000
    environment:
      # 设置 MySQL root 用户的密码为 root
      MYSQL_ROOT_PASSWORD: root
    ports:
      - "3306:3306"
    volumes:
      # 让容器的时钟与宿主机时钟同步，避免时间的问题，ro是read only的意思，就是只读。
      - /etc/localtime:/etc/localtime:ro 
      # 挂载数据目录
      - ./mysql8/data:/var/lib/mysql
      - ./mysql8/mysql-files:/var/lib/mysql-files
      # 挂载配置文件
      - ./my.cnf:/etc/mysql/my.cnf
    command:
      --default-authentication-plugin=mysql_native_password
      # 使用指定的配置文件启动
      --defaults-file=/etc/mysql/my.cnf


---
version: '3.8'
services:
  mysql:
    image: mysql:8.0.28
    container_name: docker_mysql8028
#    networks:
#      - smile-dev
    restart: always
    oom_score_adj: -1000
    environment:
      MYSQL_ROOT_PASSWORD: root
    ports:
      - 3306:3306
    privileged: true
    volumes:
      - /etc/localtime:/etc/localtime:ro 
      - ./mysql8/data:/var/lib/mysql
      - ./mysql8/mysql-files:/var/lib/mysql-files
      - ./my.cnf:/etc/mysql/my.cnf
    command:[
        '--defaults-file=./my.cnf',
        '--default-authentication-plugin=mysql_native_password'
    ]
#networks:
#  smile-dev:
#    driver: bridge
```

接着创建my.cnf  vi my.cnf
```text


[mysql]
# 默认字符集
default-character-set=utf8mb4
[client]
# 客户端使用的端口号
port=3306
# 客户端连接的 socket 路径
socket=/var/run/mysqld/mysqld.sock
[mysqld]
# 限制 MySQL 服务器只能从 /var/lib/mysql-files 目录读取文件或将文件写入该目录
secure-file-priv=/var/lib/mysql-files
# 使用主机名进行缓存查找，以提高连接性能
skip-host-cache
# 进行权限验证时，会尝试将客户端的主机名解析为 IP 地址
skip-name-resolve
# 服务端使用的端口号
port=3306
# MySQL 运行用户
user=mysql
# 服务器 ID
server-id=1
# 日志时间系统时间
log_timestamps=SYSTEM
# 默认时区东八区
default-time_zone='+8:00'
# 默认使用“mysql_native_password”插件认证
default_authentication_plugin=mysql_native_password
# 服务器连接的 socket 路径
socket=/var/run/mysqld/mysqld.sock
# 数据存放目录
datadir=/var/lib/mysql
# 开启二进制日志功能
log-bin=/var/lib/mysql/mysql-bin
# InnoDB 数据文件存放目录
innodb_data_home_dir=/var/lib/mysql
# InnoDB 日志文件存放目录
innodb_log_group_home_dir=/var/lib/mysql
# MySQL 错误日志文件路径
log-error=/var/lib/mysql/mysql.log
# 存放 MySQL 进程 ID 的文件路径
pid-file=/var/lib/mysql/mysql.pid
# 表名大小写不敏感
lower_case_table_names=1
# 服务端字符集
character-set-server=utf8mb4
# 自动提交所有事务
autocommit=1
# 跳过排它锁定
skip-external-locking
# 键缓存大小
key_buffer_size=64M
# 允许的最大数据包大小
max_allowed_packet=16M
# 表缓存
table_open_cache=6000
# 排序缓存大小
sort_buffer_size=16M
# 网络缓冲区长度
net_buffer_length=32K
# 读取缓冲区大小
read_buffer_size=16M
# 随机读取缓冲区大小
read_rnd_buffer_size=1024K
# MyISAM 排序缓冲区大小
myisam_sort_buffer_size=265M
# 线程缓存大小
thread_cache_size=512
# 临时表大小
tmp_table_size=512M
# 启用显式默认时间戳
explicit_defaults_for_timestamp=ON
# 最大连接数
max_connections=3000
# 连接错误最大数量
max_connect_errors=100
# 打开文件限制
open_files_limit=65535
# 二进制日志格式
binlog_format=mixed
# 二进制日志过期时间（秒）
binlog_expire_logs_seconds=864000
# 创建表时使用的默认存储引擎
default_storage_engine=InnoDB
# InnoDB 数据文件路径设置
innodb_data_file_path=ibdata1:12M:autoextend
# InnoDB 缓冲池大小
innodb_buffer_pool_size=2G
# InnoDB 日志文件大小
innodb_log_file_size=512M
# InnoDB 日志缓冲区大小
innodb_log_buffer_size=16M
# InnoDB 每次提交时刷新日志
innodb_flush_log_at_trx_commit=1
# InnoDB 加锁等待超时时间（秒）
innodb_lock_wait_timeout=60
[mysqldump]
# 快速导出数据
quick
# 允许的最大数据包大小
max_allowed_packet=16M
[myisamchk]
# 键缓存大小
key_buffer_size=64M
# 排序缓冲区大小
sort_buffer_size=16M
# 读取缓冲区大小
read_buffer=8M
# 写入缓冲区大小
write_buffer=8M
[mysqlhotcopy]
# 交互式超时时间llllll
interactive-timeout




```
启动 docker compose up -d
关闭并且删除 docker compose down


## 1. compose安装Redis单机版见有道云笔记
mkdir -p /home/smile/redis
cd /home/smile/redis/