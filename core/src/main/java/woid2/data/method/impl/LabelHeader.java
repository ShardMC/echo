package woid2.data.method.impl;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

public class LabelHeader implements MethodElement {

    private Label label;

    public LabelHeader(Label label) {
        this.label = label;
    }

    public Label getLabel() {
        return this.label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "LabelHeader{" +
                "label=" + label +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitLabel(this.label);
    }
}
