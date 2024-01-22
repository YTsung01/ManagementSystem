import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.dao.OverTimeDao;
import com.example.entity.Form;
import com.example.entity.OverTime;

public class OverTimeDaoImplTest {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/WEB-INF/springmvc-servlet.xml");
		
		OverTimeDao overTimeDao = ctx.getBean("overTimeDaoImpl", OverTimeDao.class);
		
		//1. 新增加班申請 -- 如何連動到FORM?
		
		OverTime overTime = new OverTime();
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
            overTime.setApplyHour(8);

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
        
        System.out.println("新增成功");
		
		//2. 依據empId查詢使用者 (??還需要存在嗎?)
		
		//3. 依據empId查詢加班資料
		//List<OverTime> overTimes = overTimeDao.findOverTimeByEmpId(102);
		 //System.out.println(overTimes);
		 
		//4.  依據empId查詢已經審核過的加班資料
		 //List<OverTime> overTimes = overTimeDao.findCheckoutOverTimeFormByEmpId(101);
		 
		 
		//5. 修改加班(注意!! 不能修改已經審核過的申請單)
		/*List<OverTime> overTimes = overTimeDao.findNonCheckoutOverTimeFormByEmpId(101);
		overTimes.get(0).setReason("測試");
		
		overTimeDao.updateOverTimeByFormId(overTimes.get(0).getFormId(),overTimes);
		
		System.out.print(overTimes.get(0));
		if (!overTimes.isEmpty()) {
		    OverTime firstNonCheckoutOverTime = overTimes.get(0);
		    firstNonCheckoutOverTime.setReason("修改的測試");

		    // 調用更新操作，不需要將整個列表傳遞給更新方法
		    int updateResult = overTimeDao.updateOverTimeByFormId(firstNonCheckoutOverTime.getFormId(), firstNonCheckoutOverTime);

		    if (updateResult > 0) {
		        System.out.println("加班申請更新成功");
		    } else {
		        System.out.println("加班申請更新失敗");
		    }
		} else {
		    System.out.println("未找到未審核的加班申請");
		}*/
		
		//6. 依照FormId取消加班申請 -> 要怎麼連form部分的一起刪掉RRRRR
		//int rowcount = overTimeDao.cancelOverTimeByFormId("a1fd4ec1-b681-11ee-adf1-6c3c8c3db22a");
		//System.out.println("刪除成功");
		
		//7.  依據DeptNo查詢部門的加班資料
		List<OverTime> overTimes = overTimeDao.findAllOverTimeByDeptNo(1);
		System.out.println(overTimes);
		
		
    }
	}


