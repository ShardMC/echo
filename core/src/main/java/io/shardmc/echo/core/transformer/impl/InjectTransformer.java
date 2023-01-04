package io.shardmc.echo.core.transformer.impl;

import io.shardmc.echo.core.transformer.TransformerUtil;
import io.shardmc.echo.core.transformer.type.MethodTransformer;
import woid.node.ClassNode;
import woid.node.MethodNode;
import woid.node.annotation.AnnotationNode;

public class InjectTransformer extends MethodTransformer {

    @Override
    protected void transform(ClassNode original, ClassNode echo, MethodNode method) {
        AnnotationNode annotation = TransformerUtil.getAnnotation(method, "Lio/shardmc/echo/annotations/Insert;");
        if (annotation != null) {
            String name = (String) annotation.getValue(1);
            String desc = (String) annotation.getValue(3);

            original.getMethods().parallelStream().filter(node -> node.equals(name, desc)).findFirst().get().instructions.addAll(0, method.instructions);
        }
    }
}
