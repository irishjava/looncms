package com.callaway.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    public String email;

    public Login() {
        super();
    }

    public Login(String email) {
        this.email = email;
    }

    protected String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
    	return new ToStringBuilder(this).appendSuper(super.toString())
    	.append("email",email)
	    .toString();
    }
}
