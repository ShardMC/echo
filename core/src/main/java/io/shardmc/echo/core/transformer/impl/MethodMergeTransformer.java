package io.shardmc.echo.core.transformer.impl;

import io.shardmc.echo.core.transformer.TransformerUtil;
import io.shardmc.echo.core.transformer.type.MethodTransformer;
import woid.node.ClassNode;
import woid.node.MethodNode;

public class MethodMergeTransformer extends MethodTransformer {

    @Override
    protected void transform(ClassNode original, ClassNode echo, MethodNode method) {
        if (this.mergeMethod(method)) {
            TransformerUtil.filterInsns(method, echo.getName(), original.getName(), true);
            original.getMethods().add(method);
        }
    }

    private boolean mergeMethod(MethodNode method) {
        if (method.getName().equals("<init>"))
            return false;

        return !TransformerUtil.hasAnnotation(method, "Lio/shardmc/echo/annotations/Shadow;")
                && !TransformerUtil.hasAnnotation(method, "Lio/shardmc/echo/annotations/Insert;");
    }
}
