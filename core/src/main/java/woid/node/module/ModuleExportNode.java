package woid.node.module;

import org.objectweb.asm.ModuleVisitor;

/**
 * A node that represents an exported package with its name and the module that can access to it.
 *
 * @param packaze The internal name of the exported package (see {@link
 *                org.objectweb.asm.Type#getInternalName()}).
 * @param access  The access flags (see {@link org.objectweb.asm.Opcodes}). Valid values are {@code
 *                ACC_SYNTHETIC} and {@code ACC_MANDATED}.
 * @param modules The list of modules that can access this exported package, specified with fully qualified names
 *                (using dots). May be {@literal null}.
 * @author Remi Forax
 */
public record ModuleExportNode(String packaze, int access, String[] modules) {

    /**
     * Makes the given module visitor visit this export declaration.
     *
     * @param moduleVisitor a module visitor.
     */
    public void accept(ModuleVisitor moduleVisitor) {
        moduleVisitor.visitExport(packaze, access, modules);
    }
}

