package woid.node;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ModuleVisitor;
import org.objectweb.asm.Opcodes;
import woid.node.module.ModuleExportNode;
import woid.node.module.ModuleOpenNode;
import woid.node.module.ModuleProvideNode;
import woid.node.module.ModuleRequireNode;

import java.util.ArrayList;
import java.util.List;

public class ModuleNode extends ModuleVisitor {

    private final String name;
    private final int access;
    private final String version;
    private String mainClass;

    private final List<String> uses = new ArrayList<>(5);
    private final List<String> packages = new ArrayList<>(5);
    private final List<ModuleOpenNode> opens = new ArrayList<>(5);
    private final List<ModuleExportNode> exports = new ArrayList<>(5);
    private final List<ModuleRequireNode> requires = new ArrayList<>(5);
    private final List<ModuleProvideNode> provides = new ArrayList<>(5);

    public ModuleNode(String name, int access, String version) {
        super(Opcodes.ASM9);

        if (this.getClass() != ModuleNode.class) {
            throw new IllegalStateException();
        }

        this.name = name;
        this.access = access;
        this.version = version;
    }

    @Override
    public void visitMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    @Override
    public void visitPackage(String packaze) {
        this.packages.add(packaze);
    }

    @Override
    public void visitRequire(String module, int access, final String version) {
        this.requires.add(new ModuleRequireNode(module, access, version));
    }

    @Override
    public void visitExport(String packaze, int access, String... modules) {
        this.exports.add(new ModuleExportNode(packaze, access, modules));
    }

    @Override
    public void visitOpen(String packaze, int access, String... modules) {
        this.opens.add(new ModuleOpenNode(packaze, access, modules));
    }

    @Override
    public void visitUse(String service) {
        this.uses.add(service);
    }

    @Override
    public void visitProvide(String service, String... providers) {
        this.provides.add(new ModuleProvideNode(service, providers));
    }

    public void accept(ClassVisitor classVisitor) {
        ModuleVisitor moduleVisitor = classVisitor.visitModule(name, access, version);

        if (moduleVisitor == null) {
            return;
        }

        if (this.mainClass != null) {
            moduleVisitor.visitMainClass(this.mainClass);
        }

        for (String packaze : this.packages) {
            moduleVisitor.visitPackage(packaze);
        }

        for (ModuleRequireNode require : this.requires) {
            require.accept(moduleVisitor);
        }

        for (ModuleExportNode export : this.exports) {
            export.accept(moduleVisitor);
        }

        for (ModuleOpenNode open : this.opens) {
            open.accept(moduleVisitor);
        }

        for (String use : this.uses) {
            moduleVisitor.visitUse(use);
        }

        for (ModuleProvideNode provide : this.provides) {
            provide.accept(moduleVisitor);
        }
    }

    @Override
    public String toString() {
        return "ModuleNode{" +
                "name='" + name + '\'' +
                ", access=" + access +
                ", version='" + version + '\'' +
                ", mainClass='" + mainClass + '\'' +
                ", packages=" + packages +
                ", requires=" + requires +
                ", exports=" + exports +
                ", opens=" + opens +
                ", uses=" + uses +
                ", provides=" + provides +
                '}';
    }
}

