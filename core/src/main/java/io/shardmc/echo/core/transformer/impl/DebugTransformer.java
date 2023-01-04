package io.shardmc.echo.core.transformer.impl;

import io.shardmc.echo.core.transformer.type.EndTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;
import woid.node.ClassNode;

import java.io.PrintWriter;

public class DebugTransformer extends EndTransformer {

    @Override
    protected void transform(ClassNode original) {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        original.accept(writer);

        new ClassReader(writer.toByteArray()).accept(new TraceClassVisitor(new PrintWriter(System.out)), 0);
    }
}
