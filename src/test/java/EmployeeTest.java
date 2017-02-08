import com.ticket.dao.EmployeeDAO;
import com.ticket.exception.PersistenceException;
import com.ticket.model.DepartmentModel;
import com.ticket.model.EmployeeModel;
import com.ticket.model.RoleModel;

public class EmployeeTest {
public static void main(String[] args) throws PersistenceException {
	EmployeeModel emp=new EmployeeModel();
	EmployeeDAO empDAO=new EmployeeDAO();
	DepartmentModel dep=new DepartmentModel();
	RoleModel role=new RoleModel();
	
//	emp.setDept(dep);
//	dep.setId(2);
//	emp.setEmailId("nareshkumar@gamil.com");
//	emp.setName("NareshKumar");
//	emp.setPassword("Naresh");
//	emp.setRole(role);
//	role.setId(2);
//	
//	empDAO.save(emp);
//	System.out.println(empDAO.tic);
//	emp.setPassword("naresh");
//	emp.setActive(true);
//	emp.setEmailId("nareshkumar@gmail.com");
//	empDAO.updateAsInactive(emp);
	
	
//	List<EmployeeModel> list=empDAO.listAll();
//	 for(EmployeeModel s:list)
//	 {
//	 System.out.println(s);
//	}
//	emp= empDAO.listById(1);
//	System.out.println(emp.getEmailId());
	
}
}
