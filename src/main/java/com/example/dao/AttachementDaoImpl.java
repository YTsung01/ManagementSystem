package com.example.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.entity.Attachement;

@Repository
public class AttachementDaoImpl implements AttachementDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//1. 查詢所有附件
	@Override
	public List<Attachement> findAllAttachements() {
		String sql ="select attachId, form_id, filePath, createTime, updateTime from attachement";	
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Attachement.class));
	}
	//2. 依據form_id及附件id查詢附件(單筆)
	@Override
	public Optional<Attachement> findAttachementByFormIdAndAttachId(String form_id, Integer attachId) {
		String sql = "select attachId, form_id, filePath, createTime, updateTime from attachement where form_id = ? and attachId = ?";
		try {
			Attachement attachement = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Attachement.class), form_id,attachId);
			return Optional.of(attachement);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	//3.依據form_id查詢所有附件(多筆)
	@Override
	public List<Attachement> findAllAttachementByFormId(String form_id) {
		String sql = "select attachId, form_id, filePath, createTime, updateTime from attachement where form_id = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Attachement.class), form_id);

	}
	
	//4. 新增附件(單筆)
	@Override
	public int addAttachement(Attachement attachement) {
		String sql = "insert into attachement(form_id, filePath) values (?, ?)";
		return jdbcTemplate.update(sql, attachement.getForm_id(), attachement.getFilePath());
	}
	
	//5. 依據附件編號及路徑修改附件
	@Override
	public int updateAttachement(Integer attachId, String filePath) {
		 String sql = "update attachement set filePath = ?, updateTime = current_timestamp where attachId = ?";
		 return jdbcTemplate.update(sql, filePath, attachId);
	}
	
	//6. 依據附件編號及路徑刪除附件
	@Override
	public int deleteAttachement(Integer attachId, String filePath) {
		 String sql = "DELETE FROM attachement WHERE attachId = ? and filePath= ?";
		 return jdbcTemplate.update(sql, attachId, filePath);
	}
	
	//7.大量新增附件
	@Override
	public int[] addBatchAttachements(List<Attachement> attachements) {
		String sql = "INSERT INTO attachement(form_id, filePath) VALUES (?, ?)";
		AttachementBatchSetter batchSetter = new AttachementBatchSetter(attachements);
		return jdbcTemplate.batchUpdate(sql, batchSetter);
	}
	
	//8.大量刪除附件
    @Override
    public int[] deleteBatchAttachements(List<String> attachementIds) {
        String sql = "DELETE FROM attachement WHERE attachId = ?";
    
        List<Object[]> batchArgs = new ArrayList<>();
        for (String attachementId : attachementIds) {
            batchArgs.add(new Object[]{attachementId});
        }
        return jdbcTemplate.batchUpdate(sql, batchArgs);
    }

	
	

}
