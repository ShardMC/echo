package woid2.data.method.impl.insn;

import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import woid2.data.method.MethodElement;

import java.util.Arrays;

public class InvokeDynamicInsnHeader implements MethodElement {

    private String name;
    private String descriptor;
    private Handle bootstrapMethodHandle;
    private Object[] bootstrapMethodArguments;

    public InvokeDynamicInsnHeader(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
        this.name = name;
        this.descriptor = descriptor;
        this.bootstrapMethodHandle = bootstrapMethodHandle;
        this.bootstrapMethodArguments = bootstrapMethodArguments;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public Handle getBootstrapMethodHandle() {
        return this.bootstrapMethodHandle;
    }

    public void setBootstrapMethodHandle(Handle bootstrapMethodHandle) {
        this.bootstrapMethodHandle = bootstrapMethodHandle;
    }

    public Object[] getBootstrapMethodArguments() {
        return this.bootstrapMethodArguments;
    }

    public void setBootstrapMethodArguments(Object[] bootstrapMethodArguments) {
        this.bootstrapMethodArguments = bootstrapMethodArguments;
    }

    @Override
    public String toString() {
        return "InvokeDynamicInsnHeader{" +
                "name='" + name + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", bootstrapMethodHandle=" + bootstrapMethodHandle +
                ", bootstrapMethodArguments=" + Arrays.toString(bootstrapMethodArguments) +
                '}';
    }

    @Override
    public void visit(MethodVisitor visitor) {
        visitor.visitInvokeDynamicInsn(this.name, this.descriptor, this.bootstrapMethodHandle, this.bootstrapMethodArguments);
    }
}
