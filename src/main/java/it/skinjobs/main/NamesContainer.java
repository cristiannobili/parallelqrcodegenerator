package it.skinjobs.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.skinjobs.utils.FileStorageService;
import java.util.ArrayList;

@Service
public class NamesContainer {

    @Autowired
    FileStorageService fileStorageService;
    
    private ArrayList<String> names;
    private ArrayList<String> surnames;
    
    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<String> getSurnames() {
        return surnames;
    }
 
    public void init() {
        try {
            this.names = fileStorageService.loadNames();
            this.surnames =  fileStorageService.loadSurnames();
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
       
    }

    public String getRandomName() {
        int value = (int)(Math.random() * this.names.size());
        return this.names.get(value);
    }

    
    public String getRandomSurname() {
        int value = (int)(Math.random() * this.surnames.size());
        return this.surnames.get(value);
    }

   
}
