package woid2.data.clazz;

import org.objectweb.asm.ClassVisitor;

public interface ClassElement<T> {
    T visit(ClassVisitor visitor);
}
