package woid.node.method.insn;

import org.objectweb.asm.MethodVisitor;

public abstract class AbstractInsnNode {
    public static final int INSN = 0;
    public static final int INT_INSN = 1;
    public static final int VAR_INSN = 2;
    public static final int TYPE_INSN = 3;
    public static final int FIELD_INSN = 4;
    public static final int METHOD_INSN = 5;
    public static final int INVOKE_DYNAMIC_INSN = 6;
    public static final int JUMP_INSN = 7;
    public static final int LABEL = 8;
    public static final int LDC_INSN = 9;
    public static final int IINC_INSN = 10;
    public static final int TABLESWITCH_INSN = 11;
    public static final int LOOKUPSWITCH_INSN = 12;
    public static final int MULTIANEWARRAY_INSN = 13;
    public static final int FRAME = 14;
    public static final int LINE = 15;

    protected int opcode;

    int index;

    protected AbstractInsnNode(int opcode) {
        this.opcode = opcode;
        this.index = -1;
    }

    public abstract int getType();

    public abstract void accept(MethodVisitor methodVisitor);

    @SuppressWarnings("all")
    protected final void acceptAnnotations(MethodVisitor methodVisitor) {

    }
}
