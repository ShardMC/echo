package io.shardmc.echo.processor;

import io.shardmc.echo.annotations.Root;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_17)
@SupportedAnnotationTypes("io.shardmc.echo.annotations.Root")
public class RootProcessor extends AbstractProcessor {

    public static final String VERSION = "1.0.0";
    public static String id;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            RootProcessor.id = roundEnv.getElementsAnnotatedWith(annotation)
                    .iterator().next().getAnnotation(Root.class).id();
            break;
        }

        return true;
    }
}
