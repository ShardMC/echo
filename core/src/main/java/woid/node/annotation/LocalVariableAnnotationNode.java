package woid.node.annotation;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

/**
 * A node that represents a type annotation on a local or resource variable.
 *
 * @author Eric Bruneton
 */
public class LocalVariableAnnotationNode extends TypeAnnotationNode {
    /**
     * The fist instructions corresponding to the continuous ranges that make the scope of this local
     * variable (inclusive). Must not be {@literal null}.
    */
    private final Label[] start;

    /**
     * The last instructions corresponding to the continuous ranges that make the scope of this local
     * variable (exclusive). This list must have the same size as the 'start' list. Must not be
     * {@literal null}.
     */
    private final Label[] end;

    /**
     * The local variable's index in each range. This list must have the same size as the 'start'
     * list. Must not be {@literal null}.
     */
    private final int[] index;

    /**
     * Constructs a new {@link LocalVariableAnnotationNode}.
     *
     * @param typeRef    a reference to the annotated type. See {@link org.objectweb.asm.TypeReference}.
     * @param typePath   the path to the annotated type argument, wildcard bound, array element type, or
     *                   static inner type within 'typeRef'. May be {@literal null} if the annotation targets
     *                   'typeRef' as a whole.
     * @param start      the fist instructions corresponding to the continuous ranges that make the scope
     *                   of this local variable (inclusive).
     * @param end        the last instructions corresponding to the continuous ranges that make the scope of
     *                   this local variable (exclusive). This array must have the same size as the 'start' array.
     * @param index      the local variable's index in each range. This array must have the same size as
     *                   the 'start' array.
     * @param descriptor the class descriptor of the annotation class.
    */
    public LocalVariableAnnotationNode(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String descriptor, boolean visible) {
        this(Opcodes.ASM9, typeRef, typePath, start, end, index, descriptor, visible);
    }

    /**
     * Constructs a new {@link LocalVariableAnnotationNode}.
     *
     * @param api        the ASM API version implemented by this visitor. Must be one of the {@code
     *                   ASM}<i>x</i> values in {@link Opcodes}.
     * @param typeRef    a reference to the annotated type. See {@link org.objectweb.asm.TypeReference}.
     * @param start      the fist instructions corresponding to the continuous ranges that make the scope
     *                   of this local variable (inclusive).
     * @param end        the last instructions corresponding to the continuous ranges that make the scope of
     *                   this local variable (exclusive). This array must have the same size as the 'start' array.
     * @param index      the local variable's index in each range. This array must have the same size as
     *                   the 'start' array.
     * @param typePath   the path to the annotated type argument, wildcard bound, array element type, or
     *                   static inner type within 'typeRef'. May be {@literal null} if the annotation targets
     *                   'typeRef' as a whole.
     * @param descriptor the class descriptor of the annotation class.
    */
    public LocalVariableAnnotationNode(int api, int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String descriptor, boolean visible) {
        super(api, typeRef, typePath, descriptor, visible);

        this.start = start;
        this.end = end;
        this.index = index;
    }

    /**
     * Makes the given visitor visit this type annotation.
     *
     * @param methodVisitor the visitor that must visit this annotation.
     */
    public void accept(MethodVisitor methodVisitor) {
        this.accept(
                methodVisitor.visitLocalVariableAnnotation(
                        this.getTypeRef(),
                        this.getTypePath(),
                        this.start,
                        this.end,
                        this.index,
                        this.getDesc(),
                        this.isVisible()
                )
        );
    }
}