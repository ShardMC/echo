package woid3;

import woid3.util.ClassUtil;
import woid3.visitor.CopyClassVisitor;
import woid3.visitor.SimpleClassVisitor;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);
        ClassUtil.read(
                "woid2/test/DummyOriginal.class",
                new SimpleClassVisitor().onEnd(cv ->
                        ClassUtil.read("woid2/test/DummyEdit.class", new CopyClassVisitor(cv, null))
                )
        );
    }
}
