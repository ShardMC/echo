package woid2.data.method.impl;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

public class LocalVariableHeader implements MethodElement {

    private String name;
    private String descriptor;
    private String signature;
    private Label start;
    private Label end;
    private int index;

    public LocalVariableHeader(String name, String descriptor, String signature, Label start, Label end, int index) {
        this.name = name;
        this.descriptor = descriptor;
        this.signature = signature;
        this.start = start;
        this.end = end;
        this.index = index;
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

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Label getStart() {
        return this.start;
    }

    public void setStart(Label start) {
        this.start = start;
    }

    public Label getEnd() {
        return this.end;
    }

    public void setEnd(Label end) {
        this.end = end;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "LocalVariableHeader{" +
                "name='" + name + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", signature='" + signature + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", index=" + index +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitLocalVariable(this.name, this.descriptor, this.signature, this.start, this.end, this.index);
    }
}
