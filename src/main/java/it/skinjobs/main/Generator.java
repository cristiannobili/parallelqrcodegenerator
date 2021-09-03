package it.skinjobs.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Generator {
    @Autowired
    QrCodeGenerator qrCodeGenerator;

    @Autowired
	NamesContainer names;

    public void generate() {
        String fullname = this.names.getRandomName() +" " + this.names.getRandomSurname();
        this.qrCodeGenerator.generateCode(fullname);
    }
}
