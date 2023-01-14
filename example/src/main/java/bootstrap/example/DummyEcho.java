package bootstrap.example;

import io.shardmc.echo.annotations.Echo;
import io.shardmc.echo.annotations.Insert;

@Echo("dummy.Dummy")
public abstract class DummyEcho {
    @Insert(method = "test", desc = "()V")
    public void test() {
        System.out.println("Additional Test");
    }

    public void bruh() {
        System.out.println("bruh");
    }
}
