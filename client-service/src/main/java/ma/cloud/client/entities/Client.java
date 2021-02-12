package ma.cloud.client.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String email;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                "code=" + code +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
