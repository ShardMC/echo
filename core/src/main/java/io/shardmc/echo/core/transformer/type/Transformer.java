package io.shardmc.echo.core.transformer.type;

import io.shardmc.echo.core.transformer.event.Event;

public interface Transformer {
    void handle(Event event, Object... objects);
}
