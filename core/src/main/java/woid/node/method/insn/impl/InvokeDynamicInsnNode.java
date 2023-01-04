package woid.node.method.insn.impl;

import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import woid.node.method.insn.AbstractInsnNode;

/**
 * A node that represents an invokedynamic instruction.
 *
 * @author Remi Forax
 */
public class InvokeDynamicInsnNode extends AbstractInsnNode {

    /**
     * The method's name.
     */
    private final String name;

    /**
     * The method's descriptor (see {@link org.objectweb.asm.Type}).
     */
    private final String desc;

    /**
     * The bootstrap method.
     */
    private final Handle bsm;

    /**
     * The bootstrap method constant arguments.
     */
    private final Object[] bsmArgs;

    /**
     * Constructs a new {@link InvokeDynamicInsnNode}.
     *
     * @param name                     the method's name.
     * @param descriptor               the method's descriptor (see {@link org.objectweb.asm.Type}).
     * @param bootstrapMethodHandle    the bootstrap method.
     * @param bootstrapMethodArguments the bootstrap method constant arguments. Each argument must be
     *                                 an {@link Integer}, {@link Float}, {@link Long}, {@link Double}, {@link String}, {@link
     *                                 org.objectweb.asm.Type} or {@link Handle} value. This method is allowed to modify the
     *                                 content of the array so a caller should expect that this array may change.
     */
    public InvokeDynamicInsnNode(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
        super(Opcodes.INVOKEDYNAMIC);

        this.name = name;
        this.desc = descriptor;
        this.bsm = bootstrapMethodHandle;
        this.bsmArgs = bootstrapMethodArguments;
    }

    @Override
    public int getType() {
        return INVOKE_DYNAMIC_INSN;
    }

    @Override
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitInvokeDynamicInsn(this.name, this.desc, this.bsm, this.bsmArgs);
        this.acceptAnnotations(methodVisitor);
    }
}

