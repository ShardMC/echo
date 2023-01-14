package io.shardmc.echo.core.transformer;

import io.shardmc.echo.core.transformer.event.Event;
import io.shardmc.echo.core.transformer.type.base.Transformer;

import java.util.ServiceLoader;

public class Transformers {
    private static final ServiceLoader<Transformer> transformers = ServiceLoader.load(Transformer.class);

    public static void handle(Event event, Object... objects) {
        Transformers.transformers.stream().parallel().forEach(provider -> provider.get().handle(event, objects));
    }
}
