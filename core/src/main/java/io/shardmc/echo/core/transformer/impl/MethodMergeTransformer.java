package io.shardmc.echo.core.transformer.impl;

import io.shardmc.echo.core.transformer.TransformerUtil;
import io.shardmc.echo.core.transformer.type.MethodTransformer;
import woid.node.ClassNode;
import woid.node.MethodNode;
import woid.node.method.insn.impl.FieldInsnNode;
import woid.node.method.insn.impl.MethodInsnNode;

public class MethodMergeTransformer extends MethodTransformer {

    @Override
    protected void transform(ClassNode original, ClassNode echo, MethodNode method) {
        if (this.mergeMethod(method)) {
            method.instructions.parallelStream()
                    .forEach(node -> {
                        if (node instanceof MethodInsnNode methodNode && methodNode.getOwner().equals(echo.getName())) {
                            methodNode.setOwner(original.getName());
                        }

                        if (node instanceof FieldInsnNode fieldNode && fieldNode.getOwner().equals(echo.getName())) {
                            fieldNode.setOwner(original.getName());
                        }
                    });

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
