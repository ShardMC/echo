package woid8;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class APMethodVisitor extends MethodVisitor {

    private final List<String> annotations = new ArrayList<>();
    private final Consumer<APMethodVisitor> callback;
    private final Predicate<String> predicate;

    public APMethodVisitor(Predicate<String> predicate, Consumer<APMethodVisitor> callback, MethodVisitor mv) {
        super(Opcodes.ASM9, mv);

        this.predicate = predicate;
        this.callback = callback;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (this.predicate.test(descriptor)) {
            this.annotations.add(descriptor);
        }

        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public void visitCode() {
        this.callback.accept(this);
        super.visitCode();
    }

    public void setDelegate(MethodVisitor mv) {
        this.mv = mv;
    }

    public List<String> getAnnotations() {
        return this.annotations;
    }
}
