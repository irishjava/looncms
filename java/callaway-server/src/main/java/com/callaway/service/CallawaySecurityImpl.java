package com.callaway.service;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.Date;

public class CallawaySecurityImpl implements CallawaySecurity , InitializingBean {
    private static String nonce;
    private String clientSecret;

    static {
        nonce = RandomStringUtils.randomAscii(18).concat(new Date().toString());
         
    }

    public String nonce() {
        return nonce;
    }

    public String clientSecret() {
        return getClientSecret();
    }


    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(clientSecret,"secret.key must be set");
    }
}