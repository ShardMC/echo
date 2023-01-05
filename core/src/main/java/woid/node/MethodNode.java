package woid.node;

import org.objectweb.asm.*;
import woid.Util;
import woid.node.annotation.*;
import woid.node.method.insn.impl.*;
import woid.simple.SimpleMethodVisitor;
import woid.node.method.insn.InsnList;
import woid.node.method.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodNode extends SimpleMethodVisitor {

    private int maxStack;
    private int maxLocals;

    private Object annotationDefault;
    private int visibleAnnotableParameterCount;
    private int invisibleAnnotableParameterCount;
    private List<AnnotationNode>[] visibleParameterAnnotations;
    private List<AnnotationNode>[] invisibleParameterAnnotations;
    private final List<AnnotationNode> annotations = new ArrayList<>();
    private final List<TypeAnnotationNode> typeAnnotations = new ArrayList<>();
    private final List<LocalVariableAnnotationNode> localVariableAnnotations = new ArrayList<>();

    private final List<Attribute> attributes = new ArrayList<>();
    private final List<TryCatchBlockNode> tryCatchBlocks = new ArrayList<>();
    private final List<ParameterNode> parameters = new ArrayList<>(5);
    private final List<LocalVariableNode> localVariables = new ArrayList<>(5);

    public final InsnList instructions = new InsnList();

    public MethodNode(int access, String name, String descriptor, String signature, String[] exceptions) {
        super(access, name, descriptor, signature, exceptions);
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        this.tryCatchBlocks.add(new TryCatchBlockNode(start, end, handler, type));
    }

    @Override
    public void visitParameter(String name, int access) {
        this.parameters.add(new ParameterNode(name, access));
    }

    @Override
    public AnnotationVisitor visitAnnotationDefault() {
        return new AnnotationNode(
                new ArrayList<>(0) {
                    @Override
                    public boolean add(Object o) {
                        annotationDefault = o;
                        return super.add(o);
                    }
                }, true);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        AnnotationNode annotation = new AnnotationNode(descriptor, visible);
        this.annotations.add(annotation);

        return annotation;
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        TypeAnnotationNode typeAnnotation = new TypeAnnotationNode(typeRef, typePath, descriptor, visible);
        this.typeAnnotations.add(typeAnnotation);

        return typeAnnotation;
    }

    @Override
    public void visitAnnotableParameterCount(int parameterCount, boolean visible) {
        if (visible) {
            this.visibleAnnotableParameterCount = parameterCount;
        } else {
            this.invisibleAnnotableParameterCount = parameterCount;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public AnnotationVisitor visitParameterAnnotation(int parameter, String descriptor, boolean visible) {
        AnnotationNode annotation = new AnnotationNode(descriptor, visible);

        if (visible) {
            if (this.visibleParameterAnnotations == null) {
                int params = Type.getArgumentTypes(descriptor).length;
                visibleParameterAnnotations = (List<AnnotationNode>[]) new List<?>[params];
            }

            this.visibleParameterAnnotations[parameter] =
                    Util.add(this.visibleParameterAnnotations[parameter], annotation);
        } else {
            if (this.invisibleParameterAnnotations == null) {
                int params = Type.getArgumentTypes(descriptor).length;
                this.invisibleParameterAnnotations = (List<AnnotationNode>[]) new List<?>[params];
            }

            this.invisibleParameterAnnotations[parameter] =
                    Util.add(this.invisibleParameterAnnotations[parameter], annotation);
        }

        return annotation;
    }

    @Override
    public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
        this.localVariables.add(new LocalVariableNode(name, descriptor, signature, start, end, index));
    }

    @Override
    public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String descriptor, boolean visible) {
        LocalVariableAnnotationNode localVariableAnnotation = new LocalVariableAnnotationNode(typeRef, typePath, start, end, index, descriptor, visible);
        this.localVariableAnnotations.add(localVariableAnnotation);

        return localVariableAnnotation;
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
    }

    @Override
    public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
        this.instructions.add(
                new FrameInsnNode(
                        type,
                        numLocal,
                        local,
                        numStack,
                        stack
                )
        );
    }

    @Override
    public void visitInsn(int opcode) {
        this.instructions.add(new SimpleInsnNode(opcode));
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        this.instructions.add(new IntInsnNode(opcode, operand));
    }

    @Override
    public void visitVarInsn(int opcode, int varIndex) {
        this.instructions.add(new VarInsnNode(opcode, varIndex));
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        this.instructions.add(new TypeInsnNode(opcode, type));
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        this.instructions.add(new FieldInsnNode(opcode, owner, name, descriptor));
    }

    @Override
    public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
        this.instructions.add(new MethodInsnNode(opcodeAndSource & ~Opcodes.SOURCE_MASK, owner, name, descriptor, isInterface));
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
        this.instructions.add(new InvokeDynamicInsnNode(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments));
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        this.instructions.add(new JumpInsnNode(opcode, label));
    }

    @Override
    public void visitLabel(Label label) {
        this.instructions.add(new LabelNode(label));
    }

    @Override
    public void visitLineNumber(int line, Label start) {
        this.instructions.add(new LineInsnNode(line, start));
    }

    @Override
    public void visitLdcInsn(Object value) {
        this.instructions.add(new LdcInsnNode(value));
    }

    @Override
    public void visitIincInsn(int varIndex, int increment) {
        this.instructions.add(new IincInsnNode(varIndex, increment));
    }

    @Override
    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        this.instructions.add(new TableSwitchInsnNode(min, max, dflt, labels));
    }

    @Override
    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        this.instructions.add(new LookupSwitchInsnNode(dflt, keys, labels));
    }

    @Override
    public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
        this.instructions.add(new MultiANewArrayInsnNode(descriptor, numDimensions));
    }

    public void accept(ClassVisitor classVisitor) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(this.getAccess(), this.getName(), this.getDescriptor(), this.getSignature(), this.getExceptions());

        if (methodVisitor != null) {
            this.accept(methodVisitor);
        }
    }

    private boolean visited = false;

    public void accept(MethodVisitor methodVisitor) {
        for (ParameterNode parameter : this.parameters) {
            parameter.accept(methodVisitor);
        }

        if (this.annotationDefault != null) {
            AnnotationVisitor annotationVisitor = methodVisitor.visitAnnotationDefault();
            AnnotationNode.accept(annotationVisitor, null, this.annotationDefault);

            if (annotationVisitor != null) {
                annotationVisitor.visitEnd();
            }
        }

        for (AnnotationNode annotation : this.annotations) {
            annotation.accept(methodVisitor.visitAnnotation(annotation.getDesc(), true));
        }

        for (TypeAnnotationNode typeAnnotation : this.typeAnnotations) {
            typeAnnotation.accept(
                    methodVisitor.visitTypeAnnotation(typeAnnotation.getTypeRef(), typeAnnotation.getTypePath(), typeAnnotation.getDesc(), typeAnnotation.isVisible())
            );
        }

        if (this.visibleAnnotableParameterCount > 0) {
            methodVisitor.visitAnnotableParameterCount(this.visibleAnnotableParameterCount, true);
        }

        if (this.visibleParameterAnnotations != null) {
            for (int i = 0, n = this.visibleParameterAnnotations.length; i < n; ++i) {
                List<AnnotationNode> parameterAnnotations = this.visibleParameterAnnotations[i];
                if (parameterAnnotations == null) {
                    continue;
                }

                for (AnnotationNode annotation : parameterAnnotations) {
                    annotation.accept(methodVisitor.visitParameterAnnotation(i, annotation.getDesc(), true));
                }
            }
        }

        if (this.invisibleAnnotableParameterCount > 0) {
            methodVisitor.visitAnnotableParameterCount(this.invisibleAnnotableParameterCount, false);
        }

        if (this.invisibleParameterAnnotations != null) {
            for (int i = 0; i < this.invisibleParameterAnnotations.length; i++) {
                List<AnnotationNode> parameterAnnotations = this.invisibleParameterAnnotations[i];
                if (parameterAnnotations == null) {
                    continue;
                }

                for (AnnotationNode annotation : parameterAnnotations) {
                    annotation.accept(methodVisitor.visitParameterAnnotation(i, annotation.getDesc(), false));
                }
            }
        }

        if (this.visited) {
            this.instructions.resetLabels();
        }

        for (Attribute attr : this.attributes) {
            methodVisitor.visitAttribute(attr);
        }

        if (this.instructions.size() > 0) {
            methodVisitor.visitCode();
            for (TryCatchBlockNode tryCatchBlock : this.tryCatchBlocks) {
                tryCatchBlock.accept(methodVisitor);
            }

            this.instructions.accept(methodVisitor);

            for (LocalVariableNode localVariable : this.localVariables) {
                localVariable.accept(methodVisitor);
            }

            for (LocalVariableAnnotationNode visibleLocalVariableAnnotation : this.localVariableAnnotations) {
                visibleLocalVariableAnnotation.accept(methodVisitor);
            }

            methodVisitor.visitMaxs(this.maxStack, this.maxLocals);
            this.visited = true;
        }

        methodVisitor.visitEnd();
    }

    public List<AnnotationNode> getAnnotations() {
        return this.annotations;
    }

    public boolean equals(String name, String desc) {
        return this.getName().equals(name) && this.getDescriptor().equals(desc);
    }

    @Override
    public String toString() {
        return "MethodNode{" +
                "maxStack=" + maxStack +
                ", maxLocals=" + maxLocals +
                ", annotationDefault=" + annotationDefault +
                ", visibleAnnotableParameterCount=" + visibleAnnotableParameterCount +
                ", invisibleAnnotableParameterCount=" + invisibleAnnotableParameterCount +
                ", visibleParameterAnnotations=" + Arrays.toString(visibleParameterAnnotations) +
                ", invisibleParameterAnnotations=" + Arrays.toString(invisibleParameterAnnotations) +
                ", annotations=" + annotations +
                ", typeAnnotations=" + typeAnnotations +
                ", localVariableAnnotations=" + localVariableAnnotations +
                ", attributes=" + attributes +
                ", tryCatchBlocks=" + tryCatchBlocks +
                ", parameters=" + parameters +
                ", localVariables=" + localVariables +
                ", instructions=" + instructions +
                ", visited=" + visited +
                '}';
    }
}
