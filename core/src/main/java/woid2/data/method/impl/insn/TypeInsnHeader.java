package woid2.data.method.impl.insn;

import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

public class TypeInsnHeader implements MethodElement {

    private int opcode;
    private String type;

    public TypeInsnHeader(int opcode, String type) {
        this.opcode = opcode;
        this.type = type;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TypeInsnHeader{" +
                "opcode=" + opcode +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitTypeInsn(this.opcode, this.type);
    }
}
