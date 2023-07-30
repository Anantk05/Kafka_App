package com.basic;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerApp {

    private static final Logger log = (Logger) LoggerFactory.getLogger(KafkaProducerDemo.class.getSimpleName());

    public static void main(String[] args) {

        String groupId = "my_app";
        String topic = "sec_topic";

        log.info("Starting Your Kafka consumer!");

        //Create Producer Properties
        Properties properties = new Properties();

        //Connect to Localhost
        properties.setProperty("bootstrap.servers","localhost:9092");

        //connect to Conduktor Playground
        properties.setProperty("bootstrap.servers","cluster.playground.cdkt.io:9092");
        properties.setProperty("security.protocol","SASL_SSL");
        properties.setProperty("sasl.jaas.config","org.apache.kafka.common.security.plain.PlainLoginModule required username=\"1GF7GwdKSU8h4BEBD1Rw25\" password=\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiIxR0Y3R3dkS1NVOGg0QkVCRDFSdzI1Iiwib3JnYW5pemF0aW9uSWQiOjc1MjAxLCJ1c2VySWQiOjg3NDk3LCJmb3JFeHBpcmF0aW9uQ2hlY2siOiI4ZjBkZTQ2Ny0xMTM1LTRlYTItYjVmMC0wYjczYWJmOTYxODMifX0.zMHO5ilIxA_xN3VqDOSQPrcQSyIkap4mZCivr5ypALY\";");
        properties.setProperty("sasl.mechanism","PLAIN");


        // create consumer configs
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());

        properties.setProperty("group.id", groupId);

        //properties.setProperty("auto.offset.reset","none/earliest/latest");
        properties.setProperty("auto.offset.reset","earliest");

        // create a consumer
        KafkaConsumer<String ,String > consumer = new KafkaConsumer<>(properties);

        // suscribe to a topic
        consumer.subscribe(Arrays.asList(topic));

        //poll for data
        while (true){
            log.info("Polling");

           ConsumerRecords<String ,String > records =  consumer.poll(Duration.ofMillis(1000));

           for (ConsumerRecord<String ,String > record:records){
               log.info("key: " + record.key() + ", Value: " + record.value());
               log.info("Partition: " + record.partition() + ", Offset: " + record.offset());
           }
        }
    }
}
