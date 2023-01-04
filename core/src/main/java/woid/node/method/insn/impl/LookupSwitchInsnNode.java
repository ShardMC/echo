package woid.node.method.insn.impl;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import woid.node.method.insn.AbstractInsnNode;

/**
 * A node that represents a LOOKUPSWITCH instruction.
 *
 * @author Eric Bruneton
 */
public class LookupSwitchInsnNode extends AbstractInsnNode {

    /**
     * Beginning of the default handler block.
     */
    public final Label dflt;

    /**
     * The values of the keys.
     */
    public final int[] keys;

    /**
     * Beginnings of the handler blocks.
     */
    public final Label[] labels;

    /**
     * Constructs a new {@link LookupSwitchInsnNode}.
     *
     * @param dflt   beginning of the default handler block.
     * @param keys   the values of the keys.
     * @param labels beginnings of the handler blocks. {@code labels[i]} is the beginning of the
     *               handler block for the {@code keys[i]} key.
     */
    public LookupSwitchInsnNode(Label dflt, int[] keys, Label[] labels) {
        super(Opcodes.LOOKUPSWITCH);

        this.dflt = dflt;
        this.keys = keys;
        this.labels = labels;
    }

    @Override
    public int getType() {
        return LOOKUPSWITCH_INSN;
    }

    @Override
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitLookupSwitchInsn(this.dflt, this.keys, this.labels);
        this.acceptAnnotations(methodVisitor);
    }
}

