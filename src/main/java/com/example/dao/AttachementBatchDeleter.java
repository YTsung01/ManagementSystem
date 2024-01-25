package com.example.dao;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AttachementBatchDeleter implements BatchPreparedStatementSetter {

    private final List<String> attachementIds;

    public AttachementBatchDeleter(List<String> attachementIds) {
        this.attachementIds = attachementIds;
    }

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {
        String attachementId = attachementIds.get(i);
        ps.setString(1, attachementId);
    }

    @Override
    public int getBatchSize() {
        return attachementIds.size();
    }
}