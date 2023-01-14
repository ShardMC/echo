package woid2.data.method.impl;

import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

public class ParameterHeader implements MethodElement {

    private String name;
    private int access;

    public ParameterHeader(String name, int access) {
        this.name = name;
        this.access = access;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccess() {
        return this.access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    @Override
    public String toString() {
        return "ParameterHeader{" +
                "name='" + name + '\'' +
                ", access=" + access +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitParameter(this.name, this.access);
    }
}
