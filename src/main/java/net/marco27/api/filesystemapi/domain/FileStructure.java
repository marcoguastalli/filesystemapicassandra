package net.marco27.api.filesystemapi.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@Table(value = "file_structure")
public final class FileStructure implements Serializable {

    @PrimaryKey("path")
    private String path;
    @Column
    private String name;
    @Column
    private String ext;
    @Column
    private String timestamp;
    @Column
    private boolean isDirectory;
    //@Column
    //private List<FileStructure> children;

    private FileStructure() {
    }

    private FileStructure(final Builder builder) {
        this.path = builder.path;
        this.name = builder.name;
        this.ext = builder.ext;
        this.timestamp = builder.timestamp;
        this.isDirectory = builder.isDirectory;
    }

    public static class Builder {
        private String path;
        private String name;
        private String ext;
        private String timestamp;
        private boolean isDirectory;
        private List<FileStructure> children;

        public Builder(final String path) {
            this.path = path;
        }

        public Builder(final String path, final String name, final String ext) {
            this.path = path;
            this.name = name;
            this.ext = ext;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withExtension(final String ext) {
            this.ext = ext;
            return this;
        }

        public Builder withTimestamp(final String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder isDirectory(final boolean isDirectory) {
            this.isDirectory = isDirectory;
            return this;
        }

        public Builder addChildren(final List<FileStructure> children) {
            this.children = children;
            return this;
        }

        public FileStructure build() {
            return new FileStructure(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileStructure that = (FileStructure) o;
        return isDirectory == that.isDirectory &&
                path.equals(that.path) &&
                Objects.equals(name, that.name) &&
                Objects.equals(ext, that.ext) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, name, ext, timestamp, isDirectory);
    }
}
