package woid4;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ConditionalMethodVisitor extends MethodVisitor {

    private final Predicate<String> predicate;
    private final Function<Boolean, MethodVisitor> delegate;

    public ConditionalMethodVisitor(Predicate<String> predicate, Function<Boolean, MethodVisitor> delegate, MethodVisitor mv) {
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
        this.mv = this.delegate.apply(this.visit);
        super.visitCode();
    }
}
