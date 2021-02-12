package ma.cloud.kafka;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface AnalyticsBinding {
    String OPERATION_OUT = "TOTAL_OPERATION";
    String OPERATION_IN = "OPERATIONS";

    @Output(OPERATION_OUT)
    KStream<String, Double> operationsOut();

    @Input(OPERATION_IN)
    KStream<Long, String> operationsIn();
}