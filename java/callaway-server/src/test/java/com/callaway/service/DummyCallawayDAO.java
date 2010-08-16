package com.callaway.service;

import com.callaway.domain.Recommendation;
import com.callaway.domain.Score;
import com.callaway.domain.User;
import com.callaway.dto.ScoreBoard;
import com.callaway.dto.ScoreBoardRow;
import com.callaway.dto.ScoreSubmission;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyCallawayDAO implements CallawayDao {

    static User u = new User("goodaccount@asfd.com");

    public String findUser(String email) {
        if ("goodaccount@asfd.com".equals(email)) {
            return u.pk;
        } else {
            return null;
        }
    }

    public String getUser(String pk) {
        return u.pk;
    }

    public String save(User u) {
        DummyCallawayDAO.u = u;
        return DummyCallawayDAO.u.pk;
    }

    public Score[] getTopScoresForUser(String user) {
        Score[] ret = new Score[]{
                new Score(1, 100l), new Score(2, 300l), new Score(3, 9100l)
        };
        return ret;
    }

    public File generateRecentSubscribersReport() {
        try {
            return File.createTempFile("testing", "csv");
        } catch (IOException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public File generateRecentRecommendationsReport() {
        try {
            return File.createTempFile("testing", "csv");
        } catch (IOException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public ScoreBoard listTopScores(int i) {
        List<Object[]> r = new ArrayList<Object[]>();
        for (Score s : u.scores) {
            Object[] o = new Object[]{u.firstName, u.lastName, s.score};
            r.add(o);
        }

        ScoreBoardRow[] rt = new ScoreBoardRow[]{
                new ScoreBoardRow(
                        "43345543DfasdffdasF",
                        "dave",
                        "jones",
                        "sdfafd@dsfadfs.com",
                        "denver",
                        "USA",
                        1337l
                ),
                new ScoreBoardRow(
                        "4549nj3345543DfasdffdasF",
                        "john",
                        "jones",
                        "sdfafd@dsfadfs.com",
                        "oakland",
                        "USA",
                        444l
                )
        };
        ScoreBoard s = new ScoreBoard();
        s.rows = Arrays.asList(rt);
        return s;
    }

    public void save(Recommendation i) {
        //do nothing
    }

    public void saveScore(String user, ScoreSubmission ss, String ip) {
        u.pk = user;
        u.scores.add(new Score(ss.level, ss.value));
    }
}