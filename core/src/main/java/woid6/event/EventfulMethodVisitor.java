package woid6.event;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventfulMethodVisitor extends MethodVisitor {

    protected final List<String> annotations = new ArrayList<>();
    private Consumer<EventfulMethodVisitor> onCode;

    public EventfulMethodVisitor(MethodVisitor mv) {
        super(Opcodes.ASM9, mv);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        this.annotations.add(descriptor);
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public void visitCode() {
        this.onCode.accept(this);
        super.visitCode();
    }

    public EventfulMethodVisitor onCode(Consumer<EventfulMethodVisitor> onCode) {
        this.onCode = onCode;
        return this;
    }

    public List<String> getAnnotations() {
        return this.annotations;
    }

    public void setDelegate(MethodVisitor delegate) {
        this.mv = delegate;
    }
}
