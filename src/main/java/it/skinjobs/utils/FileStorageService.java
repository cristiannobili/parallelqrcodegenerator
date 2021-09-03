package it.skinjobs.utils;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;


@Service
public class FileStorageService {

    private final String uploadDir;
    private final String resourceDir;
    private final String names;
    private final String surnames;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.uploadDir = fileStorageProperties.getUploadDir();
        this.resourceDir = fileStorageProperties.getResourceDir();
        this.names = this.resourceDir + "/" + fileStorageProperties.getNames();
        this.surnames =  this.resourceDir + "/" + fileStorageProperties.getSurnames();
        
    }

    public String saveFile(BufferedImage bufferedImage, String filename) throws Exception {
        String filenameComplete = uploadDir + "/" + filename;
        File outputFile = new File(filenameComplete);
        ImageIO.write(bufferedImage, "jpg", outputFile);
        return filenameComplete;
    }

    public  ArrayList<String> loadNames() throws Exception {
        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(this.names)));
        return lines;
    }

    public  ArrayList<String> loadSurnames() throws Exception {
        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(this.surnames)));
        return lines;
    }

    
}