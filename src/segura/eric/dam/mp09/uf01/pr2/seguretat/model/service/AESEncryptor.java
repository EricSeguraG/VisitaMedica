package segura.eric.dam.mp09.uf01.pr2.seguretat.model.service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.Key;

/**
 * Classe per encriptar i desencriptar dades utilitzant AES (bidireccional).
 */
public class AESEncryptor {

    // Clau secreta (ha de ser de 16, 24 o 32 bytes per AES)
    // IMPORTANT: En un entorn real, aquesta clau NO estaria hardcodejada.
    private static final String KEY_STRING = "AquestaEsUnaClauSecreta12345";
    private static final String ALGORITHM = "AES";

    private static Key getSecretKey() {
        // Assegurar que la clau tingui 16 bytes (128 bits) per AES-128
        byte[] keyBytes = KEY_STRING.getBytes(StandardCharsets.UTF_8);
        byte[] finalKey = new byte[16];
        System.arraycopy(keyBytes, 0, finalKey, 0, Math.min(keyBytes.length, finalKey.length));
        return new SecretKeySpec(finalKey, ALGORITHM);
    }

    /**
     * Encripta una cadena de text amb AES.
     * @param dataToEncrypt El text a encriptar.
     * @return El text encriptat en Base64, o null en cas d'error.
     */
    public static String encrypt(String dataToEncrypt) {
        if (dataToEncrypt == null || dataToEncrypt.isEmpty()) {
            return null;
        }
        try {
            Key key = getSecretKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            byte[] encryptedBytes = cipher.doFinal(dataToEncrypt.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (Exception e) {
            System.err.println("Error durant l'encriptació AES: " + e.getMessage());
            return null;
        }
    }

    /**
     * Desencripta una cadena de text encriptada amb AES.
     * @param dataToDecrypt El text encriptat (en Base64).
     * @return El text original, o null en cas d'error.
     */
    public static String decrypt(String dataToDecrypt) {
        if (dataToDecrypt == null || dataToDecrypt.isEmpty()) {
            return null;
        }
        try {
            Key key = getSecretKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] decodedBytes = Base64.getDecoder().decode(dataToDecrypt);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            
            return new String(decryptedBytes, StandardCharsets.UTF_8);

        } catch (Exception e) {
            System.err.println("Error durant la desencriptació AES. Possible clau incorrecta o format invàlid. " + e.getMessage());
            return null;
        }
    }
}