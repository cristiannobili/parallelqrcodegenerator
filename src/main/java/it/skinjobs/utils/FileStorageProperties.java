package it.skinjobs.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")    //file.uploadDr 
public class FileStorageProperties {
    private String uploadDir;
    private String resourceDir;
    private String names;
    private String surnames;

    public String getResourceDir() {
        return resourceDir;
    }

    public void setResourceDir(String resourceDir) {
        this.resourceDir = resourceDir;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
    
    
}
