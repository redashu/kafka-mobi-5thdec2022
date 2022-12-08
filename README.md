# Training plan 

<img src="plan.png">

### understanding kafka producer and consumers usign SDK 

<img src="sdk1.png">


### check kafka library support for python 

```
[ashu@producer ~]$ pip3 list  |   grep kafka
[ashu@producer ~]$ pip3 install kafka-python --user 
Collecting kafka-python
  Downloading kafka_python-2.0.2-py2.py3-none-any.whl (246 kB)
     |████████████████████████████████| 246 kB 32.5 MB/s 
Installing collected packages: kafka-python
Successfully installed kafka-python-2.0.2
[ashu@producer ~]$ pip3 list  |   grep kafka
kafka-python      2.0.2
[ashu@producer ~]$ 
```

### lets verify installation 

```
ashu@producer ~]$ python3
Python 3.7.15 (default, Oct 31 2022, 22:44:31) 
[GCC 7.3.1 20180712 (Red Hat 7.3.1-15)] on linux
Type "help", "copyright", "credits" or "license" for more information.
>>> import kafka 
>>> dir(kafka)
['BrokerConnection', 'ConsumerRebalanceListener', 'Deserializer', 'KafkaAdminClient', 'KafkaClient', 'KafkaConsumer', 'KafkaProducer', 'NullHandler', 'OffsetAndMetadata', 'Serializer', 'TopicPartition', '__all__', '__author__', '__builtins__', '__cached__', '__copyright__', '__doc__', '__file__', '__license__', '__loader__', '__name__', '__package__', '__path__', '__spec__', '__title__', '__version__', 'absolute_import', 'admin', 'client_async', 'cluster', 'codec', 'conn', 'consumer', 'coordinator', 'errors', 'future', 'logging', 'metrics', 'oauth', 'partitioner', 'producer', 'protocol', 'record', 'scram', 'serializer', 'structs', 'util', 'vendor', 'version']
>>> 
```

### lets write producer in python 

```
#!/usr/bin/python3

# importing kafka consumer library 
from kafka import KafkaProducer
# what kafkaproducer offer
my_producer=KafkaProducer(bootstrap_servers='ip-172-31-7-200.ec2.internal:9092')
# using above stored object i will be sending message 
send_status=my_producer.send('ashu-python-topic1','Hey i am ashutoshh ')
print(send_status)


```

### error while running above code 

<img src="error.png">

### improve code 

```
#!/usr/bin/python3

# importing kafka consumer library 
from kafka import KafkaProducer
# what kafkaproducer offer
my_producer=KafkaProducer(bootstrap_servers='ip-172-31-7-200.ec2.internal:9092')
# using above stored object i will be sending message 
send_status=my_producer.send('ashu-python-topic1',b'Hey i am ashutoshh ')
send_status.get(timeout=10)
my_producer.flush()
print(send_status)


```

###  more change

```
#!/usr/bin/python3

# importing kafka consumer library 
from kafka import KafkaProducer
# what kafkaproducer offer
my_producer=KafkaProducer(bootstrap_servers='ip-172-31-7-200.ec2.internal:9092',compression_type='gzip')
# compression message to latency will improve 
# using above stored object i will be sending message 
for i in range(10):
    
    send_status=my_producer.send('ashu-python-topic1',b'hey i am ashu %d'%i)
    send_status.get(timeout=10)
    my_producer.flush()
    print(send_status)


```

### getting more details 

```
#!/usr/bin/python3

# importing kafka consumer library 
from kafka import KafkaProducer
import time
# what kafkaproducer offer
my_producer=KafkaProducer(bootstrap_servers='ip-172-31-7-200.ec2.internal:9092',compression_type='gzip',key_serializer=str.encode)
# compression message to latency will improve 
# using above stored object i will be sending message 
for i in range(10):
    
    send_status1=my_producer.send('ashu-python-topic1',key='1234',value=b'hey i am ashu %d'%i)
    send_status2=my_producer.send('ashu-python-topic1',key='007',value=b'hey i am drone %d'%i)
    result1=send_status1.get(timeout=10)
    result2=send_status2.get(timeout=11)
    print("__________________________________")
    print(result1.topic,result1.partition,result1.offset)
    time.sleep(1)
    print("______________")
    print(result2.topic,result2.partition,result2.offset)
    
    my_producer.flush()



```

### COnsumer group 

<img src="cg.png">

## Performance Tunning is required 

## OS point of view 

### setting KAFKA_HEAP_Size

```
export KAFKA_HEAP_OPTS="-Xmx4g"
```

<p> remaining RAM will be considered as os pagging cache </p>

<img src="ost.png">

### tunning parameter for kafka server 

```
log.dirs=/var/log/kafka-logs
log.retention.hours=170
offsets.retention.minutes=1000
num.partitions=5
default.replication=2
delete.topic.enable=false
message.max.bytes=1048576
```

### topic level tunning options 

```
--config <String: name=value>            A topic configuration override for the 
                                           topic being created or altered. The  
                                           following is a list of valid         
                                           configurations:                      
                                         	cleanup.policy                        
                                         	compression.type                      
                                         	delete.retention.ms                   
                                         	file.delete.delay.ms                  
                                         	flush.messages                        
                                         	flush.ms                              
                                         	follower.replication.throttled.       
                                           replicas                             
                                         	index.interval.bytes                  
                                         	leader.replication.throttled.replicas 
                                         	local.retention.bytes                 
                                         	local.retention.ms                    
                                         	max.compaction.lag.ms                 
                                         	max.message.bytes                     
                                         	message.downconversion.enable         
                                         	message.format.version                
                                         	message.timestamp.difference.max.ms   
                                         	message.timestamp.type                
                                         	min.cleanable.dirty.ratio             
                                         	min.compaction.lag.ms                 
                                         	min.insync.replicas                   
                                         	preallocate                           
                                         	remote.storage.enable                 
                                         	retention.bytes                       
                                         	retention.ms                          
                                         	segment.bytes                         
                                         	segment.index.bytes                   
                                         	segment.jitter.ms                     
                                         	segment.ms                            
                                         	unclean.leader.election.enable 
```

