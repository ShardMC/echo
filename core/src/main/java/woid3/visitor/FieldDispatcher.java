package woid3.visitor;

import woid3.header.FieldHeader;

import java.util.List;

public abstract class FieldDispatcher {
    public FieldHeader onField(FieldHeader header) { return header; }
    public void withAnnotations(FieldHeader header, List<String> annotations) { }
}
