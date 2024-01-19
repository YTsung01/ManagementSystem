import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.dao.EmpBookDao;
import com.example.dao.FormDao;
import com.example.entity.EmpBook;
import com.example.entity.Form;
import com.example.model.entity.Employee;

public class FormDaoTest {
	
	public static void main(String[] args ) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("/WEB-INF/springmvc-servlet.xml");
		
		FormDao formDao = ctx.getBean("formDaoImpl", FormDao.class);
		EmpBookDao empBookDao = ctx.getBean("empBookDaoImpl", EmpBookDao.class);
		
		//2. Optional<Form> form = formDao.findFormByFormId("a1fd4ec1-b681-11ee-adf1-6c3c8c3db22a");
		//3. List<Form> form= formDao.findAllFormsByEmpId(101);
		EmpBook empBook = empBookDao.findEmpBookByEmpId(101).get();
		Integer applier = empBook.getEmpId();
		//4.Optional<Form>form = formDao.findFormByEmpIdAndType(applier,1);
		//5. List<Form> forms = formDao.findAllForms();
		
		//System.out.println(form);
		
		/*1.新增
		Form form = new Form();
		form.setApplier(empBook.getEmpId());
		form.setFormId("vjfiojihugfjkajtuioewogfskoa");
		form.setType(2);
		form.setApplyDate(new Date());
		formDao.addForm(form);
		System.out.println(form);*/
		
		
		
	
	}

}
