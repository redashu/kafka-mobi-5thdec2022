# Training plan 

<img src="plan.png">

## Understanding a big application and its connects/integratios --

### Introduction apache kafka

<img src="intro.png">

### more about apache kafka

<img src="info.png">


## kafka store message data inside -- topics -- topics are having partitions 

### pic 1
<img src="topic1.png">

### pic 2 

<img src="topic2.png">

### pic 3

<img src="topic3.png">


## Labs for kafka 

<img src="labs.png">

## aws cloud vm login -- from mac book 

```
fire@ashutoshhs-MacBook-Air ~ % cd Downloads
fire@ashutoshhs-MacBook-Air Downloads % 
fire@ashutoshhs-MacBook-Air Downloads % chmod 400 ashu-kafka-key.pem 
fire@ashutoshhs-MacBook-Air Downloads % 
fire@ashutoshhs-MacBook-Air Downloads % ssh -i ashu-kafka-key.pem   ec2-user@54.237.41.75
The authenticity of host '54.237.41.75 (54.237.41.75)' can't be established.
ECDSA key fingerprint is SHA256:c7g/IfkPqUb8hkvKq6ymxYkojvlxAzxxuIrDvcqZHTU.
Are you sure you want to continue connecting (yes/no/[fingerprint])? yes
Warning: Permanently added '54.237.41.75' (ECDSA) to the list of known hosts.

       __|  __|_  )
       _|  (     /   Amazon Linux 2 AMI
      ___|\___|___|

https://aws.amazon.com/amazon-linux-2/
1 package(s) needed for security, out of 1 available
Run "sudo yum update" to apply all updates.
-bash: warning: setlocale: LC_CTYPE: cannot change locale (UTF-8): No such file or directory
[ec2-user@ip-172-31-56-93 ~]$ 
[ec2-user@ip-172-31-56-93 ~]$ 
[ec2-user@ip-172-31-56-93 ~]$ 
```


## Kafka Pre-requisite : 

### jdk 11 & scala 

### Install jdk11 in linux vm on amazon cloud 
### using amazon corretto to install jdk in any platform 

```
sudo amazon-linux-extras install java-openjdk11
```

### lets verify 

```
ec2-user@ip-172-31-56-93 ~]$ java -version 
openjdk version "11.0.16" 2022-07-19 LTS
OpenJDK Runtime Environment (Red_Hat-11.0.16.0.8-1.amzn2.0.1) (build 11.0.16+8-LTS)
OpenJDK 64-Bit Server VM (Red_Hat-11.0.16.0.8-1.amzn2.0.1) (build 11.0.16+8-LTS, mixed mode, sharing)
```




