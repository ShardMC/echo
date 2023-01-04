package woid.node.method.insn.impl;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import woid.node.method.insn.AbstractInsnNode;

/**
 * A node that represents a line number declaration. These nodes are pseudo instruction nodes in
 * order to be inserted in an instruction list.
 *
 * @author Eric Bruneton
 */
public class LineInsnNode extends AbstractInsnNode {

    /**
     * A line number. This number refers to the source file from which the class was compiled.
     */
    private final int line;

    /**
     * The first instruction corresponding to this line number.
     */
    private final Label start;

    /**
     * Constructs a new {@link LineInsnNode}.
     *
     * @param line  a line number. This number refers to the source file from which the class was
     *              compiled.
     * @param start the first instruction corresponding to this line number.
     */
    public LineInsnNode(int line, Label start) {
        super(-1);

        this.line = line;
        this.start = start;
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitLineNumber(this.line, this.start);
    }
}