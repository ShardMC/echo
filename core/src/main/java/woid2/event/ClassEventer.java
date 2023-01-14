package woid2.event;

import org.objectweb.asm.*;
import woid2.Visitor;
import woid2.VisitorMethod;
import woid2.data.clazz.ClassHeader;
import woid2.data.field.FieldHeader;
import woid2.data.method.MethodHeader;

public class ClassEventer extends ClassVisitor {

    private final Visitor visitor;
    private ClassHeader header;

    public ClassEventer(Visitor visitor) {
        super(Opcodes.ASM9);

        this.visitor = visitor;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.header = this.visitor.onClassHeader(new ClassHeader(version, access, name, signature, superName, interfaces));

        super.visit(
                this.header.getVersion(),
                this.header.getAccess(),
                this.header.getName(),
                this.header.getSignature(),
                this.header.getSuperName(),
                this.header.getInterfaces()
        );
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        FieldHeader header = this.visitor.onFieldHeader(new FieldHeader(access, name, descriptor, signature, value));

        return new FieldEventer(
                super.visitField(
                        header.getAccess(),
                        header.getName(),
                        header.getDescriptor(),
                        header.getSignature(),
                        header.getValue()
                ),

                this.visitor, header
        );
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodHeader header = this.visitor.onMethodHeader(new MethodHeader(access, name, descriptor, signature, exceptions));
        AnnotationMethodVisitor mv = new AnnotationMethodVisitor(super.visitMethod(header.getAccess(), header.getName(), header.getDescriptor(), header.getSignature(), header.getExceptions()));

        VisitorMethod method = this.visitor.onMethod(mv.getAnnotations(), header);

        if (method != null) {
            return new MethodEventer(mv, method);
        }

        return mv;
    }

    @Override
    public void visitEnd() {
        this.visitor.onClass(this, this.header);
        super.visitEnd();
    }
}
