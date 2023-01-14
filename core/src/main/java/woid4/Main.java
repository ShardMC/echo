package woid4;

public class Main {

    public static void main(String[] args) {
        SimpleClassVisitor scv = ClassUtil.read("woid2/test/DummyOriginal.class", new SimpleClassVisitor().onEnd(cv -> {
            ClassUtil.read("woid2/test/DummyEdit.class", new CopyClassVisitor(cv));
        }));
    }
}
