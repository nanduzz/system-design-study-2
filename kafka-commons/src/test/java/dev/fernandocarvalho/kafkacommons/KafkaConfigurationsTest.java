package dev.fernandocarvalho.kafkacommons;

import dev.fernandocarvalho.kafkacommons.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = KafkaConfigurations.class)
class KafkaConfigurationsTest {

    @Autowired
    private KafkaTemplate<String, Message<?>> kafkaTemplate;

    @Test
    void kafkaTemplate() {
        assertNotNull(kafkaTemplate);
    }

}