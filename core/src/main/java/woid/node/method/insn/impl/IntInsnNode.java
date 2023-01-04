package woid.node.method.insn.impl;

import org.objectweb.asm.MethodVisitor;
import woid.node.method.insn.AbstractInsnNode;

/**
 * A node that represents an instruction with a single int operand.
 *
 * @author Eric Bruneton
 */
public class IntInsnNode extends AbstractInsnNode {

    /**
     * The operand of this instruction.
     */
    public final int operand;

    /**
     * Constructs a new {@link IntInsnNode}.
     *
     * @param opcode  the opcode of the instruction to be constructed. This opcode must be BIPUSH,
     *                SIPUSH or NEWARRAY.
     * @param operand the operand of the instruction to be constructed.
     */
    public IntInsnNode(int opcode, int operand) {
        super(opcode);

        this.operand = operand;
    }

    @Override
    public int getType() {
        return INT_INSN;
    }

    @Override
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitIntInsn(this.opcode, this.operand);
        this.acceptAnnotations(methodVisitor);
    }
}
