package paramonov.valentine.filemover.mover;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class DirectoryPoller {
    private final Executor executor;
    private final ProcessedFileLog log;
    private Set<File> lastTimeSeenFiles = new HashSet<>();

    /**
     * @param executor the executor that will be used to schedule file-moving jobs
     * @param log      the log that will store processed files
     */
    DirectoryPoller(Executor executor, ProcessedFileLog log) {
        this.executor = executor;
        this.log = log;
    }

    /**
     * poll the directory for changes and schedule jobs to move files to the destination
     * directory
     *
     * @param sourceDirectory      the directory to move files from
     * @param destinationDirectory the directory to move files to
     */
    void poll(String sourceDirectory, String destinationDirectory) {
        Set<File> newFiles = newFilesIn(sourceDirectory);
        diff(newFiles, lastTimeSeenFiles)
            .stream()
            .map(file -> new FileMover(file, destinationDirectory))
            .forEach(this::submitForExecution);
        lastTimeSeenFiles = newFiles;
    }

    private Set<File> newFilesIn(String sourceDirectory) {
        return Optional
            .ofNullable(new File(sourceDirectory).listFiles())
            .map(Arrays::stream)
            .orElse(Stream.empty())
            .filter(File::isFile)
            .collect(Collectors.toSet());
    }

    private Set<File> diff(Set<File> newFiles, Set<File> oldFiles) {
        HashSet<File> diff = new HashSet<>(newFiles);
        diff.removeAll(oldFiles);
        return diff;
    }

    private void submitForExecution(FileMover fileMover) {
        CompletableFuture
            .supplyAsync(fileMover::move, executor)
            .thenAccept(file ->
                log.storeProcessedFile(file.getAbsolutePath())
            );
    }
}
