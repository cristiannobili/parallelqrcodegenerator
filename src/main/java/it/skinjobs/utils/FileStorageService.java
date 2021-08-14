package it.skinjobs.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Jessica Vecchia
 *
 *Trough the configuration properties(injected via constructor) a static server directory is created and its absolute path is generated.
 */
@Service
public class FileStorageService {

    private final Path fileStorageLocation;
    private final String uploadDir;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.uploadDir = fileStorageProperties.getUploadDir();
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String saveFile(BufferedImage bufferedImage, String filename) throws Exception {
        String filenameComplete = uploadDir + "/" + filename;
        File outputFile = new File(filenameComplete);
        ImageIO.write(bufferedImage, "jpg", outputFile);
        return filenameComplete;
    }

    public String storeFile(MultipartFile file) {        
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {            
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }            
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}