package woid.node.method.insn.impl;

import org.objectweb.asm.*;
import woid.node.method.insn.AbstractInsnNode;

/**
 * A node that represents an LDC instruction.
 *
 * @author Eric Bruneton
 */
public class LdcInsnNode extends AbstractInsnNode {

    /**
     * The constant to be loaded on the stack. This field must be a non-null {@link Integer}, a {@link
     * Float}, a {@link Long}, a {@link Double}, a {@link String}, a {@link Type} of OBJECT or ARRAY
     * sort for {@code .class} constants, for classes whose version is 49, a {@link Type} of METHOD
     * sort for MethodType, a {@link Handle} for MethodHandle constants, for classes whose version is
     * 51 or a {@link ConstantDynamic} for a constant dynamic for classes whose version is 55.
     */
    private final Object cst;

    /**
     * Constructs a new {@link LdcInsnNode}.
     *
     * @param value the constant to be loaded on the stack. This parameter mist be a non-null {@link
     *              Integer}, a {@link Float}, a {@link Long}, a {@link Double}, a {@link String}, a {@link
     *              Type} of OBJECT or ARRAY sort for {@code .class} constants, for classes whose version is
     *              49, a {@link Type} of METHOD sort for MethodType, a {@link Handle} for MethodHandle
     *              constants, for classes whose version is 51 or a {@link ConstantDynamic} for a constant
     *              dynamic for classes whose version is 55.
     */
    public LdcInsnNode(Object value) {
        super(Opcodes.LDC);

        this.cst = value;
    }

    @Override
    public int getType() {
        return LDC_INSN;
    }

    @Override
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitLdcInsn(this.cst);
        this.acceptAnnotations(methodVisitor);
    }
}

