package woid2.event;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;

public class AnnotationMethodVisitor extends MethodVisitor {

    private final List<String> annotations = new ArrayList<>();

    public AnnotationMethodVisitor(MethodVisitor methodVisitor) {
        super(Opcodes.ASM9, methodVisitor);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        return super.visitAnnotation(descriptor, visible);
    }

    public List<String> getAnnotations() {
        return this.annotations;
    }
}
