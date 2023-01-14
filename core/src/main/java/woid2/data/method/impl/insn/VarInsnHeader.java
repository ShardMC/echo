package woid2.data.method.impl.insn;

import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

public class VarInsnHeader implements MethodElement {

    private int opcode;
    private int varIndex;

    public VarInsnHeader(int opcode, int varIndex) {
        this.opcode = opcode;
        this.varIndex = varIndex;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public int getVarIndex() {
        return this.varIndex;
    }

    public void setVarIndex(int varIndex) {
        this.varIndex = varIndex;
    }

    @Override
    public String toString() {
        return "VarInsnHeader{" +
                "opcode=" + opcode +
                ", varIndex=" + varIndex +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitVarInsn(this.opcode, this.varIndex);
    }
}
