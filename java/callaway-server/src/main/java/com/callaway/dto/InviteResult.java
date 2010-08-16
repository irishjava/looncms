package com.callaway.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class InviteResult implements Serializable {

    private static final long serialVersionUID = 1L;
    public boolean success = true;
    public String reason;

    /**
     * Initialise it as a failure
     *
     * @param string
     */
    public InviteResult(String string) {
        success = false;
        reason = string;
    }

    /**
     * Initialize as a success
     */
    public InviteResult() {

    }

    protected boolean isSuccess() {
        return success;
    }

    protected String getReason() {
        return reason;
    }

    public static InviteResult success() {
        InviteResult r = new InviteResult();
        r.success = true;
        return r;
    }

    public static InviteResult failure(String reason) {
        InviteResult r = new InviteResult();
        r.success = false;
        r.reason = reason;
        return r;
	}
  public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString())
        .append("reason",reason)
        .append("success",success)
        .toString();
    }

}