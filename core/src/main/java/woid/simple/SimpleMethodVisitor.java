package woid.simple;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SimpleMethodVisitor extends MethodVisitor {

    private final int access;
    private final String name;
    private final String descriptor;
    private final String signature;
    private final String[] exceptions;

    public SimpleMethodVisitor(int access, String name, String descriptor, String signature, String[] exceptions) {
        this(access, name, descriptor, signature, exceptions, null);
    }

    public SimpleMethodVisitor(int access, String name, String descriptor, String signature, String[] exceptions, MethodVisitor parent) {
        super(Opcodes.ASM9, parent);

        this.access = access;
        this.name = name;
        this.descriptor = descriptor;
        this.signature = signature;
        this.exceptions = exceptions;
    }

    public int getAccess() {
        return this.access;
    }

    public String getName() {
        return this.name;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public String getSignature() {
        return this.signature;
    }

    public String[] getExceptions() {
        return this.exceptions;
    }
}
