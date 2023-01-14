package woid2.data.method.impl;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

public class AttributeHeader implements MethodElement {

    private Attribute attribute;

    public AttributeHeader(Attribute attribute) {
        this.attribute = attribute;
    }

    public Attribute getAttribute() {
        return this.attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "AttributeHeader{" +
                "attribute=" + attribute +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitAttribute(this.attribute);
    }
}
