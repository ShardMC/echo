package io.shardmc.echo.core.transformer.impl;

import io.shardmc.echo.core.Echo;
import io.shardmc.echo.core.transformer.type.EndTransformer;
import org.objectweb.asm.ClassWriter;
import woid.node.ClassNode;

public class DefineTransformer extends EndTransformer {

    @Override
    protected void transform(ClassNode original) {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        original.accept(writer);

        Echo.getClassLoader().defineClass(original.getName(), writer.toByteArray());
    }
}
