package woid.node.method;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

/**
 * @param start   The beginning of the exception handler's scope (inclusive).
 * @param end     The end of the exception handler's scope (exclusive).
 * @param handler The beginning of the exception handler's code.
 * @param type    The internal name of the type of exceptions handled by the handler. May be {@literal null} to
 *                catch any exceptions (for "finally" blocks).
 */
public record TryCatchBlockNode(Label start, Label end, Label handler, String type) {

    /**
     * Makes the given visitor visit this try catch block.
     *
     * @param methodVisitor a method visitor.
     */
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitTryCatchBlock(this.start, this.end, this.handler, this.type);
    }
}
