package ma.cloud.account.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String code;
    private double sold;
    private Date creationDate;
    private String state;
    private String type;

    @Transient
    private Client client;
    private Long clientID;

    @OneToMany(mappedBy = "account")
    private List<Operation> operations;
}
