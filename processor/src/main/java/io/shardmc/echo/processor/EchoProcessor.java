package io.shardmc.echo.processor;

import io.shardmc.echo.annotations.Echo;
import io.shardmc.echo.util.ConfigUtil;
import io.shardmc.echo.util.FileUtil;
import io.shardmc.echo.util.Version;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SupportedSourceVersion(SourceVersion.RELEASE_17)
@SupportedAnnotationTypes("io.shardmc.echo.annotations.Echo")
public class EchoProcessor extends AbstractProcessor {

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!annotations.isEmpty()) {
            TypeElement annotation = annotations.iterator().next();

            File mainDir = FileUtil.joinRelativePath("build/resources/main");
            mainDir.mkdirs();

            try {
                ConfigUtil.write(
                        FileUtil.joinPath(mainDir.getPath(), RootProcessor.id + ".meta.echo"),
                        Version.parse(RootProcessor.VERSION)
                );

                EchoProcessor.writeRef(mainDir, roundEnv, annotation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public static void writeRef(File main, RoundEnvironment environment, TypeElement annotation) throws IOException {
        Map<String, List<String>> ref = new ConcurrentHashMap<>();

        environment.getElementsAnnotatedWith(annotation)
                .forEach(element -> {
                    String key = element.getAnnotation(Echo.class).value();
                    List<String> list = ref.getOrDefault(key, new ArrayList<>());
                    list.add(element.toString());

                    ref.put(key, list);
                });

        ConfigUtil.write(
                FileUtil.joinPath(main.getPath(), RootProcessor.id + ".ref.echo"), ref
        );
    }
}
