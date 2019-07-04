package net.marco27.api.filesystemapi.repository;

import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import net.marco27.api.filesystemapi.domain.FileStructure;

@Repository
public interface FileStructureCassandraRepository extends CassandraRepository<FileStructure, String> {

    @Query("SELECT * from file_structure where path in(?)")
    Optional<FileStructure> findById(String path);

    FileStructure findByPath(String path);
}
