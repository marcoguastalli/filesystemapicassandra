package net.marco27.api.filesystemapi.util;

import static net.marco27.api.base.ApiConstants.ASTERISK;
import static net.marco27.api.base.ApiConstants.DOT;
import static net.marco27.api.base.util.date.DateUtils.DATE_HOURS_PATTERN;
import static org.apache.commons.io.comparator.PathFileComparator.PATH_SYSTEM_COMPARATOR;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.marco27.api.filesystemapi.domain.FileStructure;

public final class FileSystemApiUtil {

    /** If the input path isDirectory the content is listed and ordered
     *
     * @param path to be listed and ordered
     * @return a List of File instance objects */
    public static List<File> listAndSortDirectoryFiles(final File path) {
        List<File> result = new ArrayList<File>();
        if (path != null && path.isDirectory() && path.listFiles() != null) {
            result = Arrays.asList(path.listFiles());
            result.sort(PATH_SYSTEM_COMPARATOR);
        }
        return result;
    }

    /** Get the input fileName extension
     * 
     * @param fileName to be checked
     * @return the extension, or EMPTY */
    public static String getFileExtension(@NotNull final String fileName) {
        if (!isDirectory(fileName) && StringUtils.lastIndexOf(fileName, DOT) != -1) {
            return StringUtils.substring(fileName, fileName.lastIndexOf(DOT) + 1);
        } else {
            return EMPTY;
        }
    }

    /** Check the input filename if is a directory
     * 
     * @param fileName to be checked
     * @return true if the input fileName is a directory, false instead */
    public static boolean isDirectory(@NotNull final String fileName) {
        return Paths.get(fileName).toFile().isDirectory();
    }

    /** Creates a FileStructure recursively navigate the file-system starting from the inputPath
     * 
     * @param inputPath the starting directory
     * @return a FileStructure object
     * @throws IOException if something goes wrong */
    public static FileStructure createFileStructure(final String inputPath) throws IOException {
        final Path path = Paths.get(inputPath);
        final String fileName = path.getFileName().toString();
        final String filePath = path.toRealPath(LinkOption.NOFOLLOW_LINKS).toString();
        final String fileExtension = getFileExtension(fileName);
        final FileTime lastModifiedTime = Files.getLastModifiedTime(path, LinkOption.NOFOLLOW_LINKS);
        final LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastModifiedTime.toMillis()),
                TimeZone.getDefault().toZoneId());
        final String fileModifiedDate = DateTimeFormatter.ofPattern(DATE_HOURS_PATTERN).format(localDateTime);

        if (isDirectory(inputPath)) {
            final List<FileStructure> children = getFileStructures(inputPath);
            return new FileStructure.Builder(filePath, fileName, fileExtension)
                    .isDirectory(Boolean.TRUE)
                    .withTimestamp(fileModifiedDate)
                    .addChildren(children).build();
        } else {
            return new FileStructure.Builder(filePath, fileName, fileExtension)
                    .isDirectory(Boolean.FALSE)
                    .withTimestamp(fileModifiedDate)
                    .build();
        }
    }

    /** Creates a FileStructure recursively navigate the file-system starting from the inputPath
     * 
     * @param inputPath the starting directory
     * @return a List of FileStructure */
    private static List<FileStructure> getFileStructures(final String inputPath) {
        List<FileStructure> children = new ArrayList<>();
        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(inputPath), ASTERISK);
            for (final Path file : directoryStream) {
                children.add(createFileStructure(file.toRealPath(LinkOption.NOFOLLOW_LINKS).toString()));
            }
            directoryStream.close();
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(FileSystemApiUtil.class);
            logger.error("ERROR getFileStructures", e);
        }
        return children;
    }
}
