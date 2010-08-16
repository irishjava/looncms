package com.callaway.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class ScoreSubmissionResult implements Serializable {

    private static final long serialVersionUID = 1L;
    public boolean success = true;
    public String reason;

    /**
     * Initialise it as a failure
     *
     * @param string
     */
    public ScoreSubmissionResult(String string) {
        success = false;
        reason = string;
    }

    /**
     * Initialize as a success
     */
    public ScoreSubmissionResult() {

    }

    protected boolean isSuccess() {
        return success;
    }

    protected String getReason() {
        return reason;
    }

    public static ScoreSubmissionResult success() {
        ScoreSubmissionResult s = new ScoreSubmissionResult();
        s.success = true;
        return s;
	}

    public static ScoreSubmissionResult failure(String reason) {
        ScoreSubmissionResult s = new ScoreSubmissionResult();
        s.success = false;
        s.reason = reason;
        return s;
    }
    public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString())
        .append("reason",reason)
        .append("success",success)
        .toString();
    }


}