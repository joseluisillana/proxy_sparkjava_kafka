package com.bbva.mike;

import com.bbva.mike.kafkautils.KafkaConsumer;
import com.bbva.mike.kafkautils.KafkaProducer;
import kafka.common.FailedToSendMessageException;
import org.eclipse.jetty.http.HttpStatus;
import spark.Spark;
import spark.servlet.SparkApplication;

import java.nio.channels.ClosedChannelException;

/**
 * Created by joseluisillana on 14/12/15.
 */
public class DataToKafka implements SparkApplication {

    @Override
    public void init() {

        Spark.get("/", (request, response) -> {
                response.status(HttpStatus.OK_200);
                return "Todo OK";

        });
        // Endpoint que carga datos de ejemplo en un topic 'test'
        Spark.post("/sendTestData/:topic", (request, response) -> {
            // Generamos datos de prueba y insertamos algo
            String topicParam = ((request.params(":topic") != null) ? request.params(":topic") : "test");
            String bodyParam = ((request.body() != null) ? request.body() : "esto es un test");

            KafkaProducer kafkaProducer = new KafkaProducer();
            try {
                kafkaProducer.send(topicParam, bodyParam);
                kafkaProducer.close();
                response.status(HttpStatus.OK_200);
                return "200 - OK";
            } catch (Exception e) {
                if (e instanceof ClosedChannelException) {
                    System.out.println("Got: " + e.getClass().getName());   // Print message
                    response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
                    return "500 - Canal cerrado";
                } else if (e instanceof FailedToSendMessageException) {
                    System.out.println("Got: " + e.getClass().getName());   // Print message
                    response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
                    return "500 - Error en el envío";
                } else {
                    System.out.println("Got: " + e.getClass().getName());   // Print message
                    response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
                    return "500 - Error del servidor";
                }
            }

            // Insertamos en kafka
        });


        // Endpoint que carga datos de ejemplo en un topic 'test'
        Spark.get("/readTestData/:topic", (request, response) -> {
            // Generamos datos de prueba y insertamos algo

            String topicParam = ((request.params(":topic") != null) ? request.params(":topic") : "test");

            KafkaConsumer kafkaConsumer = new KafkaConsumer();

            kafkaConsumer.run();
            response.status(HttpStatus.OK_200);
            return "Todo OK";
            // Insertamos en kafka
        });
    }


}

/*
public class DataToKafka {

    public static void main(String[] args) {
        // Endpoint que carga datos de ejemplo en un topic 'test'
        post("/sendTestData/:topic", (request, response) -> {
            // Generamos datos de prueba y insertamos algo
            String topicParam = ((request.params(":topic") != null) ? request.params(":topic") : "test");
            String bodyParam = ((request.body() != null) ? request.body() : "esto es un test");

            KafkaProducer kafkaProducer = new KafkaProducer();
            try {
                kafkaProducer.send(topicParam, bodyParam);
                kafkaProducer.close();
                return "200 - OK";
            } catch (Exception e) {
                if (e instanceof ClosedChannelException) {
                    System.out.println("Got: " + e.getClass().getName());   // Print message
                    response.status(500);
                    return "500 - Canal cerrado";
                } else if (e instanceof FailedToSendMessageException) {
                    System.out.println("Got: " + e.getClass().getName());   // Print message
                    response.status(500);
                    return "500 - Error en el envío";
                } else {
                    System.out.println("Got: " + e.getClass().getName());   // Print message
                    response.status(500);
                    return "500 - Error del servidor";
                }
            }

            // Insertamos en kafka
        });


        // Endpoint que carga datos de ejemplo en un topic 'test'
        get("/readTestData:topic", (request, response) -> {
            // Generamos datos de prueba y insertamos algo

            String topicParam = ((request.params(":topic") != null) ? request.params(":topic") : "test");

            KafkaConsumer kafkaConsumer = new KafkaConsumer();

            kafkaConsumer.run();

            return "200 - OK";
            // Insertamos en kafka
        });
    }


}
*/
