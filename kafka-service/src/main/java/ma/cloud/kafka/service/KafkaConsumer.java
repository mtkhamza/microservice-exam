package ma.cloud.kafka.service;

import ma.cloud.kafka.model.Operation;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "OPERATIONS", groupId = "sample_operation")
    public void onMessage(Operation operation) {
        System.out.println("Receiving Bill => " + operation.toString());
    }
}