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

### Downloading kafka 3.3 with scala 2.13 support 

[linkto_download](https://kafka.apache.org/downloads)

```
ec2-user@ip-172-31-56-93 ~]$ wget  https://downloads.apache.org/kafka/3.3.1/kafka_2.13-3.3.1.tgz
--2022-12-05 13:20:25--  https://downloads.apache.org/kafka/3.3.1/kafka_2.13-3.3.1.tgz
Resolving downloads.apache.org (downloads.apache.org)... 135.181.214.104, 88.99.95.219, 2a01:4f8:10a:201a::2, ...
Connecting to downloads.apache.org (downloads.apache.org)|135.181.214.104|:443... connected.
HTTP request sent, awaiting response... 200 OK
Length: 105053134 (100M) [application/x-gzip]
Saving to: ‘kafka_2.13-3.3.1.tgz’

100%[============================================================================================================================>] 105,053,134 14.6MB/s   in 7.6s   

2022-12-05 13:20:33 (13.1 MB/s) - ‘kafka_2.13-3.3.1.tgz’ saved [105053134/105053134]

[ec2-user@ip-172-31-56-93 ~]$ ls
kafka_2.13-3.3.1.tgz
```

### extract it 

```
 tar  xvzf  kafka_2.13-3.3.1.tgz 
 ec2-user@ip-172-31-56-93 ~]$ ls
kafka_2.13-3.3.1  kafka_2.13-3.3.1.tgz
```

### lets explore 

```
[ec2-user@ip-172-31-56-93 ~]$ cd  kafka_2.13-3.3.1/
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ ls
bin  config  libs  LICENSE  licenses  NOTICE  site-docs
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ 
```

### Understanding configuration directory 

<img src="conf.png">

### checking bin directory 

```
ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ ls bin/
connect-distributed.sh        kafka-consumer-perf-test.sh  kafka-mirror-maker.sh               kafka-transactions.sh
connect-mirror-maker.sh       kafka-delegation-tokens.sh   kafka-producer-perf-test.sh         kafka-verifiable-consumer.sh
connect-standalone.sh         kafka-delete-records.sh      kafka-reassign-partitions.sh        kafka-verifiable-producer.sh
kafka-acls.sh                 kafka-dump-log.sh            kafka-replica-verification.sh       trogdor.sh
kafka-broker-api-versions.sh  kafka-features.sh            kafka-run-class.sh                  windows
kafka-cluster.sh              kafka-get-offsets.sh         kafka-server-start.sh               zookeeper-security-migration.sh
kafka-configs.sh              kafka-leader-election.sh     kafka-server-stop.sh                zookeeper-server-start.sh
kafka-console-consumer.sh     kafka-log-dirs.sh            kafka-storage.sh                    zookeeper-server-stop.sh
kafka-console-producer.sh     kafka-metadata-quorum.sh     kafka-streams-application-reset.sh  zookeeper-shell.sh
kafka-consumer-groups.sh      kafka-metadata-shell.sh      kafka-topics.sh
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ 

```

### start zookeeper 

```
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ ls
bin  config  libs  LICENSE  licenses  NOTICE  site-docs
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ ./bin/zookeeper-server-start.sh   
USAGE: ./bin/zookeeper-server-start.sh [-daemon] zookeeper.properties
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ ./bin/zookeeper-server-start.sh   config/zookeeper.properties 
[2022-12-05 13:39:12,260] INFO Reading configuration from: config/zookeeper.properties (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-12-05 13:39:12,271] WARN config/zookeeper.properties is relative. Prepend ./ to indicate that you're sure! (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-12-05 13:39:12,283] INFO clientPortAddress is 0.0.0.0:2181 (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-12-05 13:39:12,284] INFO secureClientPort is not set (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-12-05 13:39:12,284] INFO observerMasterPort is not set (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-12-05 13:39:12,285] IN
```

### in another termianl start kafka as well

```
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ ls
bin  config  libs  LICENSE  licenses  logs  NOTICE  site-docs
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ ./bin/kafka-
kafka-acls.sh                       kafka-dump-log.sh                   kafka-replica-verification.sh
kafka-broker-api-versions.sh        kafka-features.sh                   kafka-run-class.sh
kafka-cluster.sh                    kafka-get-offsets.sh                kafka-server-start.sh
kafka-configs.sh                    kafka-leader-election.sh            kafka-server-stop.sh
kafka-console-consumer.sh           kafka-log-dirs.sh                   kafka-storage.sh
kafka-console-producer.sh           kafka-metadata-quorum.sh            kafka-streams-application-reset.sh
kafka-consumer-groups.sh            kafka-metadata-shell.sh             kafka-topics.sh
kafka-consumer-perf-test.sh         kafka-mirror-maker.sh               kafka-transactions.sh
kafka-delegation-tokens.sh          kafka-producer-perf-test.sh         kafka-verifiable-consumer.sh
kafka-delete-records.sh             kafka-reassign-partitions.sh        kafka-verifiable-producer.sh
[ec2-user@ip-172-31-56-93 kafka_2.13-3.3.1]$ ./bin/kafka-server-start.sh    config/server.properties 
[2022-12-05 13:41:20,934] INFO Registered kafka:type=kafka.Log4jController MBean (kafka.utils.Log4jControllerRegistration$)
[2022-12-05 13:41:21,480] INFO Setting -D jdk.tls.rejectClientInitiatedRenegotiation=true to disable client-initiated TLS renegotiation (org.apache.zookeeper.common.X509Util)
[2022-12-05 13:41:21,684] INFO Registered signal handlers for TERM, INT, HUP (org.apache.kafka.common.utils.LoggingSignalHandler)
[2022-12-05 13:41:21,688] INFO starting (kafka.server.KafkaServer)
[2022-12-05 13:41:21,689] INFO Connecting to zookeeper on localhost:2181 (kafka.server.KafkaServer)
[2022-12-05 13:41:21,737] INFO [ZooKeeperClient Kafka server] Initializing a new session to localhost:2181. (kafka.zookeeper.ZooKeeperClient)
[2022-12-05 13:41:21,750] INFO Client environment:zookeeper.version=3.6.3--6401e4ad2087061bc6b9f80dec2d69f2e3c8660a, built on 04/08/2021 16:35 GMT (org.apache.zookeeper.ZooKeeper)
[2022-12-05 13:41:21,751] INFO Client environment:host.na
```
