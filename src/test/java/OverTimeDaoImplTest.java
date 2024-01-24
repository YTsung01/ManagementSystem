import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.FormDao;
import com.example.dao.OverTimeDao;
import com.example.entity.Form;
import com.example.entity.OverTime;

public class OverTimeDaoImplTest {

	@Transactional(propagation = Propagation.REQUIRED)
	public static void testAddFormAndOverTime(FormDao formDao, OverTimeDao overTimeDao) throws ParseException {
		
		// 取得UUID
		String uuid = UUID.randomUUID().toString();

		// 建立表單
		Form form = new Form();
		form.setApplier(101);
		form.setFormId(uuid);
		form.setType(2);
		form.setApplyDate(new Date());

		formDao.addForm(form);
		System.out.println(form);

		// 建立加班
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		OverTime overTime = new OverTime();
		overTime.setFormId(uuid);
		overTime.setStartTime(sdf.parse("2024-01-25 17:00"));
		overTime.setEndTime(sdf.parse("2024-01-25 20:30"));
		overTime.setApplyHour(4);
		overTime.setReason("羽球");
		overTime.setDayOrHoilday(1);
		//overTime.setOvertimeType(1);
		overTimeDao.addOverTime(overTime);
		System.out.println(overTime);
	}

	public static void main(String[] args) throws ParseException {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("/WEB-INF/springmvc-servlet.xml");

		FormDao formDao = ctx.getBean("formDaoImpl", FormDao.class);
		OverTimeDao overTimeDao = ctx.getBean("overTimeDaoImpl", OverTimeDao.class);

		testAddFormAndOverTime(formDao,overTimeDao);
		
		// 1. 新增加班申請 -- 如何連動到FORM?

		// 將字串轉換成日期時間
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        try {
//            Date startTime = dateFormat.parse(startTimeStr);
//            Date endTime = dateFormat.parse(endTimeStr);
//
//            // 設定開始時間和結束時間
//            overTime.setStartTime(startTime);
//            overTime.setEndTime(endTime);
//
//            // 其他屬性的設定...
//            overTime.setDayOrHoilday(1);
//            overTime.setOvertimeType(2);
//            overTime.setReason("加班原因");
//            overTime.setApplyHour(8);
//
//            // 執行新增加班申請
//            int result = overTimeDao.addOverTime(overTime);
//
//            if (result > 0) {
//                System.out.println("新增加班申請成功");
//            } else {
//                System.out.println("新增加班申請失敗");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        System.out.println("新增成功");

		// 2. 依據empId查詢使用者 (??還需要存在嗎?)

		// 3. 依據empId查詢加班資料
		// List<OverTime> overTimes = overTimeDao.findOverTimeByEmpId(102);
		// System.out.println(overTimes);

		// 4. 依據empId查詢已經審核過的加班資料
		// List<OverTime> overTimes = overTimeDao.findCheckoutOverTimeFormByEmpId(101);

		// 5. 修改加班(注意!! 不能修改已經審核過的申請單)
//		String formId = "a1fd4ec1-b681-11ee-adf1-6c3c8c3db22a";
//		List<OverTime> overTimes = overTimeDao.findNonCheckoutOverTimeFormByEmpId(101);
//		OverTime overTime = overTimes.get(0);
//		System.out.println(overTime);
//		overTime.setReason("測試");
//		overTimeDao.updateOverTimeByFormId(formId, overTime);
//		System.out.println(overTime);

//		System.out.print(overTimes.get(0));
//		if (!overTimes.isEmpty()) {
//		    OverTime firstNonCheckoutOverTime = overTimes.get(0);
//		    firstNonCheckoutOverTime.setReason("修改的測試");
//
//		    // 調用更新操作，不需要將整個列表傳遞給更新方法
//		    int updateResult = overTimeDao.updateOverTimeByFormId(firstNonCheckoutOverTime.getFormId(), firstNonCheckoutOverTime);
//
//		    if (updateResult > 0) {
//		        System.out.println("加班申請更新成功");
//		    } else {
//		        System.out.println("加班申請更新失敗");
//		    }
//		} else {
//		    System.out.println("未找到未審核的加班申請");
//		}

		// 6. 依照FormId取消加班申請 -> 要怎麼連form部分的一起刪掉RRRRR
		// int rowcount =
		// overTimeDao.cancelOverTimeByFormId("a1fd4ec1-b681-11ee-adf1-6c3c8c3db22a");
		// System.out.println("刪除成功");

		// 7. 依據DeptNo查詢部門的加班資料
		// List<OverTime> overTimes = overTimeDao.findAllOverTimeByDeptNo(1);
		// System.out.println(overTimes);

	}
}
