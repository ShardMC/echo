package woid.node.clazz;

import org.objectweb.asm.ClassVisitor;
import woid.node.ClassNode;

/**
 * A node that represents an inner class. This inner class is not necessarily a member of the {@link
 * ClassNode} containing this object. More precisely, every class or interface C which is referenced
 * by a {@link ClassNode} and which is not a package member must be represented with an {@link
 * InnerClassNode}. The {@link ClassNode} must reference its nested class or interface members, and
 * its enclosing class, if any. See the JVMS 4.7.6 section for more details.
 *
 * @param name      The internal name of an inner class (see {@link org.objectweb.asm.Type#getInternalName()}).
 * @param outerName The internal name of the class to which the inner class belongs (see {@link
 *                  org.objectweb.asm.Type#getInternalName()}). May be {@literal null}.
 * @param innerName The (simple) name of the inner class inside its enclosing class. Must be {@literal null} if the
 *                  inner class is not the member of a class or interface (e.g. for local or anonymous classes).
 * @param access    The access flags of the inner class as originally declared in the source code from which the
 *                  class was compiled.
 * @author Eric Bruneton
 */
public record InnerClassNode(String name, String outerName, String innerName, int access) {

    /**
     * Makes the given class visitor visit this inner class.
     *
     * @param classVisitor a class visitor.
     */
    public void accept(ClassVisitor classVisitor) {
        classVisitor.visitInnerClass(this.name, this.outerName, this.innerName, this.access);
    }
}
