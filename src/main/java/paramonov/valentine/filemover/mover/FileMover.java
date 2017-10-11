package paramonov.valentine.filemover.mover;

import paramonov.valentine.filemover.logging.Loggable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

class FileMover implements Loggable {
    private final File sourceFile;
    private final String destinationDir;

    /**
     * @param sourceFile     file to move
     * @param destinationDir directory to move the file to
     */
    FileMover(File sourceFile, String destinationDir) {
        this.sourceFile = sourceFile;
        this.destinationDir = destinationDir;
    }

    /**
     * copies the file to destination directory and then deletes it. If the file is the
     * same as the destination file, then no action will be taken
     *
     * @return the source file
     */
    File move() {
        Path srcPath = sourceFile.toPath();
        Path destPath = Paths.get(destinationDir, sourceFile.getName());
        log("Moving %s to %s", srcPath, destPath);
        try {
            Files.createDirectories(Paths.get(destinationDir));
            if (!sameFile(srcPath, destPath)) {
                Files.copy(srcPath, destPath, REPLACE_EXISTING);
                Files.delete(srcPath);
            }
        } catch (IOException e) {
            logException(e);
        }
        return sourceFile;
    }

    private boolean sameFile(Path srcPath, Path destPath) {
        try {
            return Files.isSameFile(srcPath, destPath);
        } catch (IOException e) {
            return false;
        }
    }
}
