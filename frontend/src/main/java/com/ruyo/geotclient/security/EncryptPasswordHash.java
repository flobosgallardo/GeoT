package com.ruyo.geotclient.security;


import java.security.MessageDigest;

public class EncryptPasswordHash {

    public String encrypt(String pass){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pass.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte aHash : hash) {
                String hex = Integer.toHexString(0xff & aHash);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }



}
