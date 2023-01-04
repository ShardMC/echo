package woid.node.module;

import org.objectweb.asm.ModuleVisitor;

/**
 * A node that represents a required module with its name and access of a module descriptor.
 *
 * @param module  The fully qualified name (using dots) of the dependence.
 * @param access  The access flag of the dependence among {@code ACC_TRANSITIVE}, {@code ACC_STATIC_PHASE},
 *                {@code ACC_SYNTHETIC} and {@code ACC_MANDATED}.
 * @param version The module version at compile time, or {@literal null}.
 * @author Remi Forax
 */
public record ModuleRequireNode(String module, int access, String version) {

    /**
     * Makes the given module visitor visit the required directive.
     *
     * @param moduleVisitor a module visitor.
     */
    public void accept(ModuleVisitor moduleVisitor) {
        moduleVisitor.visitRequire(module, access, version);
    }
}
