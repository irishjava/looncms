package com.callaway.dto;

import java.io.Serializable;

/**
 * Represents a single row in the ScoreBoard.
 */
public class ScoreBoardRow implements Serializable {
    public String pk;
    public String firstName;
    public String lastName;
    public String email;
    public String city;
    public String country;
    public long score;

    public ScoreBoardRow(String pk,
                         String firstName,
                         String lastName,
                         String email,
                         String city,
                         String country,
                         long score
    ) {
        this.pk = pk;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.country = country;
        this.score = score;
    }

    public ScoreBoardRow() {
        super();
    }
}
