package woid.node;

import org.objectweb.asm.*;
import woid.node.annotation.AnnotationNode;
import woid.node.annotation.TypeAnnotationNode;
import woid.node.clazz.InnerClassNode;
import woid.node.clazz.RecordComponentNode;
import woid.simple.SimpleClassVisitor;

import java.util.ArrayList;
import java.util.List;

public class ClassNode extends SimpleClassVisitor {

    private int version;
    private int access;
    private String name;
    private String signature;
    private String superName;
    private String[] interfaces;

    private String file;
    private String debug;
    private ModuleNode module;
    private String nestHostClass;

    private String outerClass;
    private String outerMethod;
    private String outerMethodDesc;

    private final List<AnnotationNode> annotations = new ArrayList<>();
    private final List<TypeAnnotationNode> typeAnnotations = new ArrayList<>();
    
    private final List<String> nestMembers = new ArrayList<>();
    private final List<Attribute> attributes = new ArrayList<>();
    private final List<String> permittedSubclasses = new ArrayList<>();
    private final List<InnerClassNode> innerClasses = new ArrayList<>();
    private final List<RecordComponentNode> recordComponents = new ArrayList<>();

    private final List<FieldNode> fields = new ArrayList<>();
    private final List<MethodNode> methods = new ArrayList<>();

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.version = version;
        this.access = access;
        this.name = name;
        this.signature = signature;
        this.superName = superName;
        this.interfaces = interfaces;
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    @Override
    public void visitNestMember(String nestMember) {
        this.nestMembers.add(nestMember);
    }

    @Override
    public void visitPermittedSubclass(String permittedSubclass) {
        this.permittedSubclasses.add(permittedSubclass);
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        this.innerClasses.add(new InnerClassNode(name, outerName, innerName, access));
    }

    @Override
    public RecordComponentVisitor visitRecordComponent(String name, String descriptor, String signature) {
        RecordComponentNode recordComponent = new RecordComponentNode(name, descriptor, signature);
        this.recordComponents.add(recordComponent);

        return recordComponent;
    }

    @Override
    public void visitSource(String file, String debug) {
        this.file = file;
        this.debug = debug;
    }

    @Override
    public ModuleVisitor visitModule(String name, int access, String version) {
        this.module = new ModuleNode(name, access, version);
        return this.module;
    }

    @Override
    public void visitNestHost(String nestHost) {
        this.nestHostClass = nestHost;
    }

    @Override
    public void visitOuterClass(String owner, String name, String descriptor) {
        this.outerClass = owner;
        this.outerMethod = name;
        this.outerMethodDesc = descriptor;
    }
    
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
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        FieldNode field = new FieldNode(access, name, descriptor, signature, value);
        this.fields.add(field);

        return field;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodNode field = new MethodNode(access, name, descriptor, signature, exceptions);
        this.methods.add(field);

        return field;
    }

    public void accept(ClassVisitor classVisitor) {
        // Visit the header.
        classVisitor.visit(this.version, this.access, this.name, this.signature, this.superName, this.interfaces);

        // Visit the source.
        if (this.file != null || this.debug != null) {
            classVisitor.visitSource(this.file, this.debug);
        }

        // Visit the module.
        if (this.module != null) {
            this.module.accept(classVisitor);
        }

        // Visit the nest host class.
        if (this.nestHostClass != null) {
            classVisitor.visitNestHost(nestHostClass);
        }

        // Visit the outer class.
        if (this.outerClass != null) {
            classVisitor.visitOuterClass(outerClass, outerMethod, outerMethodDesc);
        }

        for (AnnotationNode annotation : this.annotations) {
            annotation.accept(classVisitor.visitAnnotation(annotation.getDesc(), annotation.isVisible()));
        }

        for (TypeAnnotationNode typeAnnotation : this.typeAnnotations) {
            typeAnnotation.accept(
                    classVisitor.visitTypeAnnotation(typeAnnotation.getTypeRef(), typeAnnotation.getTypePath(), typeAnnotation.getDesc(), typeAnnotation.isVisible())
            );
        }

        // Visit the non-standard attributes.
        for (Attribute attr : this.attributes) {
            classVisitor.visitAttribute(attr);
        }

        // Visit the nest members.
        for (String nestMember : this.nestMembers) {
            classVisitor.visitNestMember(nestMember);
        }

        // Visit the permitted subclasses.
        for (String permittedSubclass : this.permittedSubclasses) {
            classVisitor.visitPermittedSubclass(permittedSubclass);
        }

        // Visit the inner classes.
        for (InnerClassNode innerClass : this.innerClasses) {
            innerClass.accept(classVisitor);
        }

        // Visit the record components.
        for (RecordComponentNode recordComponent : this.recordComponents) {
            recordComponent.accept(classVisitor);
        }

        // Visit the fields.
        for (FieldNode field : this.fields) {
            field.accept(classVisitor);
        }

        // Visit the methods.
        for (MethodNode method : this.methods) {
            method.accept(classVisitor);
        }

        classVisitor.visitEnd();
    }

    public int getVersion() {
        return this.version;
    }

    public String getName() {
        return this.name;
    }

    public String getSignature() {
        return this.signature;
    }

    public String getSuperName() {
        return this.superName;
    }

    public String[] getInterfaces() {
        return this.interfaces;
    }

    public List<MethodNode> getMethods() {
        return this.methods;
    }

    public List<FieldNode> getFields() {
        return this.fields;
    }
}
