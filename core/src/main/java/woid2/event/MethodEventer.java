package woid2.event;

import org.objectweb.asm.*;
import woid2.VisitorMethod;
import woid2.data.method.impl.*;
import woid2.data.method.impl.insn.*;

public class MethodEventer extends MethodVisitor {

    private final VisitorMethod visitor;

    public MethodEventer(MethodVisitor mv, VisitorMethod visitor) {
        super(Opcodes.ASM9, mv);
        this.visitor = visitor;
    }

    @Override
    public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
        LocalVariableHeader header = this.visitor.onLocalVariable(new LocalVariableHeader(name, descriptor, signature, start, end, index));

        super.visitLocalVariable(
                header.getName(),
                header.getDescriptor(),
                header.getSignature(),
                header.getStart(),
                header.getEnd(),
                header.getIndex()
        );
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        MaxsHeader header = this.visitor.onMaxs(new MaxsHeader(maxStack, maxLocals));

        super.visitMaxs(
                header.getMaxStack(),
                header.getMaxLocals()
        );
    }

    @Override
    public void visitParameter(String name, int access) {
        ParameterHeader header = this.visitor.onParameter(new ParameterHeader(name, access));

        super.visitParameter(
                header.getName(),
                header.getAccess()
        );
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        super.visitAttribute(this.visitor.onAttribute(new AttributeHeader(attribute)).getAttribute());
    }

    @Override
    public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
        FrameHeader header = this.visitor.onFrame(new FrameHeader(type, numLocal, local, numStack, stack));

        super.visitFrame(
                header.getType(),
                header.getNumLocal(),
                header.getLocal(),
                header.getNumStack(),
                header.getStack()
        );
    }

    @Override
    public void visitLabel(Label label) {
        super.visitLabel(this.visitor.onLabel(new LabelHeader(label)).getLabel());
    }

    @Override
    public void visitLineNumber(int line, Label start) {
        LineNumberHeader header = this.visitor.onLineNumber(new LineNumberHeader(line, start));

        super.visitLineNumber(
                header.getLine(),
                header.getStart()
        );
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        TryCatchBlockHeader header = this.visitor.onTryCatchBlock(new TryCatchBlockHeader(start, end, handler, type));

        super.visitTryCatchBlock(
                header.getStart(),
                header.getEnd(),
                header.getHandler(),
                header.getType()
        );
    }

    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(this.visitor.onInsn(new InsnHeader(opcode)).getOpcode());
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        FieldInsnHeader header = this.visitor.onFieldInsn(new FieldInsnHeader(opcode, owner, name, descriptor));

        super.visitFieldInsn(
                header.getOpcode(),
                header.getOwner(),
                header.getName(),
                header.getDescriptor()
        );
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        MethodInsnHeader header = this.visitor.onMethodInsn(new MethodInsnHeader(opcode, owner, name, descriptor, isInterface));

        super.visitMethodInsn(
                header.getOpcode(),
                header.getOwner(),
                header.getName(),
                header.getDescriptor(),
                header.isInterface()
        );
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        TypeInsnHeader header = this.visitor.onTypeInsn(new TypeInsnHeader(opcode, type));

        super.visitTypeInsn(
                header.getOpcode(),
                header.getType()
        );
    }

    @Override
    public void visitVarInsn(int opcode, int varIndex) {
        VarInsnHeader header = this.visitor.onVarInsn(new VarInsnHeader(opcode, varIndex));

        super.visitVarInsn(
                header.getOpcode(),
                header.getVarIndex()
        );
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        IntInsnHeader header = this.visitor.onIntInsn(new IntInsnHeader(opcode, operand));

        super.visitIntInsn(
                header.getOpcode(),
                header.getOperand()
        );
    }

    @Override
    public void visitIincInsn(int varIndex, int increment) {
        IincInsnHeader header = this.visitor.onIincInsn(new IincInsnHeader(varIndex, increment));

        super.visitIincInsn(
                header.getVarIndex(),
                header.getIncrement()
        );
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
        InvokeDynamicInsnHeader header = this.visitor.onInvokeDynamicInsn(
                new InvokeDynamicInsnHeader(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments)
        );

        super.visitInvokeDynamicInsn(
                header.getName(),
                header.getDescriptor(),
                header.getBootstrapMethodHandle(),
                header.getBootstrapMethodArguments()
        );
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        JumpInsnHeader header = this.visitor.onJumpInsn(new JumpInsnHeader(opcode, label));

        super.visitJumpInsn(
                header.getOpcode(),
                header.getLabel()
        );
    }

    @Override
    public void visitLdcInsn(Object value) {
        super.visitLdcInsn(this.visitor.onLdcInsn(new LdcInsnHeader(value)).getValue());
    }

    @Override
    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        LookupSwitchInsnHeader header = this.visitor.onLookupSwitchInsn(new LookupSwitchInsnHeader(dflt, keys, labels));

        super.visitLookupSwitchInsn(
                header.getDflt(),
                header.getKeys(),
                header.getLabels()
        );
    }

    @Override
    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        TableSwitchInsnHeader header = this.visitor.onTableSwitchInsn(new TableSwitchInsnHeader(min, max, dflt, labels));

        super.visitTableSwitchInsn(
                header.getMin(),
                header.getMax(),
                header.getDflt(),
                header.getLabels()
        );
    }

    @Override
    public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
        MultiANewArrayInsnHeader header = this.visitor.onMultiANewArrayInsn(new MultiANewArrayInsnHeader(descriptor, numDimensions));

        super.visitMultiANewArrayInsn(
                header.getDescriptor(),
                header.getNumDimensions()
        );
    }
}
