import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.dao.CheckInDao;
import com.example.dao.EmpBookDao;
import com.example.entity.CheckIn;
import com.example.entity.EmpBook;


public class CheckInDaoTest {
	
	public static void main(String[] args) throws ParseException {
		
	ApplicationContext ctx = new ClassPathXmlApplicationContext("/WEB-INF/springmvc-servlet.xml");	
	CheckInDao checkInDao = ctx.getBean("checkInDaoImpl", CheckInDao.class);
	EmpBookDao empBookDao = ctx.getBean("empBookDaoImpl", EmpBookDao.class);
	
	
    // 1. 新增上班時間
    /*CheckIn checkIn = new CheckIn();
    checkIn.setEmpId(201);
    checkInDao.addCheckIn(checkIn);
    System.out.println("新增上班時間成功: " + checkIn);*/
	
	// 2. 新增下班時間 
	EmpBook empBook = empBookDao.findEmpBookByEmpId(101).get();
	Optional<CheckIn> latestCheckin = checkInDao.findLatestCheckInByEmpId(empBook.getEmpId());

    
    checkInDao.addCheckOut(empBook.getEmpId());
    System.out.print("新增下班時間成功: " + latestCheckin);
    
    //3. 依據empId查詢所有的打卡紀錄 
	
	//List<CheckIn> checkIn = checkInDao.findAllCheckInByEmpId(201);
	
	
	//4. 依據empId查詢自己當日的打卡紀錄
	/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date targetDate = dateFormat.parse("2024-01-23");
	List<CheckIn> checkIn = checkInDao.findTodayCheckInByEmpId(101,targetDate);
	System.out.print("最新一筆的上班時間: " + checkIn);*/
	
	
	//5.依據empDeptno查詢部門所有的打卡紀錄
	
	//List<CheckIn>checkIn = checkInDao.findAllCheckInByDeptNo(1);
	
	
	//6.依據empDeptno查詢部門當日的打卡紀錄
	/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date targetDate = dateFormat.parse("2024-01-21");
	List<CheckIn> checkIn = checkInDao.findTodayCheckInByDeptNo(2,targetDate);
	System.out.print(checkIn);*/
	
	//8. 統計上班遲到次數
	/*int empId = 102; 
    int lateCount = checkInDao.countLateCheckIns(empId);
    System.out.println("上班遲到次數：" + lateCount);*/
    
    //9. 查詢最近一次打卡紀錄
   /* Optional<CheckIn> checkins = checkInDao.findLatestCheckInByEmpId(102);
    System.out.print(checkins);*/
    
    
	
	
	
}
	}


