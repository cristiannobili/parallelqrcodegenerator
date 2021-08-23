package it.skinjobs.main;

import java.awt.image.BufferedImage;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.zxing.qrcode.QRCodeWriter;

import it.skinjobs.main.QrCodeGenerator;
import it.skinjobs.utils.FileStorageService;

@Service
public class QrCodeGenerator {

    @Autowired
    FileStorageService fileStorageService;


    public  BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = 
          barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
    
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public void generateCode(String text) {
        BufferedImage image;
		try {
			image = this.generateQRCodeImage(text);
            this.fileStorageService.saveFile(image, "miofile.jpg");
		} catch(Exception e) {

		}
    }


}
