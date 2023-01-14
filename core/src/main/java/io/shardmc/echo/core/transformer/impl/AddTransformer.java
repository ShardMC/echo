package io.shardmc.echo.core.transformer.impl;

import io.shardmc.echo.core.transformer.TransformerUtil;
import io.shardmc.echo.core.transformer.type.MethodTransformer;
import woid.node.ClassNode;
import woid.node.MethodNode;
import woid.node.annotation.AnnotationNode;

public class AddTransformer extends MethodTransformer {

    @Override
    protected void transform(ClassNode original, ClassNode echo, MethodNode method) {
        AnnotationNode annotation = TransformerUtil.getAnnotation(method, "Lio/shardmc/echo/annotations/Add;");
        if (annotation != null) {
            TransformerUtil.findMethod(original,
                    (String) annotation.getValue(1),
                    (String) annotation.getValue(3)
            ).getInstructions().addAll(method.getInstructions());
        }
    }
}
