package woid2.event;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import woid2.Visitor;
import woid2.data.field.FieldHeader;

import java.util.ArrayList;
import java.util.List;

public class FieldEventer extends FieldVisitor {

    private final List<String> annotations = new ArrayList<>();
    private final Visitor visitor;
    private final FieldHeader header;

    public FieldEventer(FieldVisitor field, Visitor visitor, FieldHeader header) {
        super(Opcodes.ASM9, field);

        this.visitor = visitor;
        this.header = header;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        this.annotations.add(descriptor);

        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public void visitEnd() {
        this.visitor.onField(this.getAnnotations(), this.header);
        super.visitEnd();
    }

    public List<String> getAnnotations() {
        return this.annotations;
    }
}
