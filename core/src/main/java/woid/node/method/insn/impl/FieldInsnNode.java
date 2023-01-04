package woid.node.method.insn.impl;

import org.objectweb.asm.MethodVisitor;
import woid.node.method.insn.AbstractInsnNode;

/**
 * A node that represents a field instruction. A field instruction is an instruction that loads or
 * stores the value of a field of an object.
 *
 * @author Eric Bruneton
 */
public class FieldInsnNode extends AbstractInsnNode {

    /**
     * The internal name of the field's owner class (see {@link
     * org.objectweb.asm.Type#getInternalName()}).
    */
    private String owner;

    /**
     * The field's name.
    */
    private final String name;

    /**
     * The field's descriptor (see {@link org.objectweb.asm.Type}).
    */
    private final String desc;

    /**
     * Constructs a new {@link FieldInsnNode}.
     *
     * @param opcode     the opcode of the type instruction to be constructed. This opcode must be
     *                   GETSTATIC, PUTSTATIC, GETFIELD or PUTFIELD.
     * @param owner      the internal name of the field's owner class (see {@link
     *                   org.objectweb.asm.Type#getInternalName()}).
     * @param name       the field's name.
     * @param descriptor the field's descriptor (see {@link org.objectweb.asm.Type}).
    */
    public FieldInsnNode(int opcode, String owner, String name, String descriptor) {
        super(opcode);

        this.owner = owner;
        this.name = name;
        this.desc = descriptor;
    }

    /**
     * Sets the opcode of this instruction.
     *
     * @param opcode the new instruction opcode. This opcode must be GETSTATIC, PUTSTATIC, GETFIELD or
     *               PUTFIELD.
    */
    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    @Override
    public int getType() {
        return FIELD_INSN;
    }

    @Override
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitFieldInsn(opcode, owner, name, desc);
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

