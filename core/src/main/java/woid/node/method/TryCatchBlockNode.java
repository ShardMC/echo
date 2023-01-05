package woid.node.method;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public record TryCatchBlockNode(Label start, Label end, Label handler, String type) {
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitTryCatchBlock(this.start, this.end, this.handler, this.type);
    }
}
