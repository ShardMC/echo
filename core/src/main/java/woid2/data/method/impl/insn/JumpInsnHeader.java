package woid2.data.method.impl.insn;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import woid.node.method.insn.impl.JumpInsnNode;
import woid2.data.method.MethodElement;

public class JumpInsnHeader implements MethodElement {

    private int opcode;
    private Label label;

    public JumpInsnHeader(int opcode, Label label) {
        this.opcode = opcode;
        this.label = label;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public Label getLabel() {
        return this.label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "JumpInsnHeader{" +
                "opcode=" + opcode +
                ", label=" + label +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitJumpInsn(this.opcode, this.label);
    }
}
