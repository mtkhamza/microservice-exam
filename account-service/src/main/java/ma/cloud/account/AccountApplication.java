package ma.cloud.account;

import ma.cloud.account.Repository.AccountRepository;
import ma.cloud.account.Repository.OperationRepository;
import ma.cloud.account.entities.Account;
import ma.cloud.account.entities.Operation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableKafka
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository, OperationRepository operationRepository) {
        return args -> {
            Account compte = accountRepository.save(
                    new Account(null, "2000", 12.00, new Date(), "COURANT", "ACTIVE", null, 1l, null));
            List<Operation> operations = new ArrayList<>();
            operations.add(operationRepository.save(new Operation(null, 20.00, "vers", compte)));
            compte.setOperations(operations);
            operations.forEach(o -> {
                operationRepository.save(o);
            });
        };
    }
}
