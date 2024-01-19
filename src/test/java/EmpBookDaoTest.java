import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.dao.EmpBookDao;
import com.example.entity.EmpBook;

public class EmpBookDaoTest {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("/WEB-INF/springmvc-servlet.xml");
		
		EmpBookDao empBookDao = ctx.getBean("empBookDaoImpl", EmpBookDao.class);
		
		List<EmpBook> empBooks = empBookDao.findAllEmpBooks();
		System.out.println(empBooks);
		
		EmpBook empBook = empBookDao.findEmpBookByEmpId(101).get();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(empBook.getHireDate()));
		
		//Optional<EmpBook> empBooks = empBookDao.findEmpBooksByEmpDeptNoAndLevelId(1,2);
		//System.out.println(empBooks);

	}

}
