package woid2.test;

import org.objectweb.asm.MethodVisitor;
import woid2.VisitorMethod;
import woid2.data.method.impl.*;
import woid2.data.method.impl.insn.*;
import woid2.util.FormatUtil;

public class TestVisitorMethod extends VisitorMethod {

    private final String originalName;
    private final String currentName;
    private final MethodVisitor method;

    public TestVisitorMethod(String originalName, String currentName, MethodVisitor method) {
        this.originalName = originalName;
        this.currentName = currentName;
        this.method = method;
    }

    @Override
    public LocalVariableHeader onLocalVariable(LocalVariableHeader header) {
        if (header.getDescriptor().equals(FormatUtil.localVarDesc(this.currentName))) {
            header.setDescriptor(FormatUtil.localVarDesc(this.originalName));
        }

        header.visit(this.method);
        return header;
    }

    @Override
    public MaxsHeader onMaxs(MaxsHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public ParameterHeader onParameter(ParameterHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public AttributeHeader onAttribute(AttributeHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public FrameHeader onFrame(FrameHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public LabelHeader onLabel(LabelHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public LineNumberHeader onLineNumber(LineNumberHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public TryCatchBlockHeader onTryCatchBlock(TryCatchBlockHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public InsnHeader onInsn(InsnHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public FieldInsnHeader onFieldInsn(FieldInsnHeader header) {
        if (header.getOwner().equals(this.currentName)) {
            header.setOwner(this.originalName);
        }

        header.visit(this.method);
        return header;
    }

    @Override
    public MethodInsnHeader onMethodInsn(MethodInsnHeader header) {
        if (header.getOwner().equals(this.currentName)) {
            header.setOwner(this.originalName);
        }

        header.visit(this.method);
        return header;
    }

    @Override
    public TypeInsnHeader onTypeInsn(TypeInsnHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public VarInsnHeader onVarInsn(VarInsnHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public IntInsnHeader onIntInsn(IntInsnHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public IincInsnHeader onIincInsn(IincInsnHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public InvokeDynamicInsnHeader onInvokeDynamicInsn(InvokeDynamicInsnHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public JumpInsnHeader onJumpInsn(JumpInsnHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public LdcInsnHeader onLdcInsn(LdcInsnHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public LookupSwitchInsnHeader onLookupSwitchInsn(LookupSwitchInsnHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public TableSwitchInsnHeader onTableSwitchInsn(TableSwitchInsnHeader header) {
        header.visit(this.method);
        return header;
    }

    @Override
    public MultiANewArrayInsnHeader onMultiANewArrayInsn(MultiANewArrayInsnHeader header) {
        header.visit(this.method);
        return header;
    }
}
