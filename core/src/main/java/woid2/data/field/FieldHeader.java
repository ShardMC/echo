package woid2.data.field;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import woid2.data.clazz.ClassElement;

public class FieldHeader implements ClassElement<FieldVisitor> {

    private int access;
    private String name;
    private String descriptor;
    private String signature;
    private Object value;

    public FieldHeader(int access, String name, String descriptor, String signature, Object value) {
        this.access = access;
        this.name = name;
        this.descriptor = descriptor;
        this.signature = signature;
        this.value = value;
    }

    public int getAccess() {
        return this.access;
    }

    public void setAccess(int access) {
        this.access = access;
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

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FieldHeader{" +
                "access=" + access +
                ", name='" + name + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", signature='" + signature + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public FieldVisitor visit(ClassVisitor visitor) {
        return visitor.visitField(this.getAccess(), this.getName(), this.getDescriptor(), this.getSignature(), this.getValue());
    }
}
