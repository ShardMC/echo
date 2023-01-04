package bootstrap.example;

import io.shardmc.echo.annotations.Echo;
import io.shardmc.echo.annotations.Insert;
import io.shardmc.echo.annotations.Shadow;

@Echo("dummy.Dummy")
public abstract class DummyEcho2 {
    @Shadow
    public String version;

    @Shadow
    public abstract void sayBruh();

    public void sayHi() {
        System.out.println("hi!");
        System.out.println("HelloTM, version " + version);
    }

    public void sayBruhAndHi() {
        this.sayBruh();
        this.sayHi();
    }

    @Insert(method = "test", desc = "()V")
    public void test() {
        System.out.println("Additional Test");
    }
}
