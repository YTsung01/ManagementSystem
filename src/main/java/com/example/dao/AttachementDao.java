package com.example.dao;

import java.util.List;
import java.util.Optional;

import com.example.entity.Attachement;

public interface AttachementDao {
	
	//1. 查詢所有附件(多筆)
	
	List<Attachement> findAllAttachements();
	
	//2. 依據form_id及附件編號查詢附件(單筆)
	
	Optional<Attachement> findAttachementByFormIdAndAttachId(String form_id, Integer attachId);
	
	//3.依據form_id查詢所有附件(多筆)
	List<Attachement> findAllAttachementByFormId(String form_id);
	
	//4. 新增附件(單個)
	
	int addAttachement(Attachement attachement);
	
	//5. 依據附件編號修改附件
	
	int updateAttachement(Integer attachId,String filePath);
	
	//6. 依據附件編號刪除附件
	
	int deleteAttachement(Integer attachId,String filePath);
	
	//7. 大量新增附件(批量上傳)
	
	int[] addBatchAttachements(List<Attachement> attachements);
	
	//8. 大量刪除附件(批量刪除)
	int[] deleteBatchAttachements(List<String> attachementIds);
	
	

}
