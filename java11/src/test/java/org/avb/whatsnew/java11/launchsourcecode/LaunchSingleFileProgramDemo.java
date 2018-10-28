package org.avb.whatsnew.java11.launchsourcecode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 * Since Java 11, it is possible to launch a single source code file invoking the command:
 * java HelloWorld.java
 * The launcher will compile the source code it before the start.
 *
 * In this example, a new process for the launcher is created programmatically.
 */
public class LaunchSingleFileProgramDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(LaunchSingleFileProgramDemo.class);

    private final static String FILE_NAME = "HelloWorld.java";

    @Test
    public void testLaunchHelloWorld() throws IOException, URISyntaxException, InterruptedException {
        var pathToSourceCode = getPathToSourceCodeFile();
        LOGGER.info("Loaded source code: {}", pathToSourceCode);

        var process = startProcess("java " + pathToSourceCode);
        int code = process.waitFor();

        LOGGER.info("Process code: {}", code);
    }

    private String getPathToSourceCodeFile() throws URISyntaxException {
        var uri = getClass().getClassLoader().getResource(FILE_NAME);
        return Paths.get(uri.toURI()).toString();
    }

    private Process startProcess(String commandWithOptions) throws IOException {
        return new ProcessBuilder()
                .command(commandWithOptions.split("\\s+"))
                .inheritIO()
                .start();
    }
}
