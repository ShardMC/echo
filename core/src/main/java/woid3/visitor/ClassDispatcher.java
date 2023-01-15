package woid3.visitor;

public abstract class ClassDispatcher {
    private final FieldDispatcher fieldDispatcher = this.field();
    abstract FieldDispatcher field();

    public FieldDispatcher getFieldDispatcher() {
        return this.fieldDispatcher;
    }
}
