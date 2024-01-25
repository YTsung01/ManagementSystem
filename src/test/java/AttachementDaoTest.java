import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.dao.AttachementDao;
import com.example.entity.Attachement;


public class AttachementDaoTest {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/WEB-INF/springmvc-servlet.xml");
		AttachementDao attachementDao = ctx.getBean("attachementDaoImpl", AttachementDao.class);
		
		//1. 查詢所有附件(多筆)
		
		//List<Attachement> attachements =  attachementDao.findAllAttachements();
		
		
		//2. 依據form_id及附件編號查詢附件(單筆)
		//Optional<Attachement> attachements =  attachementDao.findAttachementByFormIdAndAttachId("a1fd4ec1-b681-11ee-adf1-6c3c8c3db22a",4);
		
		
		//3.依據form_id查詢所有附件(多筆)
		//List<Attachement>attachements = attachementDao.findAllAttachementByFormId("a1fd4ec1-b681-11ee-adf1-6c3c8c3db22a");
		
		
		//4. 新增附件(單筆) -> 須設定不可重複上傳
		
		//Attachement attachement = new Attachement();
		//attachement.setForm_id("a1fd4ec1-b681-11ee-adf1-6c3c8c3db22a");
		//attachement.setFilePath("證明5.pdf");		
		//attachementDao.addAttachement(attachement);
	
		
		//5. 依據附件編號及路徑修改附件
		
		// Integer attachIdToUpdate = 7;  
	    // String newFilePath = "證明5更新.pdf";  
	    // int updateAttachement = attachementDao.updateAttachement(7, "證明5更新.pdf");
	    // System.out.print("附件編號:"+attachIdToUpdate+"更新成功");
	     
	     //6. 依據附件編號及路徑刪除附件
	     
	    // int deleteAttachement = attachementDao.deleteAttachement(5,"證明4.pdf" );
	     //System.out.print("附件編號:"+"5"+"刪除成功");
		
		//7. 大量新增附件(批量上傳)
		
        // 大量新增的數據
        // 使用 Arrays.asList 方法将数组转换为 List
        List<Attachement> attachements = Arrays.asList(
        	new Attachement("0ee91d5b-d4ad-42dd-bc90-ea32d7bda4d2", "大量測試1.pdf", new Date(), new Date()),
        	new Attachement("0ee91d5b-d4ad-42dd-bc90-ea32d7bda4d2", "大量測試2.pdf", new Date(), new Date())
        );

        //执行批量添加操作
        int[] addBatchResult = attachementDao.addBatchAttachements(attachements);
        System.out.println("大量新增結果 " + Arrays.toString(addBatchResult));
		
		//8.大量刪除附件
		// 輸入attachId進行刪除
        //List<String> attachementIdsToDelete = Arrays.asList("10", "4");
        //int[] deleteBatchResult = attachementDao.deleteBatchAttachements(attachementIdsToDelete);
        //System.out.println("刪除成功 " + Arrays.toString(deleteBatchResult));
	}

}
