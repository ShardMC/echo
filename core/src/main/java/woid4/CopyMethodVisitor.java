package woid4;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CopyMethodVisitor extends MethodVisitor {

    private final String original;
    private final String current;
    private boolean accept = true;

    public CopyMethodVisitor(MethodContext context, ClassHeader current) {
        super(Opcodes.ASM9, context.visit());

        this.original = context.getClassHeader().getName();
        this.current = current.getName();
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (this.accept && descriptor.equals("Lio/shardmc/echo/annotations/Shadow;")) {
            this.accept = false;
        }

        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        if (owner.equals(this.current)) {
            owner = this.original;
        }

        super.visitFieldInsn(opcode, owner, name, descriptor);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        if (owner.equals(this.current)) {
            owner = this.original;
        }

        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
    }

    @Override
    public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
        if (descriptor.equals("L" + this.current + ";")) {
            descriptor = "L" + this.original + ";";
        }

        super.visitLocalVariable(name, descriptor, signature, start, end, index);
    }
}
