package com.healthcareapp.backend.Encryption;

import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Component
public class SealedObjectConversions {

//    SecretKey key = SealedObjectConversions.generateKey(128);
//    IvParameterSpec ivParameterSpec = SealedObjectConversions.generateIv();

    String keyspec = "1234567812345678";
    String iv = "1234567812345678";

    SecretKeySpec key = new SecretKeySpec(keyspec.getBytes(), "AES");
    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());

    String algorithm = "AES/CBC/PKCS5Padding";

//    String password = "healthcare";
//    String salt = "12345678";

//    public SealedObjectConversions() throws NoSuchAlgorithmException {
//    }

    public SealedObject encryptObject(Serializable object) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, IOException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(this.algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, this.key, this.ivParameterSpec);
        SealedObject sealedObject = new SealedObject(object, cipher);
        return sealedObject;
    }

    public Serializable decryptObject(SealedObject sealedObject) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
            ClassNotFoundException, BadPaddingException, IllegalBlockSizeException,
            IOException {

        Cipher cipher = Cipher.getInstance(this.algorithm);
        cipher.init(Cipher.DECRYPT_MODE, this.key, this.ivParameterSpec);
        Serializable unsealObject = (Serializable) sealedObject.getObject(cipher);
        return unsealObject;
    }

//    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(n);
//        SecretKey key = keyGenerator.generateKey();
//        System.out.println(key);
//        return key;
//    }
//
//    public static IvParameterSpec generateIv() {
//        byte[] iv = new byte[16];
//        new SecureRandom().nextBytes(iv);
//        System.out.println(new IvParameterSpec(iv));
//        return new IvParameterSpec(iv);
//    }
}
