package woid2.data.method.impl.insn;

import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

public class InsnHeader implements MethodElement {

    private int opcode;

    public InsnHeader(int opcode) {
        this.opcode = opcode;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    @Override
    public String toString() {
        return "InsnHeader{" +
                "opcode=" + opcode +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitInsn(this.opcode);
    }
}
