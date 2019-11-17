package net.marco27.api.filesystemapi.repository;

import net.marco27.api.filesystemapi.domain.FileStructure;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStructureCassandraRepository extends CrudRepository<FileStructure, String> {

    @Query("SELECT * from file_structure where path =?0 ALLOW FILTERING")
    FileStructure findByPath(String path);

}
