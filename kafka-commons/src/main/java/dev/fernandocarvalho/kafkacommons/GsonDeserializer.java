package dev.fernandocarvalho.kafkacommons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.fernandocarvalho.kafkacommons.model.Message;
import org.apache.kafka.common.serialization.Deserializer;

public class GsonDeserializer<T> implements Deserializer<Message<T>> {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Message.class, new MessageAdapter<T>())
            .create();

    @Override
    public Message<T> deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }

        String jsonString = new String(data);
        return gson.fromJson(jsonString, Message.class);
    }
}
