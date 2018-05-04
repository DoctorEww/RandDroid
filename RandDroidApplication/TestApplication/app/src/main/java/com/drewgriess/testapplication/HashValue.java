package com.drewgriess.testapplication;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by DoctorEww on 2/23/2018.
 */

public class HashValue {

    private String hashInput;

    public HashValue (String inputValue)
    {
        setInput(inputValue);
    }
    public HashValue ()
    {
        setInput("");
    }
    public void setInput(String input)
    {
        hashInput=input;
    }

    public String getHexHash() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(hashInput.getBytes("UTF-8"));
        return new BigInteger(1, crypt.digest()).toString(16);
    }


}
