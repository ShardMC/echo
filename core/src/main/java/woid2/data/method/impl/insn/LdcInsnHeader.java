package woid2.data.method.impl.insn;

import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

public class LdcInsnHeader implements MethodElement {

    private Object value;

    public LdcInsnHeader(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LdcInsnHeader{" +
                "value=" + value +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitLdcInsn(this.value);
    }
}
