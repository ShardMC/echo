package woid4;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.function.Function;

public class APMethodVisitor extends MethodVisitor {

    private final String prefix;
    private final Function<String, MethodVisitor> delegate;

    public APMethodVisitor(String prefix, Function<String, MethodVisitor> delegate, MethodVisitor mv) {
        super(Opcodes.ASM9, mv);

        this.prefix = prefix;
        this.delegate = delegate;
    }

    private String annotation;
    private boolean done = false;

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (!this.done && descriptor.startsWith(this.prefix)) {
            this.annotation = descriptor.replace(this.prefix, "").replace(";", "");
            this.done = true;
        }

        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public void visitCode() {
        this.mv = this.delegate.apply(this.annotation);

        super.visitCode();
    }
}
