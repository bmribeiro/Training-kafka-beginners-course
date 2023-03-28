package io.conduktor.demos.kafka;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {

    private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());

    public static void main(String[] args) {
        log.info("I am a Kafka Producer");

        // create Producer Properties
        Properties properties = new Properties();

        // Localhost
        // properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

        // Conduktor

        properties.setProperty("bootstrap.servers", "cluster.playground.cdkt.io:9092");
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"3fvxoDa7e3C4eqPnskpWrZ\" password=\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiIzZnZ4b0RhN2UzQzRlcVBuc2twV3JaIiwib3JnYW5pemF0aW9uSWQiOjcxNzAzLCJ1c2VySWQiOjgzMTYyLCJmb3JFeHBpcmF0aW9uQ2hlY2siOiIwMTYwYTQxYy0yOTQ3LTRhYjEtOWYyNC0xMmNjNmI4MDk4N2IifX0.eVck5TzS0JfDZptBZvAEu-Dmcd9WwLbQzQMGE717iiI\";");
        properties.setProperty("sasl.mechanism", "PLAIN");


        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        // create a Producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // create a Producer Record
        ProducerRecord<String,String> producerRecord = new ProducerRecord<>("demo_java","demo_java");

        // send data
        producer.send(producerRecord);

        // tell the producer to send all data and block until done -- synchronous
        producer.flush();

        // flush and close the producer
        producer.close();

    }
}
