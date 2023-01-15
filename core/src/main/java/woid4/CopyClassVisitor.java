package woid4;

import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class CopyClassVisitor extends SimpleClassVisitor {

    private final SimpleClassVisitor original;

    public CopyClassVisitor(SimpleClassVisitor original) {
        super(null);

        this.original = original;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        return new ConditionalFieldVisitor(
                s -> !s.equals("Lio/shardmc"),
                () -> this.original.visitField(access, name, descriptor, signature, value),
                super.visitField(access, name, descriptor, signature, value)
        );
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        return new APMethodVisitor(
                "Lio/shardmc/echo/annotations/",
                s -> {
                    if (s != null && s.equals("Add;")) {
                        System.out.println("Add!");
                    }

                    return new CopyMethodVisitor(
                            new MethodContext(this.original, access, name, descriptor, signature, exceptions), this.getHeader()
                    );
                }, super.visitMethod(access, name, descriptor, signature, exceptions)
        );
    }
}
