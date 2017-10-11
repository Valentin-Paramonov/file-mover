package paramonov.valentine.filemover.mover;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class ProcessedFileLog {
    private final List<String> fileLog = new CopyOnWriteArrayList<>();

    boolean storeProcessedFile(String fileName) {
        return fileLog.add(fileName);
    }

    List<String> getProcessedFiles() {
        return Collections.unmodifiableList(fileLog);
    }
}
