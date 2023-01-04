package woid.node.method.insn.impl;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import woid.node.method.insn.AbstractInsnNode;

/**
 * An {@link AbstractInsnNode} that encapsulates a {@link Label}.
 */
public class LabelNode extends AbstractInsnNode {

    private Label value;

    public LabelNode(Label label) {
        super(-1);

        this.value = label;
    }

    public LabelNode() {
        this(null);
    }

    @Override
    public int getType() {
        return LABEL;
    }

    /**
     * Returns the label encapsulated by this node. A new label is created and associated with this
     * node if it was created without an encapsulated label.
     *
     * @return the label encapsulated by this node.
     */
    public Label getLabel() {
        if (this.value == null) {
            this.value = new Label();
        }

        return this.value;
    }

    @Override
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitLabel(this.getLabel());
    }

    public void resetLabel() {
        this.value = null;
    }
}
