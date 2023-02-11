package woid6;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import woid6.event.EventfulFieldVisitor;

import java.util.List;

public class EchoClassVisitor extends ClassVisitor {

    private final ClassVisitor original;

    public EchoClassVisitor(ClassVisitor original) {
        super(Opcodes.ASM9, null);

        this.original = original;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        return new EventfulFieldVisitor(super.visitField(access, name, descriptor, signature, value)).onEnd(efv -> {
            //noinspection SwitchStatementWithTooFewBranches
            switch (EchoClassVisitor.parseAnnotation(efv.getAnnotations())) {
                case "Shadow;" -> { }
                default -> this.original.visitField(access, name, descriptor, signature, value);
            }
        });
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        return new APMethodVisitor(s -> s.startsWith("Lio/shardmc"), super.visitMethod(access, name, descriptor, signature, exceptions)).onCode(emv -> {
            switch (EchoClassVisitor.parseAnnotation(emv.getAnnotations())) {
                case "Shadow;" -> { }
                case "Add;" -> emv.setDelegate(
                        this.original.visitMethod(access, name, descriptor, signature, exceptions)
                );

                default -> emv.setDelegate(
                        this.original.visitMethod(access, name, descriptor, signature, exceptions)
                );
            }
        });
    }

    private static String parseAnnotation(List<String> annotations) {
        String annotation = null;
        if (annotations.size() > 0) {
            annotation = annotations.get(0);
        }

        return annotation != null ? annotation.substring(29) : "NOHANDLE";
    }
}
