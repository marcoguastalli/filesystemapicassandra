package net.marco27.api.filesystemapi.service;

import net.marco27.api.filesystemapi.domain.FileStructure;
import net.marco27.api.filesystemapi.domain.PathFileToPrint;

public interface FileSystemApiService {

    PathFileToPrint printPathToFile(String pathToPrint, String fileToPrint);

    FileStructure createFileStructure(String path);

}
