package io.shardmc.echo.core.transformer.type;

import io.shardmc.echo.core.transformer.event.Event;
import io.shardmc.echo.core.transformer.type.base.Transformer;
import woid.node.ClassNode;

public abstract class EndTransformer implements Transformer {

    protected abstract void transform(ClassNode original);

    @Override
    public void handle(Event event, Object... objects) {
        if (event == Event.END) {
            this.transform((ClassNode) objects[0]);
        }
    }
}
