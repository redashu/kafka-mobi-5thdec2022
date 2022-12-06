# Training plan 

<img src="plan.png">

## the best way to start single node / zookeeper + kafka setup

### adding PATH to shell env for kafka directory 

```
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ ls
bin  config  libs  LICENSE  licenses  NOTICE  site-docs
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ pwd
/home/ec2-user/kafka_2.13-3.3.1
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ cp  ~/.bashrc  . 
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ cp  .bashrc   ~/.bashrc 
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ cat ~/.bashrc 
# .bashrc

# Source global definitions
if [ -f /etc/bashrc ]; then
        . /etc/bashrc
fi

# Uncomment the following line if you don't like systemctl's auto-paging feature:
# export SYSTEMD_PAGER=

# User specific aliases and functions
KAFKA_HOME=/home/ec2-user/kafka_2.13-3.3.1
PATH=$PATH:$KAFKA_HOME/bin
export PATH 
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ source  ~/.bashrc 
```
### making service persistent on boot of system 

```
cat /etc/rc.local
/home/ec2-user/kafka_2.13-3.3.1/bin/zookeeper-server-start.sh  -daemon /home/ec2-user/kafka_2.13-3.3.1/config/zookeeper.properties
sleep 2 
/home/ec2-user/kafka_2.13-3.3.1/bin/kafka-server-start.sh  -daemon /home/ec2-user/kafka_2.13-3.3.1/config/server.properties
---
sudo chmod +x /etc/rc.local
```


### Understanding zookeeper configuration file 

### changing configuration details 

```

# the directory where the snapshot is stored.
dataDir=/var/lib/zookeeper
# the port at which the clients will connect
clientPort=2181
# disable the per-ip limit on the number of connections since this is a non-production config
maxClientCnxns=0
# Disable the adminserver by default to avoid port conflicts.
# Set the port to something non-conflicting if choosing to enable this
admin.enableServer=false
# admin.serverPort=8080

```

### stop and start 

```
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ ls
bin  config  libs  LICENSE  licenses  logs  NOTICE  rc.local  site-docs
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ sudo ./bin/zookeeper-server-stop.sh 
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ 
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ sudo ./bin/zookeeper-server-start.sh  -daemon  config/zookeeper.properties 
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ 

```
### lets verify 

```
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ sudo netstat -nlpt
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name    
tcp        0      0 127.0.0.1:38593         0.0.0.0:*               LISTEN      5535/node           
tcp        0      0 0.0.0.0:111             0.0.0.0:*               LISTEN      2600/rpcbind        
tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      3404/sshd           
tcp        0      0 127.0.0.1:25            0.0.0.0:*               LISTEN      3231/master         
tcp6       0      0 :::41919                :::*                    LISTEN      28369/java          
tcp6       0      0 :::2181                 :::*                    LISTEN      28369/java          
tcp6       0      0 :::111                  :::*                    LISTEN      2600/rpcbind        
tcp6       0      0 :::22                   :::*                    LISTEN      3404/sshd           
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ ls  /var/lib/
alternatives  chrony  dhclient  hibinit-agent  machines  nfs        postfix  rpm-state  systemd      yum
amazon        cloud   games     initramfs      misc      os-prober  rpcbind  rsyslog    update-motd  zookeeper
authconfig    dbus    gssproxy  logrotate      mlocate   plymouth   rpm      stateless  xfsdump
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ ls  /var/lib/zookeeper/
version-2
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ ls  /var/lib/zookeeper/version-2/
snapshot.0
```


