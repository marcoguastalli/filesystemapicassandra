package net.marco27.api.filesystemapi.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public interface CassandraService {

    Cluster getCassandraCluster(String... addresses);

    Session getCassandraSession(Cluster cluster, String keyspaceName);

    boolean createKeyspace(Cluster cluster, String keyspaceName);
}