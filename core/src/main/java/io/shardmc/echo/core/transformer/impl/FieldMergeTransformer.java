package io.shardmc.echo.core.transformer.impl;

import io.shardmc.echo.core.transformer.TransformerUtil;
import io.shardmc.echo.core.transformer.type.FieldTransformer;
import woid.node.ClassNode;
import woid.node.FieldNode;

public class FieldMergeTransformer extends FieldTransformer {

    @Override
    protected void transform(ClassNode original, ClassNode echo, FieldNode field) {
        if (!TransformerUtil.hasAnnotation(field, "Lio/shardmc/echo/annotations/Shadow;")) {
            original.getFields().add(field);
        }
    }
}
