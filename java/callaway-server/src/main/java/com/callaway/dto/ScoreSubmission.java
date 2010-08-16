package com.callaway.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class ScoreSubmission implements Serializable {
    
    private static final long serialVersionUID = 1L;
    public int level;
    public Long value;

    public ScoreSubmission(int level, Long value){
        this.level = level;
        this.value = value;
    }

    public ScoreSubmission(){
        super();
    }

    public String toString() {
    	return new ToStringBuilder(this).appendSuper(super.toString())
        .append("level",level)
        .append("value"  ,value)
        .toString();
    }
}