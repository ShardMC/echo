package woid.node.method;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

/**
 * A node that represents a local variable declaration.
 *
 * @author Eric Bruneton
 */
public record LocalVariableNode(String name, String descriptor, String signature, Label start, Label end, int index) {

    /**
     * Makes the given visitor visit this local variable declaration.
     *
     * @param methodVisitor a method visitor.
     */
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitLocalVariable(this.name, this.descriptor, this.signature, this.start, this.end, this.index);
    }
}

