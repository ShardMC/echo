package woid2.data.method.impl;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

public class TryCatchBlockHeader implements MethodElement {

    private Label start;
    private Label end;
    private Label handler;
    private String type;

    public TryCatchBlockHeader(Label start, Label end, Label handler, String type) {

    }

    public Label getStart() {
        return this.start;
    }

    public void setStart(Label start) {
        this.start = start;
    }

    public Label getEnd() {
        return this.end;
    }

    public void setEnd(Label end) {
        this.end = end;
    }

    public Label getHandler() {
        return this.handler;
    }

    public void setHandler(Label handler) {
        this.handler = handler;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TryCatchBlockHeader{" +
                "start=" + start +
                ", end=" + end +
                ", handler=" + handler +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitTryCatchBlock(this.start, this.end, this.handler, this.type);
    }
}
