package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world! , welcome to Kafka");

        // create producer properties
        Properties pro = new Properties();
        pro.setProperty("bootstrap.servers","ec2-54-243-223-21.compute-1.amazonaws.com:9092");
        pro.setProperty("key.serializer", StringSerializer.class.getName());
        pro.setProperty("value.serializer",StringSerializer.class.getName());
        // create producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(pro);
        // create message
        ProducerRecord<String , String>  producerRecord = new ProducerRecord<>("ashu-data1","Hello world");
        // send message
        producer.send(producerRecord);
        producer.flush();
        //  close producer
        producer.close();
    }
}