package woid3.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import woid3.header.FieldHeader;
import woid3.header.MethodHeader;

public class CopyClassVisitor extends SimpleClassVisitor {

    private final SimpleClassVisitor original;
    private final FieldDispatcher fieldDispatcher;

    public CopyClassVisitor(SimpleClassVisitor original, ClassDispatcher dispatcher) {
        this(original, dispatcher, null);
    }

    public CopyClassVisitor(SimpleClassVisitor original, ClassDispatcher dispatcher, ClassVisitor classVisitor) {
        super(classVisitor);

        this.original = original;
        this.fieldDispatcher = dispatcher.getFieldDispatcher();
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        FieldHeader header = this.fieldDispatcher.onField(new FieldHeader(access, name, descriptor, signature, value));

        return new ConditionalFieldVisitor(
                this.fieldDispatcher, header, super.visitField(access, name, descriptor, signature, value)
        );
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodHeader header = new MethodHeader(access, name, descriptor, signature, exceptions);

        if (!header.getName().equals("<init>")) {
            return new ConditionalMethodVisitor(
                    s -> !s.startsWith("Lio/shardmc/echo/annotations"),
                    () -> new CopyMethodVisitor(
                            this.original.getHeader().getName(), this.getHeader().getName(),
                            this.original.visitMethod(
                                    header.getAccess(),
                                    header.getName(),
                                    header.getDescriptor(),
                                    header.getSignature(),
                                    header.getExceptions()
                            )
                    ), super.visitMethod(access, name, descriptor, signature, exceptions));
        }

        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
}
