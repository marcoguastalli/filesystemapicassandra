package net.marco27.api.filesystemapi.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Setter;

@Setter
@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "app")
public class ApplicationConfiguration {

    private int numberoOfThreads;
    private String cassandraAddresses;
    private String cassandraKeyspace;

    public int getNumberoOfThreads() {
        return numberoOfThreads;
    }

    public String getCassandraAddresses() {
        return cassandraAddresses;
    }

    public String getCassandraKeyspace() {
        return cassandraKeyspace;
    }
}
