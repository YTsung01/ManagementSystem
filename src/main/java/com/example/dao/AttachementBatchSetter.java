package com.example.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.entity.Attachement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AttachementBatchSetter implements BatchPreparedStatementSetter {

    private final List<Attachement> attachements;

    public AttachementBatchSetter(List<Attachement> attachements) {
        this.attachements = attachements;
    }

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {
        Attachement attachement = attachements.get(i);
        ps.setString(1, attachement.getForm_id());
        ps.setString(2, attachement.getFilePath());
    }

    @Override
    public int getBatchSize() {
        return attachements.size();
    }
}