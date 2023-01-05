package woid.node.method;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public record LocalVariableNode(String name, String descriptor, String signature, Label start, Label end, int index) {
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitLocalVariable(this.name, this.descriptor, this.signature, this.start, this.end, this.index);
    }
}

