package woid2.data.method.impl.insn;

import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

public class FieldInsnHeader implements MethodElement {

    private int opcode;
    private String owner;
    private String name;
    private String descriptor;

    public FieldInsnHeader(int opcode, String owner, String name, String descriptor) {
        this.opcode = opcode;
        this.owner = owner;
        this.name = name;
        this.descriptor = descriptor;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public String toString() {
        return "FieldInsnHeader{" +
                "opcode=" + opcode +
                ", owner='" + owner + '\'' +
                ", name='" + name + '\'' +
                ", descriptor='" + descriptor + '\'' +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitFieldInsn(this.opcode, this.owner, this.name, this.descriptor);
    }
}
