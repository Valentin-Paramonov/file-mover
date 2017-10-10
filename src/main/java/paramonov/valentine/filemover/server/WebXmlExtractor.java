package paramonov.valentine.filemover.server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

final class WebXmlExtractor {
    private static final String webXmlLocationInsideJar =
        "META-INF/resources/WEB-INF/web.xml";

    /**
     * Extracts the web.xml from JAR and writes it to disk
     */
    String extractWebXmlFromJar() throws IOException {
        String webXmlLocationOnDisk =
            "/tmp/tomcat-" + UUID.randomUUID().toString() + "-web.xml";
        try (
            InputStream webXml = getClass()
                .getClassLoader()
                .getResourceAsStream(webXmlLocationInsideJar)
        ) {
            Files.copy(webXml, Paths.get(webXmlLocationOnDisk));
        }
        return webXmlLocationOnDisk;
    }
}
