package woid2.data.method.impl;

import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

public class MaxsHeader implements MethodElement {

    private int maxStack;
    private int maxLocals;

    public MaxsHeader(int maxStack, int maxLocals) {
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }

    @Override
    public String toString() {
        return "MaxsHeader{" +
                "maxStack=" + maxStack +
                ", maxLocals=" + maxLocals +
                '}';
    }


    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitMaxs(this.maxStack, this.maxLocals);
    }
}
