package com.callaway.service;

import com.callaway.domain.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class DummySessionHolder implements SessionHolder {

    private static final String USER = "USER";
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>() {
        private Map<String, Object> session = new HashMap<String, Object>();

        public void set(Map<String, Object> value) {
            session = value;
        }
        public Map<String, Object> get() {
            return session;
        }
    };

    public String getUser() {
        Object o = threadLocal.get().get(USER);
        if (String.class.isInstance(o)) {
            return (String) o;
        }
        throw new UnsupportedOperationException("user not stored");
    }

    public void setSession(HttpSession session) {
        // Do nothing, we aren't testing it against a real HttpSession store.
    }

    public void setUser(String value) {
        threadLocal.get().put(USER, value);
    }

    public void setIp(String ip) {
        threadLocal.get().put("IP", ip);
    }

    public String getIp() {
        return (String) threadLocal.get().get("IP");
    }

    public Boolean isInitialized() {
        return (Boolean) threadLocal.get().get("INITIALIZED");
    }

    public void setInitialized(Boolean b) {
        threadLocal.get().put("INITIALIZED", b);
    }
}