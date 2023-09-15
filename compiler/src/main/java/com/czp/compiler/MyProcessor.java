package com.czp.compiler;

import com.czp.annotation.MyAnnotation;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;


@AutoService(value = {Processor.class})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MyProcessor extends AbstractProcessor {
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        System.out.println("MyProcessor------------init---------------");
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("MyProcessor------------process---------------");
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "====== hello ======");
        String str = "import com.czp.annotation.MyAnnotation;\n" +
                "@MyAnnotation\n" +
                "class A {\n" +
                "    \n" +
                "}";
        if (!annotations.isEmpty()) {
            Filer filer = processingEnv.getFiler();
            OutputStream outputStream = null;
            try {
                JavaFileObject sourceFile = filer.createSourceFile("A");
                outputStream = sourceFile.openOutputStream();
                outputStream.write(str.getBytes());
                outputStream.flush();
            }catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> types = new HashSet<>();
        types.add(MyAnnotation.class.getCanonicalName());
        return types;
    }
}