package dev.fernandocarvalho.kafkacommons;

import com.google.gson.*;
import dev.fernandocarvalho.kafkacommons.model.CorrelationId;
import dev.fernandocarvalho.kafkacommons.model.Message;

import java.lang.reflect.Type;

public class MessageAdapter<T> implements JsonSerializer<Message<T>>, JsonDeserializer<Message<T>> {


    @Override
    public JsonElement serialize(Message<T> tMessage, Type type, JsonSerializationContext ctx) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", tMessage.getPayload().getClass().getName());
        jsonObject.add("correlationId", ctx.serialize(tMessage.getCorrelationId()));
        jsonObject.add("payload", ctx.serialize(tMessage.getPayload()));
        return jsonObject;
    }

    @Override
    public Message<T> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        var obj = jsonElement.getAsJsonObject();
        var payloadType = obj.get("type").getAsString();
        CorrelationId correlationId = ctx.deserialize(obj.get("correlationId"), CorrelationId.class);
        try{
            var payload = ctx.deserialize(obj.get("payload"), Class.forName(payloadType));
            return new Message<>(correlationId, (T) payload);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }

}
