package woid.node.module;

import org.objectweb.asm.ModuleVisitor;

public record ModuleExportNode(String packaze, int access, String[] modules) {
    public void accept(ModuleVisitor moduleVisitor) {
        moduleVisitor.visitExport(packaze, access, modules);
    }
}

