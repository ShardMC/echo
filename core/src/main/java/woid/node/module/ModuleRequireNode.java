package woid.node.module;

import org.objectweb.asm.ModuleVisitor;

public record ModuleRequireNode(String module, int access, String version) {
    public void accept(ModuleVisitor moduleVisitor) {
        moduleVisitor.visitRequire(module, access, version);
    }
}
