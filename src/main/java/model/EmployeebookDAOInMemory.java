package model;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EmployeebookDAOInMemory implements EmployeebookDao {
	public static List<Employeebook> employeebooks;
	static {
		employeebooks=new CopyOnWriteArrayList<Employeebook>();
		employeebooks.add(new Employeebook(001,"Solar","female","Sales","Manager",new Date(),90000.0,0.0));
		employeebooks.add(new Employeebook(002,"Moonbyul","male","Sales","Boss",new Date(),100000.0,0.0));
		employeebooks.add(new Employeebook(003,"Wheein","male","Sales","Engineer",new Date(),80000.0,0.0));
		employeebooks.add(new Employeebook(004,"Hwasa","female","Sales","Engineer",new Date(),80000.0,0.0));
		employeebooks.add(new Employeebook(005,"RBW","male","Sales","Cleaning",new Date(),40000.0,0.0));
		
			}
	
	

	@Override
	public void create(Employeebook employeebook) {
		// TODO Auto-generated method stub
		
	}

	@Overrides
	public List<Employeebook> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
