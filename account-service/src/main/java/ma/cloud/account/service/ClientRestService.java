package ma.cloud.account.service;

import ma.cloud.account.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLIENT-SERVICE")
public interface ClientRestService {
    @GetMapping(path = "/clients/{id}")
    public Client findClientById(@PathVariable("id") Long id);
}
