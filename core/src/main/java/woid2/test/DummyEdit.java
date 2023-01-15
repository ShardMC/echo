package woid2.test;

import io.shardmc.echo.annotations.Add;
import io.shardmc.echo.annotations.Shadow;

public class DummyEdit {

    public String test = "test2";

    public void test() {
        System.out.println("Hello World");
    }

    public void test2() {
        System.out.println(this.test);
    }

    @Shadow
    public void test3() {
        this.test();
    }

    @Add(method = "", desc = "")
    public void test4() {
        this.test();
    }
}
