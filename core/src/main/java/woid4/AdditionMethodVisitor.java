package woid4;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AdditionMethodVisitor extends MethodVisitor {

    public AdditionMethodVisitor(MethodContext context, ClassHeader current) {
        super(Opcodes.ASM9, context.visit());

    }
}
