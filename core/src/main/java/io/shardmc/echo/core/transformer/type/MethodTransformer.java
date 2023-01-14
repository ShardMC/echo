package io.shardmc.echo.core.transformer.type;

import io.shardmc.echo.core.transformer.event.Event;
import io.shardmc.echo.core.transformer.type.base.Transformer;
import woid.node.ClassNode;
import woid.node.MethodNode;

public abstract class MethodTransformer implements Transformer {

    protected abstract void transform(ClassNode original, ClassNode echo, MethodNode method);

    @Override
    public void handle(Event event, Object... objects) {
        if (event == Event.METHOD) {
            this.transform((ClassNode) objects[0], (ClassNode) objects[1], (MethodNode) objects[2]);
        }
    }
}
