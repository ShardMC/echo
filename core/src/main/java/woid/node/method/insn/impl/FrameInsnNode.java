package woid.node.method.insn.impl;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import woid.node.method.insn.AbstractInsnNode;

import java.util.Arrays;

/**
 * A node that represents a stack map frame. These nodes are pseudo instruction nodes in order to be
 * inserted in an instruction list. In fact these nodes must(*) be inserted <i>just before</i> any
 * instruction node <b>i</b> that follows an unconditionnal branch instruction such as GOTO or
 * THROW, that is the target of a jump instruction, or that starts an exception handler block. The
 * stack map frame types must describe the values of the local variables and of the operand stack
 * elements <i>just before</i> <b>i</b> is executed. <br>
 * <br>
 * (*) this is mandatory only for classes whose version is greater than or equal to {@link
 * Opcodes#V1_6}.
 *
 * @author Eric Bruneton
 */
public class FrameInsnNode extends AbstractInsnNode {
  
    /**
     * The type of this frame. Must be {@link Opcodes#F_NEW} for expanded frames, or {@link
     * Opcodes#F_FULL}, {@link Opcodes#F_APPEND}, {@link Opcodes#F_CHOP}, {@link Opcodes#F_SAME} or
     * {@link Opcodes#F_APPEND}, {@link Opcodes#F_SAME1} for compressed frames.
    */
    private final int type;
  
    /**
     * The types of the local variables of this stack map frame. Elements of this list can be Integer,
     * String or LabelNode objects (for primitive, reference and uninitialized types respectively -
     * see {@link MethodVisitor}).
    */
    private Object[] local;
  
    /**
     * The types of the operand stack elements of this stack map frame. Elements of this list can be
     * Integer, String or LabelNode objects (for primitive, reference and uninitialized types
     * respectively - see {@link MethodVisitor}).
    */
    private Object[] stack;
  
    /**
     * Constructs a new {@link FrameInsnNode}.
     *
     * @param type     the type of this frame. Must be {@link Opcodes#F_NEW} for expanded frames, or
     *                 {@link Opcodes#F_FULL}, {@link Opcodes#F_APPEND}, {@link Opcodes#F_CHOP}, {@link
     *                 Opcodes#F_SAME} or {@link Opcodes#F_APPEND}, {@link Opcodes#F_SAME1} for compressed frames.
     * @param numLocal number of local variables of this stack map frame. Long and double values count
     *                 for one variable.
     * @param local    the types of the local variables of this stack map frame. Elements of this list
     *                 can be Integer, String or LabelNode objects (for primitive, reference and uninitialized
     *                 types respectively - see {@link MethodVisitor}). Long and double values are represented by
     *                 a single element.
     * @param numStack number of operand stack elements of this stack map frame. Long and double
     *                 values count for one stack element.
     * @param stack    the types of the operand stack elements of this stack map frame. Elements of this
     *                 list can be Integer, String or LabelNode objects (for primitive, reference and
     *                 uninitialized types respectively - see {@link MethodVisitor}). Long and double values are
     *                 represented by a single element.
    */
    public FrameInsnNode(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
        super(-1);
        this.type = type;

        switch (type) {
            case Opcodes.F_NEW, Opcodes.F_FULL -> {
                this.local = Arrays.copyOf(local, numLocal);
                this.stack = Arrays.copyOf(local, numStack);
            }

            case Opcodes.F_APPEND -> this.local = Arrays.copyOf(local, numLocal);
            case Opcodes.F_CHOP -> this.local = new Object[numLocal];

            case Opcodes.F_SAME -> { }
            case Opcodes.F_SAME1 -> this.stack = Arrays.copyOf(stack, 1);

            default -> throw new IllegalArgumentException();
        }
    }
  
    @Override
    public int getType() {
        return FRAME;
    }
  
    @Override
    public void accept(MethodVisitor methodVisitor) {
        switch (this.type) {
            case Opcodes.F_NEW, Opcodes.F_FULL -> methodVisitor.visitFrame(this.type, this.local.length, this.local, this.stack.length, this.stack);

            case Opcodes.F_APPEND -> methodVisitor.visitFrame(this.type, this.local.length, this.local, 0, null);
            case Opcodes.F_CHOP -> methodVisitor.visitFrame(this.type, this.local.length, null, 0, null);

            case Opcodes.F_SAME -> methodVisitor.visitFrame(this.type, 0, null, 0, null);
            case Opcodes.F_SAME1 -> methodVisitor.visitFrame(this.type, 0, null, 1, this.stack);

            default -> throw new IllegalArgumentException();
        }
    }
}

