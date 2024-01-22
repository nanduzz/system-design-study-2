package dev.fernandocarvalho.kafkacommons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.fernandocarvalho.kafkacommons.model.Message;
import org.apache.kafka.common.serialization.Serializer;

public class GsonSerializer<T> implements Serializer<T> {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Message.class, new MessageAdapter<T>())
            .create();

    @Override
    public byte[] serialize(String s, T obj) {
        return gson.toJson(obj).getBytes();
    }
}
