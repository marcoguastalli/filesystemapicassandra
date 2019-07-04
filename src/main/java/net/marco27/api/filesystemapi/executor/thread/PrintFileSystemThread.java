package net.marco27.api.filesystemapi.executor.thread;

import static java.nio.charset.StandardCharsets.UTF_8;
import static net.marco27.api.base.ApiConstants.*;
import static net.marco27.api.filesystemapi.util.FileSystemApiUtil.listAndSortDirectoryFiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.marco27.api.filesystemapi.domain.FileStructure;

/** Thread class that read the pathToPrint and sub-directories, printing in fileToPrint the files found */
public class PrintFileSystemThread implements Callable {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintFileSystemThread.class);

    private final String pathToPrint;
    private final String fileToPrint;

    public PrintFileSystemThread(final String pathToPrint, final String fileToPrint) {
        this.pathToPrint = pathToPrint;
        this.fileToPrint = fileToPrint;
    }

    /** Recursively read the pathToPrint and sub-directories, printing in fileToPrint the files found
     * 
     * @return a FileStructure with pathToPrint
     * @throws Exception if something goes wrong */
    @Override
    public FileStructure call() throws Exception {
        BufferedWriter bufferedWriter = null;
        try {
            LOGGER.info(String.format("Printing %s into file %s...", this.pathToPrint, this.fileToPrint));

            bufferedWriter = Files.newBufferedWriter(Paths.get(fileToPrint), UTF_8);
            printDirStructure(bufferedWriter, this.pathToPrint);

            LOGGER.info(String.format("Printed %s into file %s...", this.pathToPrint, this.fileToPrint));
            return new FileStructure.Builder(pathToPrint).build();
        } catch (Exception e) {
            LOGGER.error(ERROR + e.getMessage());
            throw e;
        } finally {
            bufferedWriter.close();
        }
    }

    /** Recursively print the input pathToPrint directory structure
     * 
     * @param bufferedWriter output file buffer
     * @param pathToPrint path to be printed
     * @throws IOException if something goes wrong */
    private void printDirStructure(BufferedWriter bufferedWriter, final String pathToPrint)
            throws IOException {
        final List<File> filesInDirectorySorted = listAndSortDirectoryFiles(Paths.get(pathToPrint).toFile());
        for (File file : filesInDirectorySorted) {
            if (file.isDirectory()) {
                bufferedWriter.write(SQUARE_BRACKET_OPEN + file.getAbsolutePath() + SQUARE_BRACKET_CLOSE + NEW_LINE);
                printDirStructure(bufferedWriter, file.getAbsolutePath());
            } else {
                bufferedWriter.write(file.getAbsolutePath() + NEW_LINE);
            }
        }
    }

}
