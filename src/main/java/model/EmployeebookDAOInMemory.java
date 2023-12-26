package model;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EmployeebookDAOInMemory implements EmployeebookDao {

	public static List<Employeebook> employeebooks;
	
	static {
		employeebooks = new CopyOnWriteArrayList<>();

		employeebooks.add(new Employeebook(001, "Solar", "Sales", new Date()));
		employeebooks.add(new Employeebook(002, "Moonbyul", "Sales", new Date()));
		employeebooks.add(new Employeebook(003, "Wheein", "Sales", new Date()));
		employeebooks.add(new Employeebook(004, "Hwasa", "Sales", new Date()));
		employeebooks.add(new Employeebook(005, "RBW", "Sales", new Date()));
	}

	@Override
	public void create(Employeebook employeebook) {
		employeebooks.add(employeebook);

	}

	@Override
	public List<Employeebook> readAll() {

		return employeebooks;
	}

}
