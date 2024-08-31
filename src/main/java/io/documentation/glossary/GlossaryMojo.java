package io.documentation.glossary;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

@Mojo( name = "glossary", defaultPhase = LifecyclePhase.COMPILE )
public class GlossaryMojo extends AbstractMojo {
    @Parameter( defaultValue = "${project.build.directory}", property = "outputDir", required = true )
    private File outputDirectory;
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

    }
}
