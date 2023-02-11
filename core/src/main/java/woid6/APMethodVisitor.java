package woid6;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import woid6.event.EventfulMethodVisitor;

import java.util.function.Predicate;

public class APMethodVisitor extends EventfulMethodVisitor {

    private final Predicate<String> predicate;

    public APMethodVisitor(Predicate<String> predicate, MethodVisitor mv) {
        super(mv);

        this.predicate = predicate;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (this.predicate.test(descriptor)) {
            this.annotations.add(descriptor);
        }

        return super.visitAnnotation(descriptor, visible);
    }
}
