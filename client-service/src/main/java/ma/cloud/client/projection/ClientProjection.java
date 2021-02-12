package ma.cloud.client.projection;

import ma.cloud.client.entities.Client;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullCustomer", types = Client.class)
interface ClientProjection {
    Long getId();

    String getName();

    String getEmail();
}
