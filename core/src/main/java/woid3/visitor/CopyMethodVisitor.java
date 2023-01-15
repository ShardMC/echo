package woid3.visitor;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CopyMethodVisitor extends MethodVisitor {

    private final String originalClazz;
    private final String currentClazz;
    private boolean accept = true;

    public CopyMethodVisitor(String originalClazz, String currentClazz, MethodVisitor mv) {
        super(Opcodes.ASM9, mv);

        this.originalClazz = originalClazz;
        this.currentClazz = currentClazz;
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
        if (owner.equals(this.currentClazz)) {
            owner = this.originalClazz;
        }

        super.visitFieldInsn(opcode, owner, name, descriptor);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        if (owner.equals(this.currentClazz)) {
            owner = this.originalClazz;
        }

        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
    }

    @Override
    public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
        if (descriptor.equals("L" + this.currentClazz + ";")) {
            descriptor = "L" + this.originalClazz + ";";
        }

        super.visitLocalVariable(name, descriptor, signature, start, end, index);
    }
}
