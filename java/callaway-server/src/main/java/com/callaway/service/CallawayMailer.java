package com.callaway.service;

import com.callaway.dto.Invite;

public interface CallawayMailer {
    void invite(Invite i);
}