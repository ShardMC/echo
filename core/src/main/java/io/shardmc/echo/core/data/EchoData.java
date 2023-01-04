package io.shardmc.echo.core.data;

import io.shardmc.echo.core.transformer.TransformerUtil;
import org.objectweb.asm.ClassReader;
import woid.node.ClassNode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EchoData {

    private final ConcurrentHashMap<ClassNode, List<ClassNode>> actualRefs = new ConcurrentHashMap<>();

    public void merge(Map<String, List<String>> refs) {
        refs.forEach((k, v) -> this.actualRefs.merge(TransformerUtil.createClassNode(k, ClassReader.EXPAND_FRAMES), TransformerUtil.createClassNodes(v), (c, c1) -> {
            c.addAll(c1);
            return c;
        }));
    }

    public Set<Map.Entry<ClassNode, List<ClassNode>>> getEntries() {
        return this.actualRefs.entrySet();
    }
}
