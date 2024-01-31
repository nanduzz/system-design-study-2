package dev.fernandocarvalho.kafkacommons;

import dev.fernandocarvalho.kafkacommons.model.Message;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static dev.fernandocarvalho.kafkacommons.model.Topics.NEW_PAYMENT_TOPIC;

@Configuration
public class KafkaProducerConfigurations {

    public ProducerFactory<String, Message<?>> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class);

        return props;
    }

    @Bean
    public KafkaTemplate<String, Message<?>> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic topicNewPayment() {
        return new NewTopic(NEW_PAYMENT_TOPIC, 3, (short) 3);
    }


}
