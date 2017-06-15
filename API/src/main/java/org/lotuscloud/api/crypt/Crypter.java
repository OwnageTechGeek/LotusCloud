package org.lotuscloud.api.crypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class Crypter {

    public static byte[] encrypt(Key key, byte[] value, String algorithm) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(value);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static byte[] decrypt(Key key, byte[] value, String algorithm) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(value);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static SecretKey generateKey(int keysize) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(keysize);
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static KeyPair generateKeyPair(int keysize) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(keysize);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static byte[] toByteArray(Serializable object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos);
        out.writeObject(object);
        return bos.toByteArray();
    }

    public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = new ObjectInputStream(bis);
        return in.readObject();
    }
}