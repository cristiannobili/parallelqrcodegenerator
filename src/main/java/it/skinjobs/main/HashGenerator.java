package it.skinjobs.main;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;

public class HashGenerator {

    public static String hash(String message) throws Exception{
        String timeStamp = " - " + new Date().getTime();
        message = timeStamp + message;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
