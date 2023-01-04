package woid.node.method.insn;

import org.objectweb.asm.MethodVisitor;
import woid.node.method.insn.impl.LabelNode;

import java.util.ArrayList;

public class InsnList extends ArrayList<AbstractInsnNode> {

    public void resetLabels() {
        for (AbstractInsnNode node : this) {
            if (node instanceof LabelNode label) {
                label.resetLabel();
            }
        }
    }

    public void accept(final MethodVisitor methodVisitor) {
        for (AbstractInsnNode node : this) {
            node.accept(methodVisitor);
        }
    }
}
