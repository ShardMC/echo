package io.shardmc.echo.core.transformer;

import io.shardmc.echo.util.FileUtil;
import org.objectweb.asm.ClassReader;
import woid.node.ClassNode;
import woid.node.FieldNode;
import woid.node.MethodNode;
import woid.node.annotation.AnnotationNode;
import woid.node.method.insn.impl.FieldInsnNode;
import woid.node.method.insn.impl.MethodInsnNode;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("unused")
public class TransformerUtil {

    public static boolean hasAnnotation(MethodNode method, String annotationClass) {
        return method.getAnnotations().parallelStream().anyMatch(annotation -> annotation.equals(annotationClass));
    }

    public static boolean hasAnnotation(FieldNode field, String annotationClass) {
        return field.getAnnotations().parallelStream().anyMatch(annotation -> annotation.equals(annotationClass));
    }

    public static AnnotationNode getAnnotation(MethodNode method, String annotationClass) {
        return method.getAnnotations().parallelStream().filter(annotation -> annotation.equals(annotationClass)).findFirst().orElse(null);
    }

    public static ClassNode createClassNode(String clazz, int options) {
        try {
            ClassNode node = new ClassNode();

            new ClassReader(
                    FileUtil.readBytes(FileUtil.getResource(clazz.replace('.', '/') + ".class"))
            ).accept(node, options);

            return node;
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public static ClassNode createClassNode(String clazz) {
        return TransformerUtil.createClassNode(clazz, 0);
    }

    public static List<ClassNode> createClassNodes(List<String> classes) {
        //List<ClassNode> nodes = new ArrayList<>();
        //classes.parallelStream().forEach(s -> nodes.add(TransformerUtil.createClassNode(s)));

        return classes.parallelStream().map(TransformerUtil::createClassNode).toList();
    }

    public static MethodNode findMethod(ClassNode clazz, String name, String desc) {
        return clazz.getMethods().parallelStream().filter(node -> node.by(name, desc)).findFirst().orElse(null);
    }

    public static void filterInsns(MethodNode method, String from, String to, boolean removeReturns) {
        method.getInstructions().parallelStream()
                .forEach(node -> {
                    if (node instanceof MethodInsnNode methodNode && methodNode.getOwner().equals(from)) {
                        methodNode.setOwner(to);
                    }

                    if (node instanceof FieldInsnNode fieldNode && fieldNode.getOwner().equals(from)) {
                        fieldNode.setOwner(to);
                    }
                });

    }
}
