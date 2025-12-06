package segura.eric.dam.mp09.uf01.pr2.seguretat.model.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe per generar hash MD5 (unidireccional).
 */
public class MD5Encryptor {

    public static String encrypt(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            // Convertir el byte array a String hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                // Afegir '0' al davant si el valor és < 16 (per obtenir sempre dos dígits)
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            // Aquest error normalment no hauria de passar ja que MD5 és un estàndard
            System.err.println("Error: Algorisme MD5 no trobat. " + e.getMessage());
            return null;
        }
    }
}