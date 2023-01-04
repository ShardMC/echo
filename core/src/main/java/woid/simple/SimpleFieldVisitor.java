package woid.simple;

import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

public class SimpleFieldVisitor extends FieldVisitor {

    public int access;
    public String name;
    public String descriptor;
    public String signature;
    public Object value;

    public SimpleFieldVisitor(int access, String name, String descriptor, String signature, Object value) {
        this(access, name, descriptor, signature, value, null);
    }

    public SimpleFieldVisitor(int access, String name, String descriptor, String signature, Object value, FieldVisitor parent) {
        super(Opcodes.ASM9, parent);

        this.access = access;
        this.name = name;
        this.descriptor = descriptor;
        this.signature = signature;
        this.value = value;
    }
}
