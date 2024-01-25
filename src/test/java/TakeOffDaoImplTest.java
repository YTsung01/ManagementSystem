import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.example.dao.TakeOffDao;
import com.example.entity.EmpBook;
import com.example.entity.TakeOff;

public class TakeOffDaoImplTest {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/WEB-INF/springmvc-servlet.xml");
		
		TakeOffDao takeOffDao = ctx.getBean("takeOffDaoImpl", TakeOffDao.class);
		
		/*
		//1. 新增請假申請
		TakeOff takeOff=new TakeOff();
		takeOff.setFormId("t1fd4ec1-b681-11ee-adf1-6c3c8c3db22t");
		// 使用者輸入的日期時間字串
        String startTimeStr = "2024-01-25 17:00";
        String endTimeStr = "2024-01-25 20:30";

        // 將字串轉換成日期時間
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date startTime = dateFormat.parse(startTimeStr);
            Date endTime = dateFormat.parse(endTimeStr);

            // 設定開始時間和結束時間
            takeOff.setStartTime(startTime);
            takeOff.setEndTime(endTime);
          

            // 其他屬性的設定...
            takeOff.setAgent(102);
            takeOff.setTakeoffType(2);
            takeOff.setReason("事假");
            takeOff.setTakeoffDay(0);
            takeOff.setTakeoffHour(8);
           

            // 執行新增加班申請
            int result = takeOffDao.addTakeOff(takeOff);
          
            if (result > 0) {
                System.out.println("新增加班申請成功");
            } else {
                System.out.println("新增加班申請失敗");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("新增成功");
		*/
		
		//2. 依據empId查詢使用者
		
		
		/*
		//3. 依據empId查詢請假資料
		 List<TakeOff> takeOffs = takeOffDao.findTakeOffByEmpId(101);
		 System.out.println(takeOffs);
		 */
		
		/*
		//4. 依據empId查詢已經審核過的加班資料
		 List<TakeOff> takeOffs = takeOffDao.findCheckoutTakeOffByEmpId(101);
		 System.out.println(takeOffs);
		 */
		
		
		//5. 依據formId修改請假(注意!! 不能修改已經審核過的申請單)
		
		
		//6. 依照FormId取消請假申請
		

		/*
		//7. 依據DeptNo查詢部門的請假資料
		 List<TakeOff> takeOffs = takeOffDao.findAllTakeOffByDeptNo(1);
		 System.out.println(takeOffs);
		 */

		 
		 
		 
		 
		 
		
	
	}

}
