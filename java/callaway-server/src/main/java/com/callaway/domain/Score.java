package com.callaway.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
public class Score implements Serializable {
    private static final long serialVersionUID = 5085059194571767223L;
    @Column(nullable = false)
    public Date creationDate = new Date();
    @Column(nullable = false)
    public Integer level;
    @Id
    @Column(name = "score_pk")
    public String pk = UUID.randomUUID().toString();
    @Column(nullable = false)
    public Long score;
    @Column(name = "remote_ip")
    public String remoteIp;
    @Version
    public long version;
    @ManyToOne(optional = true)
    @JoinTable(name = "user_score",
            joinColumns = {
                    @JoinColumn(name = "score_fk", referencedColumnName = "score_pk")},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_fk", referencedColumnName = "user_pk")}
    )
    public User user;
    
    public Score(int level, long value) {
        this.level = level;
        this.score = value;
    }

    public Score() {
        super();
    }
}