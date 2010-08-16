package com.callaway.domain;

import com.callaway.dto.SubscriptionRequest;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class User implements Serializable {

    public User(SubscriptionRequest s) {
        email = s.email;
        firstName = s.firstName;
        lastName = s.lastName;
        country = s.country;
        city = s.city;
        subscriber = s.optIn;
        //if (s.dob != null && !(s.dob.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}"))) {
        //    throw new UnsupportedOperationException("cannot parse date string");
        //}
        //if (s.dob != null && s.dob.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
        DateTimeFormatter forPattern = DateTimeFormat
                .forPattern("DD/MM/YYYY");
        dob = forPattern.parseDateTime(s.dob).toDate();
        //}
    }

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "user_pk")
    public String pk = UUID.randomUUID().toString();

    @Column
    public String firstName;

    @Column
    public String lastName;

    @Column(unique = true)
    public String email;

    @Column
    public String country;

    @Column
    public String city;

    @Column
    public boolean subscriber;

    @Column
    public Date dob;

    @Column(name = "remote_ip")
    public String remoteIp;

    @Version
    public long version;

    @Column
    public Date creationDate = new Date();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_score",
            joinColumns = {
                    @JoinColumn(name = "user_fk", referencedColumnName = "user_pk")},
            inverseJoinColumns = {
                    @JoinColumn(name = "score_fk", referencedColumnName = "score_pk")}
    )
    public List<Score> scores = new ArrayList<Score>();

    public void copyDetailsFrom(User u) {
        u.firstName = this.firstName;
        u.lastName = this.lastName;
        u.email = this.email;
        u.country = this.country;
        u.city = this.city;
        u.subscriber = this.subscriber;
        u.dob = this.dob;
        u.scores = this.scores;
    }

    public User(String email) {
        this.email = email;
    }

    public User() {
    }

//	public synchronized void addNewScore(Score s) {
//		s.user = this;
//		scores.add(s);
//		Collections.sort(scores, new Comparator<Score>() {
//			public int compare(Score o1, Score o2) {
//				if (o1.level == o1.level) {
//					return o1.score > o2.score ? -1 : 1;
//				}
//				return 0;
//			}
//		});
//	}
}
