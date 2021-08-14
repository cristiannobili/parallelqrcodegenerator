package it.skinjobs;
import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import it.skinjobs.Prototype;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import javax.imageio.ImageIO;

public class Prototype {


    public  BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = 
          barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
    
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public void generateCode() {
        BufferedImage image;
		try {
			image = this.generateQRCodeImage("ciao sono Jessica e voglio un QR Code");
			String filenameComplete = "files/miofile.jpg";
            File outputFile = new File(filenameComplete);
            ImageIO.write(image, "jpg", outputFile);
		} catch(Exception e) {

		}
    }


}
