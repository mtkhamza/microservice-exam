package ma.cloud.account;

import lombok.extern.slf4j.Slf4j;
import ma.cloud.account.entities.Account;
import ma.cloud.account.entities.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class OperationSource implements ApplicationRunner {
    @Autowired
    private KafkaTemplate<String, Operation> kafkaTemplate;

    @Override
    public void run(ApplicationArguments applicationArguments) {
        System.out.println("..................................................;");

        Runnable runnable = () -> {
            Account account = new Account(null, "300", 200.00, new Date(), "COURANT", "ACTIVE", null, 1l,null);
            Operation operation = new Operation(null, 20.00, "vers", account);
            System.out.println("## Sending Operation ...");
            kafkaTemplate.send("OPERATIONS", operation);
            log.info("Sending Operation =>" + operation.toString());
        };
        System.out.println("--------------------------------------------");
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }
}