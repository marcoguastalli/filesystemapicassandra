package net.marco27.api.filesystemapi.domain;

import static net.marco27.api.base.ApiConstants.SLASH;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/** The file of the pathToPrint directory are printed in fileToPrint
 *
 * pathToPrint: /Users/marcoguastalli/opt/docker
 *
 * fileToPrint: /Users/marcoguastalli/Documents/docker.txt */
public final class PathFileToPrint implements Serializable {

    private String pathToPrint;
    private String fileToPrint;

    // com.fasterxml.jackson.databind
    private PathFileToPrint() {
    }

    private PathFileToPrint(final String pathToPrint, final String fileToPrint) {
        this.pathToPrint = pathToPrint;
        this.fileToPrint = fileToPrint;
    }

    public String getPathToPrint() {
        return pathToPrint;
    }

    public String getFileToPrint() {
        return fileToPrint;
    }

    public static class Builder {
        private String pathToPrint;
        private String fileToPrint;

        public Builder(final String pathToPrint, final String fileToPrint) {
            this.pathToPrint = StringUtils.startsWith(pathToPrint, SLASH) ? pathToPrint : SLASH.concat(pathToPrint);
            this.fileToPrint = StringUtils.startsWith(fileToPrint, SLASH) ? fileToPrint : SLASH.concat(fileToPrint);
        }

        public PathFileToPrint build() {
            return new PathFileToPrint(pathToPrint, fileToPrint);
        }

    }

    @Override
    public String toString() {
        return "{ pathToPrint: \"" + pathToPrint + "\"" +
                ", fileToPrint: \"" + fileToPrint + "\" }";
    }
}
