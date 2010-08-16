package com.callaway.service;

import com.callaway.domain.User;

import javax.servlet.http.HttpSession;

public interface SessionHolder {

    void setUser(String value);

    void setIp(String ip);

    String getIp();

    String getUser();

    void setSession(HttpSession session);

    /*
      * Have we (as best we can) validated that it's a genuine client.
      */
    void setInitialized(Boolean b);

    Boolean isInitialized();
}