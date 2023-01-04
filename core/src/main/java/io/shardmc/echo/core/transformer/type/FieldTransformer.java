package io.shardmc.echo.core.transformer.type;

import io.shardmc.echo.core.transformer.event.Event;
import woid.node.ClassNode;
import woid.node.FieldNode;

public abstract class FieldTransformer implements Transformer {

    protected abstract void transform(ClassNode original, ClassNode echo, FieldNode field);

    @Override
    public void handle(Event event, Object... objects) {
        if (event == Event.FIELD) {
            this.transform((ClassNode) objects[0], (ClassNode) objects[1], (FieldNode) objects[2]);
        }
    }
}
