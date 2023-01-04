package io.shardmc.echo.core.transformer;

import io.shardmc.echo.core.data.EchoData;
import io.shardmc.echo.core.transformer.event.Event;

public class TransformationManager {

    public static void start(EchoData config) {
        config.getEntries().parallelStream().forEach(entry -> {
            entry.getValue().forEach(echo -> {
                echo.getMethods().forEach(method -> Transformers.handle(Event.METHOD, entry.getKey(), echo, method));
                echo.getFields().forEach(field -> Transformers.handle(Event.FIELD, entry.getKey(), echo, field));
            });

            Transformers.handle(Event.END, entry.getKey());
        });
    }
}
