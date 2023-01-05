package woid.node.module;

import org.objectweb.asm.ModuleVisitor;

public record ModuleProvideNode(String service, String[] providers) {
    public void accept(ModuleVisitor moduleVisitor) {
        moduleVisitor.visitProvide(service, providers);
    }
}
