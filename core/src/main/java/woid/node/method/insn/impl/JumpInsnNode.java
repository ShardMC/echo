package woid.node.method.insn.impl;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import woid.node.method.insn.AbstractInsnNode;

/**
 * A node that represents a jump instruction. A jump instruction is an instruction that may jump to
 * another instruction.
 *
 * @author Eric Bruneton
 */
public class JumpInsnNode extends AbstractInsnNode {

    /**
     * The operand of this instruction. This operand is a label that designates the instruction to
     * which this instruction may jump.
     */
    private final Label label;

    /**
     * Constructs a new {@link JumpInsnNode}.
     *
     * @param opcode the opcode of the type instruction to be constructed. This opcode must be IFEQ,
     *               IFNE, IFLT, IFGE, IFGT, IFLE, IF_ICMPEQ, IF_ICMPNE, IF_ICMPLT, IF_ICMPGE, IF_ICMPGT,
     *               IF_ICMPLE, IF_ACMPEQ, IF_ACMPNE, GOTO, JSR, IFNULL or IFNONNULL.
     * @param label  the operand of the instruction to be constructed. This operand is a label that
     *               designates the instruction to which the jump instruction may jump.
     */
    public JumpInsnNode(int opcode, Label label) {
        super(opcode);

        this.label = label;
    }

    @Override
    public int getType() {
        return JUMP_INSN;
    }

    @Override
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitJumpInsn(this.opcode, this.label);
        this.acceptAnnotations(methodVisitor);
    }
}

