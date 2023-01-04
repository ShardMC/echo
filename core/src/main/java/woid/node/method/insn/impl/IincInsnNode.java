package woid.node.method.insn.impl;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import woid.node.method.insn.AbstractInsnNode;

/**
 * A node that represents an IINC instruction.
 *
 * @author Eric Bruneton
 */
public class IincInsnNode extends AbstractInsnNode {

    /**
     * Index of the local variable to be incremented.
     */
    private final int var;

    /**
     * Amount to increment the local variable by.
     */
    private final int incr;

    /**
     * Constructs a new {@link IincInsnNode}.
     *
     * @param varIndex index of the local variable to be incremented.
     * @param incr     increment amount to increment the local variable by.
     */
    public IincInsnNode(int varIndex, final int incr) {
        super(Opcodes.IINC);

        this.var = varIndex;
        this.incr = incr;
    }

    @Override
    public int getType() {
        return IINC_INSN;
    }

    @Override
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitIincInsn(this.var, this.incr);
        this.acceptAnnotations(methodVisitor);
    }
}
