package bootstrap;

import io.shardmc.echo.core.Echo;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        Thread.sleep(10000);

        Echo.load("test");

        long start = System.currentTimeMillis();

        //for (int i = 0; i < 100; i++) {
            Echo.finish();
        //}

        System.out.println("finish: " + (System.currentTimeMillis() - start));

        //Echo.finish();

        Class<?> clazz = Class.forName("dummy.Dummy", true, Echo.getClassLoader());
        Object instance = clazz.getDeclaredConstructor().newInstance();
        System.out.println(Arrays.toString(clazz.getDeclaredFields()));
        clazz.getMethod("sayBruhAndHi").invoke(instance);
    }
}
