package woid6;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AddMethodVisitor extends MethodVisitor {

    private final CachedMethodVisitor cached;
    private final MethodVisitor original;

    public AddMethodVisitor(CachedMethodVisitor cached, MethodVisitor original) {
        super(Opcodes.ASM9);

        this.cached = cached;
        //cached.apply();
        this.original = original;
    }

}
