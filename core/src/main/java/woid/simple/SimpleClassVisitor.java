package woid.simple;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class SimpleClassVisitor extends ClassVisitor {

    public SimpleClassVisitor() {
        super(Opcodes.ASM9);
    }

    public SimpleClassVisitor(ClassVisitor parent) {
        super(Opcodes.ASM9, parent);
    }
}
