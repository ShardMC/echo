package woid7;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassNode extends ClassVisitor {

    private final Actions<ClassVisitor> actions = new Actions<>();

    public ClassNode() {
        this(null);
    }

    public ClassNode(ClassVisitor cv) {
        super(Opcodes.ASM9, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        this.actions.add(cv -> cv.visitMethod(access, name, descriptor, signature, exceptions));
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
}
