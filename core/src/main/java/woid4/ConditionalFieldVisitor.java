package woid4;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

import java.util.function.Predicate;

public class ConditionalFieldVisitor extends FieldVisitor {

    private final Predicate<String> predicate;
    private final Runnable onVisit;

    public ConditionalFieldVisitor(Predicate<String> predicate, Runnable visit, FieldVisitor fv) {
        super(Opcodes.ASM9, fv);

        this.predicate = predicate;
        this.onVisit = visit;
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
    public void visitEnd() {
        if (this.visit) {
            this.onVisit.run();
        }
    }
}
