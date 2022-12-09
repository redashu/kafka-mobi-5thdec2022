# Training plan 

<img src="plan.png">

### REvision 

<img src="rev.png">

### kafka with KRAFT 

<img src="kraft.png">

### checking kraft 

```
[root@producer ~]# cd  /opt/kafka_2.13-3.3.1/
[root@producer kafka_2.13-3.3.1]# ls
LICENSE  NOTICE  bin  config  libs  licenses  site-docs
[root@producer kafka_2.13-3.3.1]# cd config/
[root@producer config]# ls
connect-console-sink.properties    connect-file-source.properties   consumer.properties  server.properties
connect-console-source.properties  connect-log4j.properties         kraft                tools-log4j.properties
connect-distributed.properties     connect-mirror-maker.properties  log4j.properties     trogdor.conf
connect-file-sink.properties       connect-standalone.properties    producer.properties  zookeeper.properties
[root@producer config]# cd kraft/
[root@producer kraft]# ls
README.md  broker.properties  controller.properties  server.properties
[root@producer kraft]# 


```

### start kafka without zookeeper -- using kraft 

```
 60  kafka-storage.sh --help
   61  kafka-storage.sh random-uuid 
   62  kafka-storage.sh format -t 63zWeUnUSVKWSW5wdtP9KA  -c /opt/kafka_2.13-3.3.1/config/kraft/server.properties  
   63  kafka-server-start.sh  /opt/kafka_2.13-3.3.1/config/kraft/server.properties 
   64  kafka-server-start.sh -daemon  /opt/kafka_2.13-3.3.1/config/kraft/server.properties 
```

### can stop kafka like before 

```
[root@producer kraft]# kafka-server-stop.sh 
[root@producer kraft]# 
[root@producer kraft]# netstat -nlpt
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name    
tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      3217/sshd           
tcp        0      0 127.0.0.1:25            0.0.0.0:*               LISTEN      3164/master         
tcp        0      0 127.0.0.1:37117         0.0.0.0:*               LISTEN      4621/node           
tcp        0      0 127.0.0.1:35105         0.0.0.0:*               LISTEN      17121/node          
tcp        0      0 127.0.0.1:43905         0.0.0.0:*               LISTEN      5999/node           
tcp        0      0 127.0.0.1:44267         0.0.0.0:*               LISTEN      6563/node           
tcp        0      0 0.0.0.0:111             0.0.0.0:*               LISTEN      2760/rpcbind        
tcp6       0      0 :::22                   :::*                    LISTEN      3217/sshd           
tcp6       0      0 :::111                  :::*                    LISTEN      2760/rpcbind       
```

## configure SSL serts for kafka setup 

### creating self CA 

```
[root@producer ssl-certs]# mkdir  self-ca  zookeeper  kafkabr  kfclients zooclients 
[root@producer ssl-certs]# cd self-ca/
[root@producer self-ca]# openssl req -new -x509 -keyout ca-key -out ca-cert -days 3650
Generating a 2048 bit RSA private key
......+++
.............+++
writing new private key to 'ca-key'
Enter PEM pass phrase:
Verifying - Enter PEM pass phrase:
-----
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) [XX]:IN
State or Province Name (full name) []:RAJ
Locality Name (eg, city) [Default City]:JAI
Organization Name (eg, company) [Default Company Ltd]:adhoc
Organizational Unit Name (eg, section) []:tech
Common Name (eg, your name or your server's hostname) []:localhost
Email Address []:
[root@producer self-ca]# ls
ca-cert  ca-key

```

### create certs for zookeeper server 

```
 keytool -keystore kafka.zookeeper.truststore.jks -alias ca-cert -import -file ../self-ca/ca-cert
 keytool -keystore kafka.zookeeper.keystore.jks -alias zookeeper -validity 3650 -genkey -keyalg RSA -ext SAN=dns:localhost
 keytool -keystore kafka.zookeeper.keystore.jks -alias zookeeper -certreq -file ca-request-zookeeper
 ls
 ca-request-zookeeper
```
### send above listed file to CA for signing 

```
openssl x509 -req -CA ../self-ca/ca-cert -CAkey ../self-ca/ca-key -in ca-request-zookeeper -out ca-signed-zookeeper -days 3650 -CAcreateserial

ls
ca-signed-zookeeper
```

### import ca cert and ca signed cert to zookeeper store 

```
keytool -keystore kafka.zookeeper.keystore.jks -alias ca-cert -import -file ../self-ca/ca-cert
keytool -keystore kafka.zookeeper.keystore.jks -alias zookeeper -import -file ca-signed-zookeeper
```





