package ma.cloud.kafka.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class Account {
    private  Long id;
    private String code;
    private double solde;
    private Date creationDate;
    private String state;
    private String type;
    private Client client;
    private Long clientID;
    private List<Operation> operations;
}
