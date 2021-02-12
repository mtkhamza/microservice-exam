package ma.cloud.kafka.model;

import lombok.Data;


@Data
public class Operation {
    private Long id;
    private double montant;
    private String type;
    private Account account;
}