package dev.fernandocarvalho.kafkacommons;


import dev.fernandocarvalho.kafkacommons.model.Message;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaListenerConfigurations {
    @Bean
    public ConsumerFactory<String, Message<?>> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Message<?>>
    kafkaListenerContainerFactory(ConsumerFactory<String, Message<?>> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Message<?>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }


    private Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        // ...
        return props;
    }


}
