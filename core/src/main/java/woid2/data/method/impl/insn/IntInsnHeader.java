package woid2.data.method.impl.insn;

import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

public class IntInsnHeader implements MethodElement {

    private int opcode;
    private int operand;

    public IntInsnHeader(int opcode, int operand) {
        this.opcode = opcode;
        this.operand = operand;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public int getOperand() {
        return this.operand;
    }

    public void setOperand(int operand) {
        this.operand = operand;
    }

    @Override
    public String toString() {
        return "IntInsnHeader{" +
                "opcode=" + opcode +
                ", operand=" + operand +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitIntInsn(this.opcode, this.operand);
    }
}
