package woid3;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);
        ClassUtil.read(
                "woid2/test/DummyOriginal.class",
                new SimpleClassVisitor().onEnd(cv ->
                        ClassUtil.read("woid2/test/DummyEdit.class", new CopyClassVisitor(cv))
                )
        );
    }
}
