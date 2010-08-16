package com.callaway;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {

    public static byte[] md5digest(byte[] input) {
        MessageDigest messageDigest;
        try {
            messageDigest = java.security.MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            throw new UnsupportedOperationException(e);
        }
        return messageDigest.digest(input);
    }
}