package com.callaway.dto;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * The Credentials.token is the product of MD5(dailyNonce + client secret)
 * @author bryan
 */
public class Credentials implements Serializable {
    private static final long serialVersionUID = 1L;
    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static Credentials generate(String nonce, String secret) {
        Credentials c = new Credentials();
        c.token = DigestUtils.md5Hex(nonce + secret);
        return c;
    }

    public String toString() {
    	return new ToStringBuilder(this).appendSuper(super.toString())
	    .append("token",token)
    	.toString();
    }
}