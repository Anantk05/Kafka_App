package com.basic;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class KafkaProducerDemo<S, S1> {

    private static final Logger log = (Logger) LoggerFactory.getLogger(KafkaProducerDemo.class.getSimpleName());

    public static void main(String[] args) {

        log.info("Starting Your Kafka producer!");

        //Create Producer Properties
        Properties properties = new Properties();

        //Connect to Localhost
        properties.setProperty("bootstrap.servers","localhost:9092");

        //connect to Conduktor Playground
        properties.setProperty("bootstrap.servers","cluster.playground.cdkt.io:9092");
        properties.setProperty("security.protocol","SASL_SSL");
        properties.setProperty("sasl.jaas.config","org.apache.kafka.common.security.plain.PlainLoginModule required username=\"1GF7GwdKSU8h4BEBD1Rw25\" password=\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiIxR0Y3R3dkS1NVOGg0QkVCRDFSdzI1Iiwib3JnYW5pemF0aW9uSWQiOjc1MjAxLCJ1c2VySWQiOjg3NDk3LCJmb3JFeHBpcmF0aW9uQ2hlY2siOiI4ZjBkZTQ2Ny0xMTM1LTRlYTItYjVmMC0wYjczYWJmOTYxODMifX0.zMHO5ilIxA_xN3VqDOSQPrcQSyIkap4mZCivr5ypALY\";");
        properties.setProperty("sasl.mechanism","PLAIN");

        //set producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());

        //create the producer
        KafkaProducer<String ,String > producer = new KafkaProducer<>(properties);

        // create a producer record
        ProducerRecord<String ,String > producerRecord = new ProducerRecord<>("sec_topic","hello");

        // send data
        producer.send(producerRecord);

        //flush the producer to send all data and black until done -- synchonous
        producer.flush();

        // flush and close the producer
        producer.close();

        log.info("kafka closing");


    }

}