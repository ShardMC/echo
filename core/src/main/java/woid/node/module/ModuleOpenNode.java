package woid.node.module;

import org.objectweb.asm.ModuleVisitor;

/**
 * A node that represents an opened package with its name and the module that can access it.
 *
 * @param packaze The internal name of the opened package (see {@link org.objectweb.asm.Type#getInternalName()}).
 * @param access  The access flag of the opened package, valid values are among {@code ACC_SYNTHETIC} and {@code
 *                ACC_MANDATED}.
 * @param modules The fully qualified names (using dots) of the modules that can use deep reflection to the
 *                classes of the open package, or {@literal null}.
 * @author Remi Forax
 */
public record ModuleOpenNode(String packaze, int access, String[] modules) {

    /**
     * Makes the given module visitor visit this opened package.
     *
     * @param moduleVisitor a module visitor.
     */
    public void accept(ModuleVisitor moduleVisitor) {
        moduleVisitor.visitOpen(packaze, access, modules);
    }
}
