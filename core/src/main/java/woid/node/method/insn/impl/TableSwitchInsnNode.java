package woid.node.method.insn.impl;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import woid.node.method.insn.AbstractInsnNode;

/**
 * A node that represents a TABLESWITCH instruction.
 *
 * @author Eric Bruneton
 */
public class TableSwitchInsnNode extends AbstractInsnNode {

    /**
     * The minimum key value.
     */
    private final int min;

    /**
     * The maximum key value.
     */
    private final int max;

    /**
     * Beginning of the default handler block.
     */
    private final Label dflt;

    /**
     * Beginnings of the handler blocks. This list is a list of {@link tree.LabelNode} objects.
     */
    private final Label[] labels;

    /**
     * Constructs a new {@link TableSwitchInsnNode}.
     *
     * @param min    the minimum key value.
     * @param max    the maximum key value.
     * @param dflt   beginning of the default handler block.
     * @param labels beginnings of the handler blocks. {@code labels[i]} is the beginning of the
     *               handler block for the {@code min + i} key.
     */
    public TableSwitchInsnNode(int min, int max, Label dflt, Label... labels) {
        super(Opcodes.TABLESWITCH);

        this.min = min;
        this.max = max;
        this.dflt = dflt;
        this.labels = labels;
    }

    @Override
    public int getType() {
        return TABLESWITCH_INSN;
    }

    @Override
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitTableSwitchInsn(this.min, this.max, this.dflt, this.labels);
        this.acceptAnnotations(methodVisitor);
    }
}
