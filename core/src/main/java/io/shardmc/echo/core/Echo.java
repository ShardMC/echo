package io.shardmc.echo.core;

import io.shardmc.echo.core.data.EchoClassLoader;
import io.shardmc.echo.core.data.EchoData;
import io.shardmc.echo.core.transformer.TransformationManager;
import io.shardmc.echo.util.ConfigUtil;
import io.shardmc.echo.util.Version;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class Echo {

    private static final Version version = Version.parse("1.0.0");
    private static final EchoData globalConfig = new EchoData();

    private static final EchoClassLoader classLoader = new EchoClassLoader();

    /**
     * @param id id of your module
     */
    @SuppressWarnings("unchecked")
    public static void load(String id) {
        try {
            if(!Echo.version.supports((Version) ConfigUtil.read(id + ".meta.echo"))) {
                System.out.println("The config (" + id + ") doesn't support this version of Echo (or vice-versa)!");
            }

            Echo.globalConfig.merge((Map<String, List<String>>) ConfigUtil.read(id + ".ref.echo"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static EchoClassLoader getClassLoader() {
        return Echo.classLoader;
    }

    public static void finish() {
        try {
            TransformationManager.start(Echo.globalConfig);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static Version getVersion() {
        return Echo.version;
    }
}
