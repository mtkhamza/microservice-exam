package ma.cloud.client.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    @Autowired
    private Environment environment;

    @GetMapping("/")
    public String home() {
        return "Hello from Client Service running at port: " + environment.getProperty("local.server.port");
    }
}



