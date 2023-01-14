package woid2.data.method.impl.insn;

import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

public class MethodInsnHeader implements MethodElement {

    private int opcode;
    private String owner;
    private String name;
    private String descriptor;
    private boolean isInterface;

    public MethodInsnHeader(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        this.opcode = opcode;
        this.owner = owner;
        this.name = name;
        this.descriptor = descriptor;
        this.isInterface = isInterface;
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

    public boolean isInterface() {
        return this.isInterface;
    }

    public void setInterface(boolean anInterface) {
        this.isInterface = anInterface;
    }

    @Override
    public String toString() {
        return "MethodInsnHeader{" +
                "opcode=" + opcode +
                ", owner='" + owner + '\'' +
                ", name='" + name + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", isInterface=" + isInterface +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitMethodInsn(this.opcode, this.owner, this.name, this.descriptor, this.isInterface);
    }
}
