package woid2.data.method.impl;

import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

import java.util.Arrays;

public class FrameHeader implements MethodElement {

    private int type;
    private int numLocal;
    private Object[] local;
    private int numStack;
    private Object[] stack;

    public FrameHeader(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
        this.type = type;
        this.numLocal = numLocal;
        this.local = local;
        this.numStack = numStack;
        this.stack = stack;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNumLocal() {
        return this.numLocal;
    }

    public void setNumLocal(int numLocal) {
        this.numLocal = numLocal;
    }

    public Object[] getLocal() {
        return this.local;
    }

    public void setLocal(Object[] local) {
        this.local = local;
    }

    public int getNumStack() {
        return this.numStack;
    }

    public void setNumStack(int numStack) {
        this.numStack = numStack;
    }

    public Object[] getStack() {
        return this.stack;
    }

    public void setStack(Object[] stack) {
        this.stack = stack;
    }

    @Override
    public String toString() {
        return "FrameHeader{" +
                "type=" + type +
                ", numLocal=" + numLocal +
                ", local=" + Arrays.toString(local) +
                ", numStack=" + numStack +
                ", stack=" + Arrays.toString(stack) +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitFrame(this.type, this.numLocal, this.local, this.numStack, this.stack);
    }
}
