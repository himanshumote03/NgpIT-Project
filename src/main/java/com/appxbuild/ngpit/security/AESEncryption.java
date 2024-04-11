package com.appxbuild.ngpit.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESEncryption {
    private static final String secretKey = "YourSecretKey"; // Change this to your secret key

    public static String encrypt(String strToEncrypt) {
        try {
            byte[] keyBytes = padKey(secretKey.getBytes(), 16); // 128-bit key length
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(strToEncrypt.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
            return null;
        }
    }

    public static String decrypt(String strToDecrypt) {
        try {
            byte[] keyBytes = padKey(secretKey.getBytes(), 16); // 128-bit key length
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(strToDecrypt));
            return new String(decryptedBytes);
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
            return null;
        }
    }

    private static byte[] padKey(byte[] key, int length) {
        byte[] paddedKey = new byte[length];
        System.arraycopy(key, 0, paddedKey, 0, Math.min(key.length, length));
        return paddedKey;
    }
}
