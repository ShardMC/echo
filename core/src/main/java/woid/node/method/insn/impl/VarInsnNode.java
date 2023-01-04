package woid.node.method.insn.impl;

import org.objectweb.asm.MethodVisitor;
import woid.node.method.insn.AbstractInsnNode;

/**
 * A node that represents a local variable instruction. A local variable instruction is an
 * instruction that loads or stores the value of a local variable.
 *
 * @author Eric Bruneton
 */
public class VarInsnNode extends AbstractInsnNode {

    /**
     * The operand of this instruction. This operand is the index of a local variable.
     */
    public final int var;

    /**
     * Constructs a new {@link VarInsnNode}.
     *
     * @param opcode   the opcode of the local variable instruction to be constructed. This opcode must
     *                 be ILOAD, LLOAD, FLOAD, DLOAD, ALOAD, ISTORE, LSTORE, FSTORE, DSTORE, ASTORE or RET.
     * @param varIndex the operand of the instruction to be constructed. This operand is the index of
     *                 a local variable.
     */
    public VarInsnNode(int opcode, int varIndex) {
        super(opcode);

        this.var = varIndex;
    }

    @Override
    public int getType() {
        return VAR_INSN;
    }

    @Override
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(opcode, var);
        this.acceptAnnotations(methodVisitor);
    }
}
