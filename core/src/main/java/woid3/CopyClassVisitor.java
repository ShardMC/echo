package woid3;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class CopyClassVisitor extends SimpleClassVisitor {

    private final SimpleClassVisitor original;

    public CopyClassVisitor(SimpleClassVisitor original) {
        this(original, null);
    }

    public CopyClassVisitor(SimpleClassVisitor original, ClassVisitor classVisitor) {
        super(classVisitor);

        this.original = original;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        FieldHeader header = new FieldHeader(access, name, descriptor, signature, value);

        return new PreFieldVisitor(
                s -> !s.equals("Lio/shardmc/echo/annotations/Shadow;"),
                () -> this.original.visitField(
                        header.getAccess(),
                        header.getName(),
                        header.getDescriptor(),
                        header.getSignature(),
                        header.getValue()
                ),
                super.visitField(access, name, descriptor, signature, value)
        );
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodHeader header = new MethodHeader(access, name, descriptor, signature, exceptions);

        if (!header.getName().equals("<init>")) {
            return new PreMethodVisitor(
                    s -> !s.equals("Lio/shardmc/echo/annotations/Shadow;"),
                    () -> new CopyMethodVisitor(
                            this.original.getName(), this.getName(),
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
