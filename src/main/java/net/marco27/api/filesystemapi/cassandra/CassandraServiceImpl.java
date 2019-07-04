package net.marco27.api.filesystemapi.cassandra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.InvalidQueryException;

@Service
public class CassandraServiceImpl implements CassandraService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CassandraServiceImpl.class);

    private static final String CQL_KEYSPACE_CREATE = "CREATE KEYSPACE IF NOT EXISTS %s WITH replication = {'class':'SimpleStrategy','replication_factor':1};";
    private static final String CQL_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS %s;";

    @Override
    public Cluster getCassandraCluster(final String... addresses) {
        return Cluster.builder().withoutMetrics().addContactPoints(addresses).build();
    }

    @Override
    public Session getCassandraSession(final Cluster cluster, final String keyspaceName) {
        Session result = null;
        try {
            result = cluster.connect(keyspaceName);
        } catch (InvalidQueryException e) {
            LOGGER.error(String.format("Error getCassandraSession with keyspaceName '%s'", keyspaceName));
            if (createKeyspace(cluster, keyspaceName)) {
                result = cluster.connect(keyspaceName);
            }
        }
        return result;
    }

    @Override
    public boolean createKeyspace(final Cluster cluster, final String keyspaceName) {
        try (Session session = cluster.connect()) {
            final String query = String.format(CQL_KEYSPACE_CREATE, keyspaceName);
            final ResultSet resultSet = session.execute(query);
            LOGGER.info("createKeyspace statement: {}", resultSet.getExecutionInfo().getStatement());
            return resultSet.wasApplied();
        }
    }
}