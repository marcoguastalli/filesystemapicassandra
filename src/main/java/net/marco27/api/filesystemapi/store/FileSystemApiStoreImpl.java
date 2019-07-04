package net.marco27.api.filesystemapi.store;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import net.marco27.api.filesystemapi.cassandra.CassandraServiceImpl;
import net.marco27.api.filesystemapi.configuration.ApplicationConfiguration;
import net.marco27.api.filesystemapi.domain.FileStructure;
import net.marco27.api.filesystemapi.repository.FileStructureCassandraRepository;

@Service
public class FileSystemApiStoreImpl extends CassandraServiceImpl implements FileSystemApiStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemApiStoreImpl.class);

    private static final String CQL_SELECT_BY_PATH = "SELECT * FROM file_structure WHERE path = '%s'";

    private ApplicationConfiguration applicationConfiguration;
    private FileStructureCassandraRepository fileStructureCrudRepository;

    public FileSystemApiStoreImpl(@Autowired final ApplicationConfiguration applicationConfiguration,
            @Autowired final FileStructureCassandraRepository fileStructureCassandraRepository) {
        this.applicationConfiguration = applicationConfiguration;
        this.fileStructureCrudRepository = fileStructureCassandraRepository;
    }

    @Override
    public FileStructure findFileStructureById(final String path) {
        final Optional<FileStructure> result = this.fileStructureCrudRepository.findById(path);
        return result.orElse(null);
    }

    @Override
    public FileStructure findFileStructureByPath(final String path) {
        return this.fileStructureCrudRepository.findByPath(path);
    }

    @Override
    public FileStructure saveFileStructure(final FileStructure fileStructure) {
        return this.fileStructureCrudRepository.save(fileStructure);
    }

    @Override
    public void deleteFileStructure(final FileStructure fileStructure) {
        this.fileStructureCrudRepository.delete(fileStructure);
    }

    @Override
    public FileStructure loadFileStructure(final String path) {
        try (Cluster cluster = getCassandraCluster(this.applicationConfiguration.getCassandraAddresses())) {
            try (Session session = getCassandraSession(cluster, this.applicationConfiguration.getCassandraKeyspace())) {
                final String query = String.format(CQL_SELECT_BY_PATH, path);
                ResultSet resultSet = session.execute(query);
                for (final Row row : resultSet) {
                    final String rowPath = row.getString("path");
                    return new FileStructure.Builder(rowPath).build();
                }
            }
        }
        return null;
    }

}
