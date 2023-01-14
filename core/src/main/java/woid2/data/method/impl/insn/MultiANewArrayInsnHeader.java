package woid2.data.method.impl.insn;

import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

public class MultiANewArrayInsnHeader implements MethodElement {

    private String descriptor;
    private int numDimensions;

    public MultiANewArrayInsnHeader(String descriptor, int numDimensions) {
        this.descriptor = descriptor;
        this.numDimensions = numDimensions;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public int getNumDimensions() {
        return this.numDimensions;
    }

    public void setNumDimensions(int numDimensions) {
        this.numDimensions = numDimensions;
    }

    @Override
    public String toString() {
        return "MultiANewArrayInsnHeader{" +
                "descriptor='" + descriptor + '\'' +
                ", numDimensions=" + numDimensions +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitMultiANewArrayInsn(this.descriptor, this.numDimensions);
    }
}
