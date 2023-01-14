package woid2.test;

import woid2.Visitor;
import woid2.VisitorMethod;
import woid2.data.clazz.ClassHeader;
import woid2.data.field.FieldHeader;
import woid2.data.method.MethodHeader;
import woid2.util.EventedClassVisitor;

import java.util.List;

public class TestVisitor extends Visitor {

    private String currentClass;
    private final EventedClassVisitor original;

    public TestVisitor(EventedClassVisitor original) {
        this.original = original;
    }

    @Override
    public ClassHeader onClassHeader(ClassHeader clazz) {
        this.currentClass = clazz.getName();
        return clazz;
    }

    @Override
    public void onField(List<String> annotations, FieldHeader header) {
        if (!annotations.contains("Lio/shardmc/echo/annotations/Shadow;")) {
            header.visit(this.original);
        }
    }

    @Override
    public VisitorMethod onMethod(List<String> annotations, MethodHeader header) {
        System.out.println(annotations);
        if (!header.getName().equals("<init>") && !annotations.contains("Lio/shardmc/echo/annotations/Shadow;")) {
            return new TestVisitorMethod(this.original.getName(), this.currentClass, header.visit(this.original));
        }

        return null;
    }
}
