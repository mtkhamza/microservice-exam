package ma.cloud.client;

import ma.cloud.client.Repository.ClientRepository;
import ma.cloud.client.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


@SpringBootApplication
@EnableDiscoveryClient
public class ClientApplication implements CommandLineRunner {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) {
        repositoryRestConfiguration.exposeIdsFor(Client.class);
        clientRepository.save(new Client(null,"code1", "Hamza", "hamza@gmail.com"));
        clientRepository.save(new Client(null,"code2", "Maryam", "maryam@gmail.com"));
        clientRepository.save(new Client(null, "code3","Saad", "saad@gmail.com"));
        clientRepository.save(new Client(null, "code4","Anas", "anas@gmail.com"));
    }

}
