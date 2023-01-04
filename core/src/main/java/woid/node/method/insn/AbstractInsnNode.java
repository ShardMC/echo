package woid.node.method.insn;

import org.objectweb.asm.MethodVisitor;

/**
 * A node that represents a bytecode instruction. <i>An instruction can appear at most once in at
 * most one {@link InsnList} at a time</i>.
 *
 * @author Eric Bruneton
 */
public abstract class AbstractInsnNode {

    /**
     * The type of {@link woid.node.method.insn.impl.SimpleInsnNode} instructions.
     */
    public static final int INSN = 0;

    /**
     * The type of {@link woid.node.method.insn.impl.IntInsnNode} instructions.
     */
    public static final int INT_INSN = 1;

    /**
     * The type of {@link woid.node.method.insn.impl.VarInsnNode} instructions.
     */
    public static final int VAR_INSN = 2;

    /**
     * The type of {@link woid.node.method.insn.impl.TypeInsnNode} instructions.
     */
    public static final int TYPE_INSN = 3;

    /**
     * The type of {@link woid.node.method.insn.impl.FieldInsnNode} instructions.
     */
    public static final int FIELD_INSN = 4;

    /**
     * The type of {@link woid.node.method.insn.impl.MethodInsnNode} instructions.
     */
    public static final int METHOD_INSN = 5;

    /**
     * The type of {@link woid.node.method.insn.impl.InvokeDynamicInsnNode} instructions.
     */
    public static final int INVOKE_DYNAMIC_INSN = 6;

    /**
     * The type of {@link woid.node.method.insn.impl.JumpInsnNode} instructions.
     */
    public static final int JUMP_INSN = 7;

    /**
     * The type of {@link woid.node.method.insn.impl.LabelNode} "instructions".
     */
    public static final int LABEL = 8;

    /**
     * The type of {@link woid.node.method.insn.impl.LdcInsnNode} instructions.
     */
    public static final int LDC_INSN = 9;

    /**
     * The type of {@link woid.node.method.insn.impl.IincInsnNode} instructions.
     */
    public static final int IINC_INSN = 10;

    /**
     * The type of {@link woid.node.method.insn.impl.TableSwitchInsnNode} instructions.
     */
    public static final int TABLESWITCH_INSN = 11;

    /**
     * The type of {@link woid.node.method.insn.impl.LookupSwitchInsnNode} instructions.
     */
    public static final int LOOKUPSWITCH_INSN = 12;

    /**
     * The type of {@link woid.node.method.insn.impl.MultiANewArrayInsnNode} instructions.
     */
    public static final int MULTIANEWARRAY_INSN = 13;

    /**
     * The type of {@link woid.node.method.insn.impl.FrameInsnNode} "instructions".
     */
    public static final int FRAME = 14;

    /**
     * The type of {@link woid.node.method.insn.impl.LineInsnNode} "instructions".
     */
    public static final int LINE = 15;

    /**
     * The opcode of this instruction, or -1 if this is not a JVM instruction (e.g. a label or a line
     * number).
     */
    protected int opcode;

    int index;

    /**
     * Constructs a new {@link AbstractInsnNode}.
     *
     * @param opcode the opcode of the instruction to be constructed.
     */
    protected AbstractInsnNode(int opcode) {
        this.opcode = opcode;
        this.index = -1;
    }

    /**
     * Returns the type of this instruction.
     *
     * @return the type of this instruction, i.e. one the constants defined in this class.
     */
    public abstract int getType();

    /**
     * Makes the given method visitor visit this instruction.
     *
     * @param methodVisitor a method visitor.
     */
    public abstract void accept(MethodVisitor methodVisitor);

    /**
     * Makes the given visitor visit the annotations of this instruction.
     *
     * @param methodVisitor a method visitor.
     */
    @SuppressWarnings("all")
    protected final void acceptAnnotations(MethodVisitor methodVisitor) {

    }
}
