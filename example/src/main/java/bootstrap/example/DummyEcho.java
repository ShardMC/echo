package bootstrap.example;

import io.shardmc.echo.annotations.Echo;

@Echo("dummy.Dummy")
public abstract class DummyEcho {
    public String version = "test";

    public void sayBruh() {
        System.out.println("bruh!");
    }
}
