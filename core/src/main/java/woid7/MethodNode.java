package woid7;

import org.objectweb.asm.*;

import java.util.function.Consumer;

public class MethodNode extends MethodVisitor {

    private final Actions<MethodVisitor> actions = new Actions<>();

    public MethodNode(MethodVisitor mv) {
        super(Opcodes.ASM9, mv);
    }

    public void apply(MethodVisitor mv) {
        for (Consumer<MethodVisitor> action : this.actions.get()) {
            action.accept(mv);
        }
    }

    public void visitParameter(String name, int access) {
        this.actions.add(mv -> mv.visitParameter(name, access));
    }

    /*public AnnotationVisitor visitAnnotationDefault() {
        return mv.visitAnnotationDefault();
    }

    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        return mv.visitAnnotation(descriptor, visible);
    }

    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        return mv.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
    }

    public void visitAnnotableParameterCount(int parameterCount, boolean visible) {

    }

    public AnnotationVisitor visitParameterAnnotation(int parameter, String descriptor, boolean visible) {
        return mv.visitParameterAnnotation(parameter, descriptor, visible);
    }*/

    public void visitAttribute(Attribute attribute) {
        this.actions.add(mv -> mv.visitAttribute(attribute));
    }

    public void visitCode() {
        this.actions.add(MethodVisitor::visitCode);
    }

    public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
        this.actions.add(mv -> mv.visitFrame(type, numLocal, local, numStack, stack));
    }

    public void visitInsn(int opcode) {
        this.actions.add(mv -> mv.visitInsn(opcode));
    }

    public void visitIntInsn(int opcode, int operand) {
        this.actions.add(mv -> mv.visitIntInsn(opcode, operand));
    }

    public void visitVarInsn(int opcode, int varIndex) {
        this.actions.add(mv -> mv.visitVarInsn(opcode, varIndex));
    }

    public void visitTypeInsn(int opcode, String type) {
        this.actions.add(mv -> mv.visitTypeInsn(opcode, type));
    }

    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        this.actions.add(mv -> mv.visitFieldInsn(opcode, owner, name, descriptor));
    }

    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        this.actions.add(mv -> mv.visitMethodInsn(opcode, owner, name, descriptor, isInterface));
    }

    public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
        this.actions.add(mv -> mv.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments));
    }

    public void visitJumpInsn(int opcode, Label label) {
        this.actions.add(mv -> mv.visitJumpInsn(opcode, label));
    }

    public void visitLabel(Label label) {
        this.actions.add(mv -> mv.visitLabel(label));
    }

    public void visitLdcInsn(Object value) {
        this.actions.add(mv -> mv.visitLdcInsn(value));
    }

    public void visitIincInsn(int varIndex, int increment) {
        this.actions.add(mv -> mv.visitIincInsn(varIndex, increment));
    }

    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        this.actions.add(mv -> mv.visitTableSwitchInsn(min, max, dflt, labels));
    }

    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        this.actions.add(mv -> mv.visitLookupSwitchInsn(dflt, keys, labels));
    }

    public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
        this.actions.add(mv -> mv.visitMultiANewArrayInsn(descriptor, numDimensions));
    }

    /*public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        return mv.visitInsnAnnotation(typeRef, typePath, descriptor, visible);
    }*/

    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        this.actions.add(mv -> mv.visitTryCatchBlock(start, end, handler, type));
    }

    /*public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        return mv.visitTryCatchAnnotation(typeRef, typePath, descriptor, visible);
    }*/

    public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
        this.actions.add(mv -> mv.visitLocalVariable(name, descriptor, signature, start, end, index));
    }

    /*public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String descriptor, boolean visible) {
        return mv.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, descriptor, visible);
    }*/

    public void visitLineNumber(int line, Label start) {
        this.actions.add(mv -> mv.visitLineNumber(line, start));
    }

    public void visitMaxs(int maxStack, int maxLocals) {
        this.actions.add(mv -> mv.visitMaxs(maxStack, maxLocals));
    }

    public void visitEnd() {
        this.actions.add(MethodVisitor::visitEnd);
    }
}
