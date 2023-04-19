package com.healthcareapp.backend.Encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoEncryption {
        public String encrypt(String str) {
            try {
                String data = str;
                String key = "1234567812345678";
                String iv = "1234567812345678";

                Base64.Decoder decoder = Base64.getDecoder();
                byte[] encrypted1 = decoder.decode(data);

                Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
                SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
                IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

                cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                System.out.println(originalString.trim());
                return originalString.trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
}
