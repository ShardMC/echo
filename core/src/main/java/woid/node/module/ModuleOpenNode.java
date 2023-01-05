package woid.node.module;

import org.objectweb.asm.ModuleVisitor;

public record ModuleOpenNode(String packaze, int access, String[] modules) {
    public void accept(ModuleVisitor moduleVisitor) {
        moduleVisitor.visitOpen(packaze, access, modules);
    }
}
