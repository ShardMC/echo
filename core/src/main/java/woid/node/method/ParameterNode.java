package woid.node.method;

import org.objectweb.asm.MethodVisitor;

/**
 * A node that represents a parameter of a method.
 *
 * @param name   The parameter's name.
 * @param access The parameter's access flags (see {@link org.objectweb.asm.Opcodes}). Valid values are {@code
 *               ACC_FINAL}, {@code ACC_SYNTHETIC} and {@code ACC_MANDATED}.
 * @author Remi Forax
 */
public record ParameterNode(String name, int access) {

    /**
     * Makes the given visitor visit this parameter declaration.
     *
     * @param methodVisitor a method visitor.
     */
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitParameter(this.name, this.access);
    }
}
