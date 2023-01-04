package woid.node.module;

import org.objectweb.asm.ModuleVisitor;

/**
 * A node that represents a service and its implementation provided by the current module.
 *
 * @param service   The internal name of the service (see {@link org.objectweb.asm.Type#getInternalName()}).
 * @param providers The internal names of the implementations of the service (there is at least one provider). See
 *                  {@link org.objectweb.asm.Type#getInternalName()}.
 * @author Remi Forax
 */
public record ModuleProvideNode(String service, String[] providers) {

    /**
     * Makes the given module visitor visit the required declaration.
     *
     * @param moduleVisitor a module visitor.
     */
    public void accept(ModuleVisitor moduleVisitor) {
        moduleVisitor.visitProvide(service, providers);
    }
}
