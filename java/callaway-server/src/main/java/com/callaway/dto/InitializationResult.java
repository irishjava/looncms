package com.callaway.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Immutable class
 *
 * @author bryan
 */
public class InitializationResult implements Serializable {

    private static final long serialVersionUID = 1L;
    public boolean success = true;
    public String reason;

    /**
     * Initialise it as a failure
     *
     * @param string
     */
    public InitializationResult(String string) {
        success = false;
        reason = string;
    }

    /**
     * Initialize as a success
     */
    public InitializationResult() {

    }

    protected boolean isSuccess() {
        return success;
    }

    protected String getReason() {
        return reason;
	}

  public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString())
        .append("reason",reason)
        .append("success",success)
        .toString();
    }
}
