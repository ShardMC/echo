package woid2.data.method;

import org.objectweb.asm.MethodVisitor;

public interface MethodElement {
    void visit(MethodVisitor visitor);
}
