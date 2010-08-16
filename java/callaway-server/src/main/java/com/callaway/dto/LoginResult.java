package com.callaway.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class LoginResult implements Serializable {

    private static final long serialVersionUID = 1L;
    public boolean success = true;
    public String reason;

    public LoginResult() {
    }

    protected boolean isSuccess() {
        return success;
    }

    protected String getReason() {
        return reason;
    }

    public static LoginResult failure() {
        LoginResult r = new LoginResult();
        r.success = false;
        r.reason = "unspecified";
        return r;
    }

    public static LoginResult success() {
        LoginResult r = new LoginResult();
        r.success = true;
        return r;
    }

      public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString())
        .append("reason",reason)
        .append("success",success)
        .toString();
    }	
}