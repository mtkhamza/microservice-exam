package ma.cloud.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ma.cloud.kafka.model.Account;
import ma.cloud.kafkastream.entities.Bill;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class BillsProcessing {
    ObjectMapper objectMapper = new ObjectMapper();
    @StreamListener
    @SendTo({AnalyticsBinding.OPERATION_OUT})
    public KStream<String, Double> process(@Input(AnalyticsBinding.BILLS_IN) KStream<Long,String> events){
        return events
                .map((k,v)->new KeyValue<>(k, bill(v)))
                .map((k,v)->new KeyValue<>(v.getClient(),v.getSolde()))
                .groupByKey()
                .windowedBy(TimeWindows.of(5000))
                .reduce(Double::sum).toStream()
                .map((k,v)->new KeyValue<>(k.key(),v));
    }

    public Account bill(String strObject){
        Account account=new Account();
        try {
            account=objectMapper.readValue(strObject,Account.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return account;
    }


}
