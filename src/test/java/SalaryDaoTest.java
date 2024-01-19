import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.dao.EmpBookDao;
import com.example.dao.SalaryDaoImpl;
import com.example.entity.EmpBook;
import com.example.entity.Salary;

public class SalaryDaoTest {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("/WEB-INF/springmvc-servlet.xml");
		
		SalaryDaoImpl salaryDao = ctx.getBean("salaryDaoImpl", SalaryDaoImpl.class);
		
		EmpBookDao empBookDao = ctx.getBean("empBookDaoImpl", EmpBookDao.class);
		
		//List<Salary> salarys = salaryDao.findAllSalarys();
		//System.out.println(salarys);
		
		//Salary salary = salaryDao.findSalaryByEmpIdAndSalaryDate(101, "2024-01").get();
		//System.out.println(salary);
		
//		EmpBook empBook = empBookDao.findEmpBookByEmpId(101).get();
//
//		Salary salary = new Salary();
//		salary.setEmpId(empBook.getEmpId());
//		salary.setBasicAmonut(empBook.getSalary());
//		salary.setTakeoffAmount(1000);
//		salary.setOvertimeAmount(2000);
//		salary.setTotalAmount(empBook.getSalary() - 1000 + 2000);
//		salary.setSalaryDate("2023-12");
//		salaryDao.addSalary(salary);
		
		Salary salary = salaryDao.findSalaryBySalaryId(3).get();
		System.out.println(salary);
	}

}
