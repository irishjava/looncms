package com.callaway.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class SubscriptionResult implements Serializable {
    private static final long serialVersionUID = 1L;

    public boolean success;
    public String reason;

    public static SubscriptionResult success() {
        SubscriptionResult r = new SubscriptionResult();
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