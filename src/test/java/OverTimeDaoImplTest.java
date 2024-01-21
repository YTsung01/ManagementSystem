import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.dao.OverTimeDao;
import com.example.entity.OverTime;

public class OverTimeDaoImplTest {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/WEB-INF/springmvc-servlet.xml");
		
		OverTimeDao overTimeDao = ctx.getBean("overTimeDaoImpl", OverTimeDao.class);
		
		//1. 新增加班申請 -- 如何連動到FORM?
		/*OverTime overTime = new OverTime();
		overTime.setFormId("d1fd4ec1-b681-11ee-adf1-6c3c8c3db22d");
		   // 使用者輸入的日期時間字串
        String startTimeStr = "2024-01-25 17:00";
        String endTimeStr = "2024-01-25 20:30";

        // 將字串轉換成日期時間
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date startTime = dateFormat.parse(startTimeStr);
            Date endTime = dateFormat.parse(endTimeStr);

            // 設定開始時間和結束時間
            overTime.setStartTime(startTime);
            overTime.setEndTime(endTime);

            // 其他屬性的設定...
            overTime.setDayOrHoilday(1);
            overTime.setOvertimeType(2);
            overTime.setReason("加班原因");

            // 執行新增加班申請
            int result = overTimeDao.addOverTime(overTime);

            if (result > 0) {
                System.out.println("新增加班申請成功");
            } else {
                System.out.println("新增加班申請失敗");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("新增成功");*/
		
		//2. 
		
	
	}

}
