package woid.node.clazz;

import org.objectweb.asm.*;
import woid.node.annotation.AnnotationNode;
import woid.node.annotation.TypeAnnotationNode;

import java.util.ArrayList;
import java.util.List;

/**
 * A node that represents a record component.
 *
 * @author Remi Forax
 */
public class RecordComponentNode extends RecordComponentVisitor {

    /**
     * The record component name.
     */
    private final String name;

    /**
     * The record component descriptor (see {@link Type}).
     */
    private final String descriptor;

    /**
     * The record component signature. May be {@literal null}.
     */
    private final String signature;

    /**
     * The runtime annotations of this record component.
     */
    private final List<AnnotationNode> annotations = new ArrayList<>();

    /**
     * The runtime type annotations of this record component.
     */
    private final List<TypeAnnotationNode> typeAnnotations = new ArrayList<>();

    /**
     * The non-standard attributes of this record component. * May be {@literal null}.
     */
    private final List<Attribute> attrs = new ArrayList<>();

    /**
     * Constructs a new {@link RecordComponentNode}. <i>Subclasses must not use this constructor</i>.
     * Instead, they must use the {@link #RecordComponentNode(int, String, String, String)} version.
     *
     * @param name       the record component name.
     * @param descriptor the record component descriptor (see {@link Type}).
     * @param signature  the record component signature.
     */
    public RecordComponentNode(String name, String descriptor, String signature) {
        this(Opcodes.ASM9, name, descriptor, signature);
    }

    /**
     * Constructs a new {@link RecordComponentNode}.
     *
     * @param api        the ASM API version implemented by this visitor. Must be one of {@link Opcodes#ASM8}
     *                   or {@link Opcodes#ASM9}.
     * @param name       the record component name.
     * @param descriptor the record component descriptor (see {@link Type}).
     * @param signature  the record component signature.
     */
    public RecordComponentNode(int api, String name, String descriptor, String signature) {
        super(api);

        this.name = name;
        this.descriptor = descriptor;
        this.signature = signature;
    }

    // -----------------------------------------------------------------------------------------------
    // Implementation of the FieldVisitor abstract class
    // -----------------------------------------------------------------------------------------------

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
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

    // -----------------------------------------------------------------------------------------------
    // Accept methods
    // -----------------------------------------------------------------------------------------------

    /**
     * Makes the given class visitor visit this record component.
     *
     * @param visitor a class visitor.
     */
    public void accept(ClassVisitor visitor) {
        RecordComponentVisitor recordComponentVisitor = visitor.visitRecordComponent(this.name, this.descriptor, this.signature);
        if (recordComponentVisitor == null) {
            return;
        }

        for (AnnotationNode annotation : this.annotations) {
            annotation.accept(recordComponentVisitor.visitAnnotation(annotation.getDesc(), annotation.isVisible()));
        }

        for (TypeAnnotationNode typeAnnotation : this.typeAnnotations) {
            typeAnnotation.accept(
                    recordComponentVisitor.visitTypeAnnotation(
                            typeAnnotation.getTypeRef(), typeAnnotation.getTypePath(), typeAnnotation.getDesc(), typeAnnotation.isVisible())
            );
        }

        // Visit the non-standard attributes.
        for (Attribute attr : this.attrs) {
            recordComponentVisitor.visitAttribute(attr);
        }

        recordComponentVisitor.visitEnd();
    }
}

