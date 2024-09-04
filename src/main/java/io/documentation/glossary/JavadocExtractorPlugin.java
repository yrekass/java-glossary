package io.documentation.glossary;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.Collection;

@Mojo(name = "extract-javadoc", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class JavadocExtractorPlugin extends AbstractMojo {

    @Parameter(property = "sourceDirectory", defaultValue = "${project.build.sourceDirectory}")
    private File sourceDirectory;

    @Parameter(property = "annotationClass", required = true)
    private String annotationClass;

    @Override
    public void execute() throws MojoExecutionException {
        try {
            JavaProjectBuilder builder = new JavaProjectBuilder();
            builder.addSourceTree(sourceDirectory);

            // Parcourir toutes les classes dans le projet
            Collection<JavaSource> sources = builder.getSources();
            for (JavaSource source : sources) {
                for (JavaClass javaClass : source.getClasses()) {
                    if (javaClass.isA(annotationClass)) {
                        // Récupérer la Javadoc de la classe annotée
                        String classJavadoc = javaClass.getComment();
                        getLog().info("Class: " + javaClass.getFullyQualifiedName());
                        getLog().info("Javadoc: " + classJavadoc);
                    }
                }
            }
        } catch (Exception e) {
            throw new MojoExecutionException("Failed to extract Javadoc", e);
        }
    }
}