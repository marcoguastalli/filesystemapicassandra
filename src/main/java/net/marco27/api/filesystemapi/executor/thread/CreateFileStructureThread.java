package net.marco27.api.filesystemapi.executor.thread;

import static net.marco27.api.filesystemapi.util.FileSystemApiUtil.createFileStructure;

import java.io.IOException;
import java.util.concurrent.Callable;

import net.marco27.api.filesystemapi.domain.FileStructure;

public class CreateFileStructureThread implements Callable {

    private final String path;

    public CreateFileStructureThread(final String path) {
        this.path = path;
    }

    @Override
    public FileStructure call() throws IOException {
        return createFileStructure(this.path);
    }

}
