package io.shardmc.echo.core.transformer.type.base;

import io.shardmc.echo.core.transformer.event.Event;

public interface Transformer {
    void handle(Event event, Object... objects);
}
