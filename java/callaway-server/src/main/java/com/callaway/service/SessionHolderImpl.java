package com.callaway.service;

import org.springframework.util.Assert;

import javax.servlet.http.HttpSession;


public class SessionHolderImpl implements SessionHolder {

    private static final String USER = "USER";
    private static final String INITIALIZED = "INITIALIZED";
    private static ThreadLocal<HttpSession> threadLocalSession = new ThreadLocal<HttpSession>();
    private static ThreadLocal<String> threadLocalIp = new ThreadLocal<String>();

    public String getUser() {
        Object user_pk = threadLocalSession.get().getAttribute(USER);
        return String.class.isInstance(user_pk) ?
                (String) user_pk : null;
    }

    public void setSession(HttpSession session) {
        threadLocalSession.set(session);
    }

    public void setIp(String ip) {
        threadLocalIp.set(ip);
    }

    public String getIp() {
        return threadLocalIp.get();
    }

    public void setUser(String value) {
        Assert.notNull(value, "cannot set null valued User into Session");
        threadLocalSession.get().setAttribute(USER, value);
    }

    public Boolean isInitialized() {
        return (Boolean) threadLocalSession.get().getAttribute(INITIALIZED);
    }

    public void setInitialized(Boolean b) {
        threadLocalSession.get().setAttribute(INITIALIZED, b);
    }
}