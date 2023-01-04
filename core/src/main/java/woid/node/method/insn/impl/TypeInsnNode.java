package woid.node.method.insn.impl;

import org.objectweb.asm.MethodVisitor;
import woid.node.method.insn.AbstractInsnNode;

/**
 * A node that represents a type instruction. A type instruction is an instruction which takes an
 * internal name as parameter (see {@link org.objectweb.asm.Type#getInternalName()}).
 *
 * @author Eric Bruneton
 */
public class TypeInsnNode extends AbstractInsnNode {

    /**
     * The operand of this instruction. Despite its name (due to historical reasons), this operand is
     * an internal name (see {@link org.objectweb.asm.Type#getInternalName()}).
     */
    public final String desc;

    /**
     * Constructs a new {@link TypeInsnNode}.
     *
     * @param opcode the opcode of the type instruction to be constructed. This opcode must be NEW,
     *               ANEWARRAY, CHECKCAST or INSTANCEOF.
     * @param type   the operand of the instruction to be constructed. This operand is an internal name
     *               (see {@link org.objectweb.asm.Type#getInternalName()}).
     */
    public TypeInsnNode(int opcode, String type) {
        super(opcode);

        this.desc = type;
    }

    @Override
    public int getType() {
        return TYPE_INSN;
    }

    @Override
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitTypeInsn(opcode, desc);
        this.acceptAnnotations(methodVisitor);
    }
}
