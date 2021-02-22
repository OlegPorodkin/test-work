package ru.porodkin.postgresql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.porodkin.Entry;
import ru.porodkin.postgresql.connection.PostgreConnection;
import ru.porodkin.usecase.port.EntryDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PostgreFieldDao implements EntryDao {

    public static final Logger LOG = LoggerFactory.getLogger(PostgreFieldDao.class);

    private final PostgreConnection postgreCoon;

    private static final String INSERT_SQL = "insert into test(field) values (?)";
    private static final String SELECT_SQL = "select * from test";
    private static final String TRUNCATE_SQL = "truncate table test";

    public PostgreFieldDao(String url, String login, String pass) {
        this.postgreCoon = new PostgreConnection(url, login, pass);
    }


    @Override
    public int saveEntries(Collection<Entry> entries) {
        try (Connection conn = postgreCoon.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            conn.setAutoCommit(false);

            LOG.info("Starting insert to DB {} field", entries.size());

            clearTable();

            for (Entry entry : entries) {
                ps.setInt(1, entry.getField());
                ps.addBatch();
            }

            int[] result = ps.executeBatch();
            conn.commit();

            LOG.info("Insert FIELDS into DB successful");
            return result.length;

        } catch (SQLException ex) {
            LOG.error("Something wrong whit Database when inserting", ex);
        }
        return 0;
    }

    @Override
    public Collection<Entry> getAllEntries() {
        Collection<Entry> entries = new ArrayList<>();

        try (Connection conn = postgreCoon.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_SQL)) {

            ResultSet rs = ps.executeQuery();

            if (LOG.isDebugEnabled()) LOG.debug("Get from DB {} entries",rs.getFetchSize());
            while (rs.next()){
                Entry entry = new Entry();
                int anInt = rs.getInt(1);
                entry.setField(anInt);
                if (LOG.isTraceEnabled()) LOG.trace("Entry from DB {}",entry);
                entries.add(entry);
            }

        } catch (SQLException ex) {
            LOG.error("When select all Entry happened exception:", ex);
        }
        return entries;
    }

    private void clearTable() {
        try (Connection conn = postgreCoon.getConnection();
             PreparedStatement stat = conn.prepareStatement(TRUNCATE_SQL)) {
            conn.setAutoCommit(false);

            if (LOG.isDebugEnabled()) LOG.debug("Prepare to truncate table...");
            stat.execute();
            if (LOG.isDebugEnabled()) LOG.debug("Truncate table complete...");

            conn.commit();
            LOG.info("Table truncate complete.");
        } catch (SQLException ex) {
            LOG.error("When truncate table happened exception:", ex);
        }
    }
}
