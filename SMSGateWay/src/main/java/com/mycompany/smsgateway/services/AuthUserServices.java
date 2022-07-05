/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Minh Hieu Pham
 */
@Service
public class AuthUserServices {

    public String encryptPasswordByMD5(String password) {
        String encryptedPw = "";
        try {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            encryptedPw = s.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AuthUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encryptedPw;
    }

    public static void main(String[] args) {
        AuthUserServices as = new AuthUserServices();
        System.out.println("encrypted password: " + as.encryptPasswordByMD5("123456"));
    }
}
