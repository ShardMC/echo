package woid2.data.method.impl;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

public class LineNumberHeader implements MethodElement {

    private int line;
    private Label start;

    public LineNumberHeader(int line, Label start) {
        this.line = line;
        this.start = start;
    }

    public int getLine() {
        return this.line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public Label getStart() {
        return this.start;
    }

    public void setStart(Label start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "LineNumberHeader{" +
                "line=" + line +
                ", start=" + start +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitLineNumber(this.line, this.start);
    }
}
