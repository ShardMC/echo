package woid2;

import org.objectweb.asm.ClassVisitor;
import woid2.data.clazz.ClassHeader;
import woid2.data.field.FieldHeader;
import woid2.data.method.MethodHeader;
import woid2.event.FieldEventer;

import java.util.List;

public abstract class Visitor {
    public ClassHeader onClassHeader(ClassHeader clazz) { return clazz; }
    public void onClass(ClassVisitor visitor, ClassHeader header) { }

    public FieldHeader onFieldHeader(FieldHeader field) { return field; }
    public void onField(List<String> annotations, FieldHeader header) { }

    public MethodHeader onMethodHeader(MethodHeader method) { return method; }
    public VisitorMethod onMethod(List<String> annotations, MethodHeader header) { return null; } //FIXME: how to get access to the delayed visitMethod? Perhaps, a Consumer/Supplier thing to make a callback?
}
