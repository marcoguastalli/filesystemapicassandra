package net.marco27.api.filesystemapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.marco27.api.filesystemapi.domain.FileStructure;

@Repository
public interface FileStructureCrudRepository extends CrudRepository<FileStructure, String> {

    FileStructure findByPath(String path);
}
