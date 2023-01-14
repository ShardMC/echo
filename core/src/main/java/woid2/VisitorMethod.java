package woid2;

import woid2.data.method.impl.*;
import woid2.data.method.impl.insn.*;

public abstract class VisitorMethod {
    public LocalVariableHeader onLocalVariable(LocalVariableHeader header) {
        return header;
    }

    public MaxsHeader onMaxs(MaxsHeader header) {
        return header;
    }

    public ParameterHeader onParameter(ParameterHeader header) {
        return header;
    }

    public AttributeHeader onAttribute(AttributeHeader header) {
        return header;
    }

    public FrameHeader onFrame(FrameHeader header) {
        return header;
    }

    public LabelHeader onLabel(LabelHeader header) {
        return header;
    }

    public LineNumberHeader onLineNumber(LineNumberHeader header) {
        return header;
    }

    public TryCatchBlockHeader onTryCatchBlock(TryCatchBlockHeader header) {
        return header;
    }

    public InsnHeader onInsn(InsnHeader header) {
        return header;
    }

    public FieldInsnHeader onFieldInsn(FieldInsnHeader header) {
        return header;
    }

    public MethodInsnHeader onMethodInsn(MethodInsnHeader header) {
        return header;
    }

    public TypeInsnHeader onTypeInsn(TypeInsnHeader header) {
        return header;
    }

    public VarInsnHeader onVarInsn(VarInsnHeader header) {
        return header;
    }

    public IntInsnHeader onIntInsn(IntInsnHeader header) {
        return header;
    }

    public IincInsnHeader onIincInsn(IincInsnHeader header) {
        return header;
    }

    public InvokeDynamicInsnHeader onInvokeDynamicInsn(InvokeDynamicInsnHeader header) {
        return header;
    }

    public JumpInsnHeader onJumpInsn(JumpInsnHeader header) {
        return header;
    }

    public LdcInsnHeader onLdcInsn(LdcInsnHeader header) {
        return header;
    }

    public LookupSwitchInsnHeader onLookupSwitchInsn(LookupSwitchInsnHeader header) {
        return header;
    }

    public TableSwitchInsnHeader onTableSwitchInsn(TableSwitchInsnHeader header) {
        return header;
    }

    public MultiANewArrayInsnHeader onMultiANewArrayInsn(MultiANewArrayInsnHeader header) {
        return header;
    }
}