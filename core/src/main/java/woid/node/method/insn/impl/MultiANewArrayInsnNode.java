package woid.node.method.insn.impl;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import woid.node.method.insn.AbstractInsnNode;

/**
 * A node that represents a MULTIANEWARRAY instruction.
 *
 * @author Eric Bruneton
 */
public class MultiANewArrayInsnNode extends AbstractInsnNode {

    /**
     * An array type descriptor (see {@link org.objectweb.asm.Type}).
     */
    public final String desc;

    /**
     * Number of dimensions of the array to allocate.
     */
    public final int dims;

    /**
     * Constructs a new {@link MultiANewArrayInsnNode}.
     *
     * @param descriptor    an array type descriptor (see {@link org.objectweb.asm.Type}).
     * @param numDimensions the number of dimensions of the array to allocate.
     */
    public MultiANewArrayInsnNode(String descriptor, int numDimensions) {
        super(Opcodes.MULTIANEWARRAY);

        this.desc = descriptor;
        this.dims = numDimensions;
    }

    @Override
    public int getType() {
        return MULTIANEWARRAY_INSN;
    }

    @Override
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitMultiANewArrayInsn(this.desc, this.dims);
        this.acceptAnnotations(methodVisitor);
    }
}
