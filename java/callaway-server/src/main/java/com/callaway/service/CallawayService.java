package com.callaway.service;

import com.callaway.domain.Score;
import com.callaway.dto.*;

public interface CallawayService {
    String nonce();

    InitializationResult initialize(Credentials c);

    SubscriptionResult subscribe(SubscriptionRequest s);

    ScoreBoard listTopScores();

    InviteResult invite(Invite i);

    LoginResult login(Login l);

    ScoreSubmissionResult submit(ScoreSubmission s);

    Score[] listMyScores();
}