package woid5;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

public class ClassDispatcher extends ClassVisitor {

    private final ClassTransformer transformer;

    public ClassDispatcher(ClassTransformer transformer) {
        this(transformer, null);
    }

    public ClassDispatcher(ClassTransformer transformer, ClassVisitor cv) {
        super(Opcodes.ASM9, cv);

        this.transformer = transformer;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        FieldTransformer ft = transformer.field();
        if (ft != null) {
            FieldHeader header = ft.define(new FieldHeader(access, name, descriptor, signature, value));

        }

        return super.visitField(access, name, descriptor, signature, value);
    }
}
