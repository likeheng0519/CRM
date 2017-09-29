#!/bin/bash
查看是否已经安装postgresql
sudo rpm -qa | grep postgresql
#删除旧版本postgresql
sudo yum remove postgresql*
#在没有安装postgresql的前提下安装
sudo yum -y install postgresql*
#
#
#初始化后默认postgresql数据库有一个默认的用户postgres（密码为空）和一个默认创建的postgres数据库
sudo service postgresql initdb
#启动数据库
sudo /bin/systemctl start  postgresql.service
#
#切换用户修改密码
sudo postgres
psql
alter  user postgres with password  'conversant' ;
\q
exit
#
#设定postgresql服务等级,开机自动启动
sudo systemctl enable postgresql.service
#
#修改防火墙端口5432,修改[/etc/sysconfig/iptables]
/sbin/iptables -I INPUT -p tcp --dport 5432 -j ACCEPT
sudo service iptables save
sudo /bin/systemctl restart  iptables.service
#查看是否启动成功
sudo netstat -tulnp | grep 5432
#
#
#修改配置
sudo cp /var/lib/pgsql/data/postgresql.conf{,.original}
sudo cp /var/lib/pgsql/data/pg_hba.conf{,.original}
sudo cp /var/lib/pgsql/data/pg_ident.conf{,.original}
sudo sed -i "s/#listen_addresses = 'localhost'/listen_addresses = '*'/" /var/lib/pgsql/data/postgresql.conf
#修改postgre数据库登入验证方式
#把这个配置文件中的认证 METHOD的ident修改为trust，可以实现用账户和密码来访问数据库
#参照[http://www.cnblogs.com/hiloves/archive/2011/08/20/2147043.html],[http://www.linuxidc.com/Linux/2014-09/106772.htm]
#[host    all             all             0.0.0.0/0            trust]
sudo vi /var/lib/pgsql/data/pg_hba.conf
#
#启动数据库
sudo /bin/systemctl restart  postgresql.service
#