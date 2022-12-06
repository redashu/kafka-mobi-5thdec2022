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
