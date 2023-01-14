package woid2.data.method.impl.insn;

import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

public class IincInsnHeader implements MethodElement {

    private int varIndex;
    private int increment;

    public IincInsnHeader(int varIndex, int increment) {
        this.varIndex = varIndex;
        this.increment = increment;
    }

    public int getVarIndex() {
        return this.varIndex;
    }

    public void setVarIndex(int varIndex) {
        this.varIndex = varIndex;
    }

    public int getIncrement() {
        return this.increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    @Override
    public String toString() {
        return "IincInsnHeader{" +
                "varIndex=" + varIndex +
                ", increment=" + increment +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitIincInsn(this.varIndex, this.increment);
    }
}
