package io.shardmc.echo.core.transformer;

import io.shardmc.echo.util.FileUtil;
import org.objectweb.asm.ClassReader;
import woid.node.ClassNode;
import woid.node.FieldNode;
import woid.node.MethodNode;
import woid.node.annotation.AnnotationNode;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("unused")
public class TransformerUtil {

    public static boolean hasAnnotation(MethodNode method, String annotationClass) {
        if (method.getAnnotations() == null)
            return false;

        return method.getAnnotations().parallelStream().anyMatch(annotation -> annotation.equals(annotationClass));
    }

    public static boolean hasAnnotation(FieldNode field, String annotationClass) {
        if (field.getAnnotations() == null)
            return false;

        return field.getAnnotations().parallelStream().anyMatch(annotation -> annotation.equals(annotationClass));
    }

    public static AnnotationNode getAnnotation(MethodNode method, String annotationClass) {
        if (method.getAnnotations() == null)
            return null;

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
}
