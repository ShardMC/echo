package woid4;

import org.objectweb.asm.FieldVisitor;

public class CopyClassVisitor extends SimpleClassVisitor {

    private final SimpleClassVisitor original;
    private final ClassDispatcher dispatcher;

    public CopyClassVisitor(SimpleClassVisitor original, ClassDispatcher dispatcher) {
        super(null);

        this.original = original;
        this.dispatcher = dispatcher;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        FieldHeader header = this.dispatcher.defineField(new FieldHeader(access, name, descriptor, signature, value));

        return new ConditionalFieldVisitor(
                s -> !s.equals("Lio/shardmc/echo/annotations/Shadow;"),
                () -> this.original.visitField(
                        header.getAccess(),
                        header.getName(),
                        header.getDescriptor(),
                        header.getSignature(),
                        header.getValue()
                ), super.visitField(access, name, descriptor, signature, value));
    }
}
