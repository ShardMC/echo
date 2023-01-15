package woid3.visitor;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import woid3.header.FieldHeader;

import java.util.ArrayList;
import java.util.List;

public class ConditionalFieldVisitor extends FieldVisitor {

    private final List<String> annotations = new ArrayList<>();
    private final FieldDispatcher dispatcher;
    private final FieldHeader header;

    public ConditionalFieldVisitor(FieldDispatcher dispatcher, FieldHeader header, FieldVisitor fv) {
        super(Opcodes.ASM9, fv);

        this.dispatcher = dispatcher;
        this.header = header;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        this.annotations.add(descriptor);
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public void visitEnd() {
        this.dispatcher.withAnnotations(this.header, this.annotations);
    }
}
