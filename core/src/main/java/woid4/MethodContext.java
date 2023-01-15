package woid4;

import org.objectweb.asm.MethodVisitor;

public record MethodContext(SimpleClassVisitor visitor, MethodHeader method) {

    public MethodContext(SimpleClassVisitor visitor, int access, String name, String descriptor, String signature, String[] exceptions) {
        this(visitor, new MethodHeader(access, name, descriptor, signature, exceptions));
    }

    public MethodVisitor visit() {
        return this.visitor.visitMethod(
                this.method.getAccess(),
                this.method.getName(),
                this.method.getDescriptor(),
                this.method.getSignature(),
                this.method.getExceptions()
        );
    }

    public ClassHeader getClassHeader() {
        return this.visitor.getHeader();
    }
}
