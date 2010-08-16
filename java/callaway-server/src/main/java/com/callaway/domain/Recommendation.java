package com.callaway.domain;

import com.callaway.dto.Invite;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
public class Recommendation implements Serializable {
    private static final long serialVersionUID = -8982236152601738808L;
    @Id
    public String pk = UUID.randomUUID().toString();
    @Column
    public String senderEmail;
    @Column(name = "remote_ip")
    public String remoteIp;
    @Column(nullable = false)
    public Date creationDate = new Date();
    @Column
    public String recipientEmail;
    @Column
    public String senderName;
    @Version
    public long version;

    public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString())
        .append("pk",pk)
        .append("senderEmail",senderEmail)
        .append("remoteIp", remoteIp)
        .append("creationDate",creationDate)
        .append("recipientEmail",recipientEmail)
        .append("senderName",senderName)
        .append("version",version)
        .toString();
    }
}