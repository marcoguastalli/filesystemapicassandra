package net.marco27.api.filesystemapi.util;

import static net.marco27.api.filesystemapi.util.FileSystemApiUtil.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import net.marco27.api.filesystemapi.domain.FileStructure;

public class FileSystemApiUtilTest {

    private static final String PATH = "/Users/marcoguastalli/temp";

    @Test
    public void testListAndSortDirectoryFiles() {
        assertEquals(new ArrayList<File>(), listAndSortDirectoryFiles(new File("non existing path")));
    }

    @Test
    public void testGetFileExtension() {
        assertEquals("DS_Store", getFileExtension(".DS_Store"));
        assertEquals("jpg", getFileExtension("image.jpg"));
        assertEquals("", getFileExtension("images"));
    }

    @Test
    public void testIsDirectory() {
        assertFalse(isDirectory(".DS_Store"));
        assertTrue(isDirectory(PATH));
    }

    @Test
    public void testCreateFileStructure() throws IOException {
        final FileStructure expected = new FileStructure.Builder(PATH).build();
        final FileStructure result = createFileStructure(PATH);
        assertNotEquals(expected, result);
    }

}
