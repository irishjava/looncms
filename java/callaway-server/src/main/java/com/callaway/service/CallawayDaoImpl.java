package com.callaway.service;

import au.com.bytecode.opencsv.CSVWriter;
import com.callaway.domain.Recommendation;
import com.callaway.domain.Score;
import com.callaway.domain.User;
import com.callaway.dto.ScoreBoard;
import com.callaway.dto.ScoreBoardRow;
import com.callaway.dto.ScoreSubmission;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.io.*;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CallawayDaoImpl extends JpaDaoSupport implements CallawayDao {

    private static String
            RECOMMENDATIONS =
            "SELECT * FROM Recommendation";

    private static String
            SUBSCRIPTIONS =
            "SELECT * FROM User u";

    private static String TOP_SCORES = "SELECT \n" +
            "SUM(max_by_level) as total, \n" +
            "user_fk, \n" +
            "firstName,\n" +
            "lastName,\n" +
            "email,\n" +
            "city,\n" +
            "country\n" +
            "FROM (\n" +
            "SELECT \n" +
            "MAX(S.score) as max_by_level, \n" +
            "u.user_fk as user_fk,\n" +
            "UU.firstName as firstName, \n" +
            "UU.lastName as lastName,\n" +
            "UU.email as email,\n" +
            "UU.city as city,\n" +
            "UU.country as country,\n" +
            "S.level as level \n" +
            "FROM Score S, user_score u, `User` UU\n" +
            "WHERE u.score_fk=S.score_pk AND u.user_fk=UU.user_pk\n" +
            "group by u.user_fk, S.level order by u.user_fk, S.level) AS SEMI group by user_fk order by total desc limit 0,10";

    private static String USER_TOP_SCORES =
            "SELECT MAX(S.score),u.user_fk,S.level FROM Score S, user_score u, `User` UU\n" +
                    "WHERE u.score_fk=S.score_pk AND u.user_fk=UU.user_pk and user_fk = ? \n" +
                    "group by u.user_fk, S.level order by u.user_fk, S.level desc ";
    private DriverManagerDataSource dataSource;

    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DriverManagerDataSource getDataSource() {
        return dataSource;
    }

    public File generateRecentSubscribersReport() {
        try {
            File f = File.createTempFile("subscribers-" + RandomStringUtils.randomAlphanumeric(25), ".csv");
            Writer fw = new BufferedWriter(new FileWriter(f));
            CSVWriter csvw = new CSVWriter(fw);
            CallableStatement callableStatement = getDataSource().getConnection().prepareCall(SUBSCRIPTIONS);
            callableStatement.execute();
            csvw.writeAll(callableStatement.getResultSet(), true);
            IOUtils.closeQuietly(fw);
            return f;
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e);
        }
        catch (IOException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public File generateRecentRecommendationsReport() {
        try {
            File f = File.createTempFile("subscribers-" + RandomStringUtils.randomAlphanumeric(25), ".csv");
            Writer fw = new BufferedWriter(new FileWriter(f));
            CSVWriter csvw = new CSVWriter(fw);
            CallableStatement callableStatement = getDataSource().getConnection().prepareCall(RECOMMENDATIONS);
            callableStatement.execute();
            csvw.writeAll(callableStatement.getResultSet(), true);
            IOUtils.closeQuietly(fw);
            return f;
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e);
        }
        catch (IOException e) {
            throw new UnsupportedOperationException(e);
        }
    }


    private static final class ScoreBoardMapper implements RowMapper {
        public ScoreBoardRow mapRow(ResultSet rs, int rowNum) throws SQLException {
            ScoreBoardRow r = new ScoreBoardRow();
            r.pk = rs.getString("user_fk");
            r.firstName = rs.getString("firstName");
            r.lastName = rs.getString("lastName");
            r.email = rs.getString("email");
            r.city = rs.getString("city");
            r.country = rs.getString("country");
            r.score = rs.getLong("total");
            return r;
        }
    }


    private static final class ScoreMapper implements RowMapper {
        public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
            Score s = new Score();
            s.pk = rs.getString("pk");
            s.creationDate = rs.getDate("creation_date");
            s.level = rs.getInt("level");
            s.score = rs.getLong("score");
            return s;
        }
    }

    @SuppressWarnings("unchecked")
    public ScoreBoard listTopScores(int i) {
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.rows = new JdbcTemplate(getDataSource()).query(TOP_SCORES, new ScoreBoardMapper());
        return scoreBoard;
    }

    @SuppressWarnings("unchecked")
    public Score[] getTopScoresForUser(final String user) {
        List<Score> list = new JdbcTemplate(getDataSource()).query(USER_TOP_SCORES, new Object[]{user}, new ScoreMapper());
        return list.toArray(new Score[]{});
    }


    public String findUser(final String email) {
        return (String) getJpaTemplate().execute(new JpaCallback() {
            @SuppressWarnings("unchecked")
            public String doInJpa(final EntityManager em)
                    throws PersistenceException {
                Query createQuery = em
                        .createQuery("SELECT u from User u where u.email = :email");
                createQuery.setParameter("email", email);
                List<User> resultList = createQuery.getResultList();
                if (resultList.size() > 0) {
                    final User find = resultList.get(0);
                    em.clear();
                    return find.pk;
                }
                return null;
            }
        });
    }


    public String getUser(final String pk) {
        return (String) getJpaTemplate().execute(new JpaCallback() {
            public String doInJpa(final EntityManager em)
                    throws PersistenceException {
                final Object find = em.find(User.class, pk);
                if (find != null) {
                    em.clear();
                    return ((User) find).pk;
                }
                return null;
            }
        });
    }


    public String save(final User u) {
        return (String) getJpaTemplate().execute(new JpaCallback() {
            @SuppressWarnings("unchecked")
            public String doInJpa(final EntityManager em)
                    throws PersistenceException {
                // Email must never be null
                Query q = em.createQuery("SELECT u FROM User u WHERE u.email = :email");
                q.setParameter("email", u.email);
                List res = q.getResultList();
                Object o = (res.size() > 0) ? res.get(0) : null;
                if (o != null) {
                    ((User) o).copyDetailsFrom(u);
                    em.persist(o);
                    em.flush();
                } else {
                    em.persist(u);
                    em.flush();
                }
                em.clear();
                return u.pk;
            }
        });
    }

    public void save(final Recommendation r) {
        getJpaTemplate().execute(new JpaCallback() {
            public Object doInJpa(final EntityManager em)
                    throws PersistenceException {
                em.persist(r);
                return null;
            }
        });
    }

    public void saveScore(final String user, final ScoreSubmission ss, final String ip) {
        getJpaTemplate().execute(new JpaCallback() {
            public Object doInJpa(final EntityManager em)
                    throws PersistenceException {
                User u = em.find(User.class, user);
                if (u != null) {
                    Score s = new Score();
                    s.remoteIp = ip;
                    s.level = ss.level;
                    s.score = ss.value;
                    u.scores.add(s);
                    em.flush();
                    em.clear();
                }
                return null;
            }
        });
    }
}