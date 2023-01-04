package woid.node.annotation;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;

/**
 * A node that represents an annotation.
 *
 * @author Eric Bruneton
 */
public class AnnotationNode extends AnnotationVisitor {

    /**
     * The class descriptor of the annotation class.
     */
    private final String desc;

    /**
     * The name value pairs of this annotation. Each name value pair is stored as two consecutive
     * elements in the list. The name is a {@link String}, and the value may be a {@link Byte}, {@link
     * Boolean}, {@link Character}, {@link Short}, {@link Integer}, {@link Long}, {@link Float},
     * {@link Double}, {@link String} or {@link org.objectweb.asm.Type}, or a two elements String
     * array (for enumeration values), an {@link AnnotationNode}, or a {@link List} of values of one
     * of the preceding types. The list may be {@literal null} if there is no name value pair.
     */
    private final List<Object> values;

    /**
     * Visibility of this annotation
     */
    private final boolean visible;

    /**
     * Constructs a new {@link AnnotationNode}. <i>Subclasses must not use this constructor</i>.
     * Instead, they must use the {@link #AnnotationNode(int, String, List, boolean)} version.
     *
     * @param descriptor the class descriptor of the annotation class.
     * @throws IllegalStateException If a subclass calls this constructor.
     */
    public AnnotationNode(String descriptor, boolean visible) {
        this(Opcodes.ASM9, descriptor, new ArrayList<>(descriptor != null ? 2 : 1), visible);
    }

    /**
     * Constructs a new {@link AnnotationNode} to visit an array value.
     *
     * @param values where the visited values must be stored.
     */
    public AnnotationNode(List<Object> values, boolean visible) {
        this(Opcodes.ASM9, null, values, visible);
    }

    /**
     * Constructs a new {@link AnnotationNode}.
     *
     * @param api        the ASM API version implemented by this visitor. Must be one of the {@code
     *                   ASM}<i>x</i> values in {@link Opcodes}.
     * @param descriptor the class descriptor of the annotation class.
     */
    public AnnotationNode(int api, String descriptor, List<Object> values, boolean visible) {
        super(api);
        
        this.desc = descriptor;
        this.values = values;
        this.visible = visible;
    }

    // ------------------------------------------------------------------------
    // Implementation of the AnnotationVisitor abstract class
    // ------------------------------------------------------------------------

    /**
     * Makes the given visitor visit a given annotation value.
     *
     * @param annotationVisitor an annotation visitor. Maybe {@literal null}.
     * @param name              the value name.
     * @param value             the actual value.
     */
    public static void accept(AnnotationVisitor annotationVisitor, String name, Object value) {
        if (annotationVisitor != null) {
            if (value instanceof String[] typeValue) {
                annotationVisitor.visitEnum(name, typeValue[0], typeValue[1]);
            } else if (value instanceof AnnotationNode annotationValue) {
                annotationValue.accept(annotationVisitor.visitAnnotation(name, annotationValue.desc));
            } else if (value instanceof List) {
                AnnotationVisitor arrayAnnotationVisitor = annotationVisitor.visitArray(name);
                if (arrayAnnotationVisitor != null) {
                    List<?> arrayValue = (List<?>) value;
                    for (Object o : arrayValue) {
                        AnnotationNode.accept(arrayAnnotationVisitor, null, o);
                    }

                    arrayAnnotationVisitor.visitEnd();
                }
            } else {
                annotationVisitor.visit(name, value);
            }
        }
    }

    @Override
    public void visit(String name, Object value) {
        if (this.desc != null) {
            this.values.add(name);
        }

        /*if (value instanceof byte[] bytes) {
            values.add(Util.asArrayList(bytes));
        } else if (value instanceof boolean[] booleans) {
            values.add(Util.asArrayList(booleans));
        } else if (value instanceof short[] shorts) {
          values.add(Util.asArrayList(shorts));
        } else if (value instanceof char[] chars) {
            values.add(Util.asArrayList(chars));
        } else if (value instanceof int[] ints) {
            values.add(Util.asArrayList(ints));
        } else if (value instanceof long[] longs) {
            values.add(Util.asArrayList(longs));
        } else if (value instanceof float[] floats) {
            values.add(Util.asArrayList(floats));
        } else if (value instanceof double[] doubles) {
            values.add(Util.asArrayList(doubles));
        } else {*/
        this.values.add(value);
        //}
    }

    @Override
    public void visitEnum(String name, String descriptor, String value) {
        if (this.desc != null) {
            this.values.add(name);
        }

        this.values.add(new String[] { descriptor, value });
    }

    @Override
    public AnnotationVisitor visitAnnotation(String name, String descriptor) {
        if (this.desc != null) {
            this.values.add(name);
        }

        AnnotationNode annotation = new AnnotationNode(descriptor, this.visible);
        this.values.add(annotation);

        return annotation;
    }

    @Override
    public AnnotationVisitor visitArray(String name) {
        if (this.desc != null) {
            this.values.add(name);
        }

        List<Object> array = new ArrayList<>();
        this.values.add(array);

        return new AnnotationNode(array, this.visible);
    }

    // ------------------------------------------------------------------------
    // Accept methods
    // ------------------------------------------------------------------------

    /**
     * Makes the given visitor visit this annotation.
     *
     * @param visitor an annotation visitor. Maybe {@literal null}.
     */
    public void accept(AnnotationVisitor visitor) {
        if (visitor != null) {
            if (this.values != null) {
                for (int i = 0, n = this.values.size(); i < n; i += 2) {
                    AnnotationNode.accept(
                            visitor, (String) this.values.get(i), this.values.get(i + 1)
                    );
                }
            }
            
            visitor.visitEnd();
        }
    }

    public String getDesc() {
        return this.desc;
    }

    public List<Object> getValues() {
        return this.values;
    }

    public Object getValue(int index) {
        return this.getValues().get(index);
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean equals(String desc) {
        return this.getDesc().equals(desc);
    }
}
