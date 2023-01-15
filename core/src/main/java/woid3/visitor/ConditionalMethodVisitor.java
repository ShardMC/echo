package woid3.visitor;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class ConditionalMethodVisitor extends MethodVisitor {

    private final Predicate<String> predicate;
    private final Supplier<MethodVisitor> delegate;

    public ConditionalMethodVisitor(Predicate<String> predicate, Supplier<MethodVisitor> delegate, MethodVisitor mv) {
        super(Opcodes.ASM9, mv);

        this.predicate = predicate;
        this.delegate = delegate;
    }

    boolean visit = true;

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (!this.predicate.test(descriptor)) {
            this.visit = false;
        }

        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public void visitCode() {
        if (this.visit) {
            this.mv = this.delegate.get();
        }

        super.visitCode();
    }
}
