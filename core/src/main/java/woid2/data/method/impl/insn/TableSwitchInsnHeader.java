package woid2.data.method.impl.insn;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

import java.util.Arrays;

public class TableSwitchInsnHeader implements MethodElement {

    private int min;
    private int max;
    private Label dflt;
    private Label[] labels;

    public TableSwitchInsnHeader(int min, int max, Label dflt, Label... labels) {
        this.min = min;
        this.max = max;
        this.dflt = dflt;
        this.labels = labels;
    }

    public int getMin() {
        return this.min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Label getDflt() {
        return this.dflt;
    }

    public void setDflt(Label dflt) {
        this.dflt = dflt;
    }

    public Label[] getLabels() {
        return this.labels;
    }

    public void setLabels(Label[] labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "TableSwitchInsnHeader{" +
                "min=" + min +
                ", max=" + max +
                ", dflt=" + dflt +
                ", labels=" + Arrays.toString(labels) +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitTableSwitchInsn(this.min, this.max, this.dflt, this.labels);
    }
}
