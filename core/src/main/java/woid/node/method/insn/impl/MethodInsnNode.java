package woid.node.method.insn.impl;

import org.objectweb.asm.MethodVisitor;
import woid.node.method.insn.AbstractInsnNode;

/**
 * A node that represents a onMethod instruction. A onMethod instruction is an instruction that invokes
 * a onMethod.
 *
 * @author Eric Bruneton
 */
public class MethodInsnNode extends AbstractInsnNode {

    /**
     * The internal name of the onMethod's owner class (see {@link
     * org.objectweb.asm.Type#getInternalName()}).
     *
     * <p>For methods of arrays, e.g., {@code clone()}, the array type descriptor.
     */
    private String owner;

    /**
     * The onMethod's name.
     */
    private final String name;

    /**
     * The onMethod's descriptor (see {@link org.objectweb.asm.Type}).
     */
    private final String desc;

    /**
     * Whether the onMethod's owner class if an interface.
     */
    public final boolean itf;

    /**
     * Constructs a new {@link MethodInsnNode}.
     *
     * @param opcode      the opcode of the type instruction to be constructed. This opcode must be
     *                    INVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC or INVOKEINTERFACE.
     * @param owner       the internal name of the onMethod's owner class (see {@link
     *                    org.objectweb.asm.Type#getInternalName()}).
     * @param name        the onMethod's name.
     * @param descriptor  the onMethod's descriptor (see {@link org.objectweb.asm.Type}).
     * @param isInterface if the onMethod's owner class is an interface.
     */
    public MethodInsnNode(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        super(opcode);

        this.owner = owner;
        this.name = name;
        this.desc = descriptor;
        this.itf = isInterface;
    }

    @Override
    public int getType() {
      return METHOD_INSN;
    }

    @Override
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitMethodInsn(this.opcode, this.owner, this.name, this.desc, this.itf);
        this.acceptAnnotations(methodVisitor);
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
