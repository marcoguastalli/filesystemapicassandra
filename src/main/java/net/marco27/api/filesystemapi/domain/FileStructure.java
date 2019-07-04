package net.marco27.api.filesystemapi.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

/** A file like:
 *
 * {
 *   path: "/Users/marcoguastalli/temp",
 *   name: "temp",
 *   ext: null,
 *   timestamp: "2019-02-19 11:35:56",
 *   children: [
 *     {
 *       path: "/Users/marcoguastalli/temp/.DS_Store",
 *       name: ".DS_Store",
 *       ext: "DS_Store",
 *       timestamp: "2019-02-12 09:01:05",
 *       children: [
 *
 *       ],
 *       directory: false
 *     },
 *     {
 *       path: "/Users/marcoguastalli/temp/docker.txt",
 *       name: "docker.txt",
 *       ext: "txt",
 *       timestamp: "2019-02-19 11:35:56",
 *       children: [
 *
 *       ],
 *       directory: false
 *     }
 *   ],
 *   directory: true
 * }
 *
 *
 *
 * */
@Entity
public final class FileStructure implements Serializable {

    @Id
    @NotNull(message = "File path can not be null!")
    private String path;
    @NotNull(message = "File name can not be null!")
    private String name;
    private String ext;
    private String timestamp;
    private boolean isDirectory;
    @OneToMany(targetEntity = FileStructure.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<FileStructure> children;

    // JPA constructor
    private FileStructure() {
    }

    private FileStructure(final Builder builder) {
        this.path = builder.path;
        this.name = builder.name;
        this.ext = builder.ext;
        this.timestamp = builder.timestamp;
        this.isDirectory = builder.isDirectory;
        this.children = builder.children;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getExt() {
        return ext;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public List<FileStructure> getChildren() {
        return children;
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
    public String toString() {
        return new ToStringBuilder(this)
                .append("path", path)
                .append("name", name)
                .append("ext", ext)
                .append("timestamp", timestamp)
                .append("isDirectory", isDirectory)
                .append("children", children)
                .toString();
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
                Objects.equals(path, that.path) &&
                Objects.equals(name, that.name) &&
                Objects.equals(ext, that.ext) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, name, ext, timestamp, isDirectory, children);
    }
}
