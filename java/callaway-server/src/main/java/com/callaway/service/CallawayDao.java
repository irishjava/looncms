package com.callaway.service;

import com.callaway.domain.Recommendation;
import com.callaway.domain.Score;
import com.callaway.domain.User;
import com.callaway.dto.ScoreBoard;
import com.callaway.dto.ScoreSubmission;
import java.io.File;

public interface CallawayDao {
    String getUser(String pk);

    String findUser(String email);

    String save(User u);

    void save(Recommendation r);

    void saveScore(String user, ScoreSubmission ss, String ip);

    ScoreBoard listTopScores(int i);

    Score[] getTopScoresForUser(final String user);

    File generateRecentSubscribersReport();

    File generateRecentRecommendationsReport();
}
