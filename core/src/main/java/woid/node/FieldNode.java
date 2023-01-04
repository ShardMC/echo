package woid.node;

import org.objectweb.asm.*;
import woid.node.annotation.AnnotationNode;
import woid.node.annotation.TypeAnnotationNode;
import woid.simple.SimpleFieldVisitor;

import java.util.ArrayList;
import java.util.List;

public class FieldNode extends SimpleFieldVisitor {

    private final List<Attribute> attrs = new ArrayList<>();
    private final List<AnnotationNode> annotations = new ArrayList<>();
    private final List<TypeAnnotationNode> typeAnnotations = new ArrayList<>();

    public FieldNode(int access, String name, String descriptor, String signature, Object value) {
        super(access, name, descriptor, signature, value);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String descriptor, final boolean visible) {
        AnnotationNode annotation = new AnnotationNode(descriptor, visible);
        this.annotations.add(annotation);

        return annotation;
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        TypeAnnotationNode typeAnnotation = new TypeAnnotationNode(typeRef, typePath, descriptor, visible);
        this.typeAnnotations.add(typeAnnotation);

        return typeAnnotation;
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        this.attrs.add(attribute);
    }

    public void accept(ClassVisitor classVisitor) {
        FieldVisitor fieldVisitor = classVisitor.visitField(this.access, this.name, this.descriptor, this.signature, this.value);
        if (fieldVisitor == null) {
            return;
        }

        // Visit the annotations.
        for (AnnotationNode annotation : this.annotations) {
            annotation.accept(fieldVisitor.visitAnnotation(annotation.getDesc(), annotation.isVisible()));
        }

        for (TypeAnnotationNode typeAnnotation : this.typeAnnotations) {
            typeAnnotation.accept(
                    fieldVisitor.visitTypeAnnotation(typeAnnotation.getTypeRef(), typeAnnotation.getTypePath(), typeAnnotation.getDesc(), typeAnnotation.isVisible())
            );
        }

        // Visit the non-standard attributes.
        for (Attribute attr : attrs) {
            fieldVisitor.visitAttribute(attr);
        }

        fieldVisitor.visitEnd();
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.descriptor;
    }

    public List<AnnotationNode> getAnnotations() {
        return this.annotations;
    }
}
