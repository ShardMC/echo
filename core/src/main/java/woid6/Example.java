package woid6;

import org.objectweb.asm.util.TraceClassVisitor;
import woid6.event.EventfulClassVisitor;

public class Example {

    public static void main(String[] args) throws InterruptedException {
        //Thread.sleep(10000);

        // step 1 init EvCV
        // step 2 init EcCv
        // step 3 read to EcCV and pass everything to StackCV (woid8)
        // step 4 EvCV.onMethod -> synchronize with EcCV
        // step 5 EvCV.onEnd -> apply StackCV
        // step 6 read to EvCV
        long start = System.currentTimeMillis();
        ClassUtil.read(
                "woid2/test/DummyOriginal.class",
                new EventfulClassVisitor(new TraceClassVisitor(null))
                        .onEnd(cv -> {
                            for (int i = 0; i < 100; i++) {
                                ClassUtil.read("woid2/test/DummyEdit.class", new EchoClassVisitor(cv));
                            }
                        })
        );

        System.out.println(System.currentTimeMillis() - start + "ms");
    }
}
