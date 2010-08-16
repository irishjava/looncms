package com.callaway.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class Invite implements Serializable {
    private static final long serialVersionUID = 1L;
    public String senderEmail = "";
    public String senderName = "";
    public String[] recipients;
    boolean optIn;
    public String message;

    public String toString() {
	return new ToStringBuilder(this).appendSuper(super.toString())
	.append("senderEmail"  ,senderEmail)
	.append("senderName",senderName)
	.append("recipients",recipients)
    .append("optIn",optIn)
    .append("message",message)
	.toString();
    }
}