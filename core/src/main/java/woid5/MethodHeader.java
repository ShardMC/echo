package woid5;

import java.util.Arrays;

public class MethodHeader {

    private int access;
    private String name;
    private String descriptor;
    private String signature;
    private String[] exceptions;

    public MethodHeader(int access, String name, String descriptor, String signature, String[] exceptions) {
        this.access = access;
        this.name = name;
        this.descriptor = descriptor;
        this.signature = signature;
        this.exceptions = exceptions;
    }

    public int getAccess() {
        return this.access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String[] getExceptions() {
        return this.exceptions;
    }

    public void setExceptions(String[] exceptions) {
        this.exceptions = exceptions;
    }

    @Override
    public String toString() {
        return "MethodHeader{" +
                "access=" + access +
                ", name='" + name + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", signature='" + signature + '\'' +
                ", exceptions=" + Arrays.toString(exceptions) +
                '}';
    }
}
