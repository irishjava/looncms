package com.callaway.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class SubscriptionRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    public String email;
    public String firstName;
    public String lastName;
    public String country;
    public String city;
    public String dob;
    public boolean optIn;

    public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString())
        .append("email",email)
        .append("firstName",firstName)
        .append("lastName", lastName)
        .append("country",country)
        .append("city"  ,city)
        .append("dob",dob)
        .append("optIn",optIn)
        .toString();
    }
} 
