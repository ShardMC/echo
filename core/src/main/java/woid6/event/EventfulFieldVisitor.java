package woid6.event;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventfulFieldVisitor extends FieldVisitor {

    private final List<String> annotations = new ArrayList<>();
    private Consumer<EventfulFieldVisitor> onEnd;

    public EventfulFieldVisitor(FieldVisitor fv) {
        super(Opcodes.ASM9, fv);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        this.annotations.add(descriptor);
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public void visitEnd() {
        this.onEnd.accept(this);
        super.visitEnd();
    }

    public EventfulFieldVisitor onEnd(Consumer<EventfulFieldVisitor> onEnd) {
        this.onEnd = onEnd;
        return this;
    }

    public List<String> getAnnotations() {
        return this.annotations;
    }
}
