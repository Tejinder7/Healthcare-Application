package com.healthcareapp.backend.Encryption;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@Component
public class ObjectEncryption {

        private static final String ALGO = "AES"; // Default uses ECB PKCS5Padding

        public String encrypt(String Data) throws Exception {
            String secretKey = "1234567812345678";
            String encodedBase64Key = encodeKey(secretKey);
            System.out.println(encodedBase64Key);
            Key key = generateKey(encodedBase64Key);
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(Data.getBytes());
            String encryptedValue = Base64.getEncoder().encodeToString(encVal);
            return encryptedValue;
        }

        public String decrypt(String strToDecrypt) {
            String secretKey = "1234567812345678";
            String encodedBase64Key = encodeKey(secretKey);
            try {
                Key key = generateKey(encodedBase64Key);
                Cipher cipher = Cipher.getInstance(ALGO);
                cipher.init(Cipher.DECRYPT_MODE, key);
                return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
            } catch (Exception e) {
                System.out.println("Error while decrypting: “ + e.toString()");
            }
            return null;
        }

        private Key generateKey(String secret) throws Exception {
            byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
            Key key = new SecretKeySpec(decoded, ALGO);
            return key;
        }

        public static String decodeKey(String str) {
            byte[] decoded = Base64.getDecoder().decode(str.getBytes());
            return new String(decoded);
        }

        public String encodeKey(String str) {
            byte[] encoded = Base64.getEncoder().encode(str.getBytes());
            return new String(encoded);
        }

}
