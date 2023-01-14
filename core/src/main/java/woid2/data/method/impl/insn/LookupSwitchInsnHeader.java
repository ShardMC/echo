package woid2.data.method.impl.insn;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

import java.util.Arrays;

public class LookupSwitchInsnHeader implements MethodElement {

    private Label dflt;
    private int[] keys;
    private Label[] labels;

    public LookupSwitchInsnHeader(Label dflt, int[] keys, Label[] labels) {
        this.dflt = dflt;
        this.keys = keys;
        this.labels = labels;
    }

    public Label getDflt() {
        return this.dflt;
    }

    public void setDflt(Label dflt) {
        this.dflt = dflt;
    }

    public int[] getKeys() {
        return this.keys;
    }

    public void setKeys(int[] keys) {
        this.keys = keys;
    }

    public Label[] getLabels() {
        return this.labels;
    }

    public void setLabels(Label[] labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "LookupSwitchInsnHeader{" +
                "dflt=" + dflt +
                ", keys=" + Arrays.toString(keys) +
                ", labels=" + Arrays.toString(labels) +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitLookupSwitchInsn(this.dflt, this.keys, this.labels);
    }
}
