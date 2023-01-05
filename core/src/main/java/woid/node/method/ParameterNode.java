package woid.node.method;

import org.objectweb.asm.MethodVisitor;

public record ParameterNode(String name, int access) {
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitParameter(this.name, this.access);
    }
}
