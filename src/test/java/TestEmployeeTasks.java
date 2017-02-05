import java.util.List;

import com.ticket.dao.TicketDetailsDAO;
import com.ticket.exception.ServiceException;
import com.ticket.model.DepartmentModel;
import com.ticket.model.EmployeeModel;
import com.ticket.model.IssueModel;
import com.ticket.model.RoleModel;
import com.ticket.model.TicketDetailsModel;
import com.ticket.service.EmployeeService;

public class TestEmployeeTasks {

public static void main(String[] args) throws ServiceException {
	EmployeeService ser=new EmployeeService();
	EmployeeModel emp=new EmployeeModel();
	DepartmentModel dep=new DepartmentModel();
	TicketDetailsDAO ticDAO=new TicketDetailsDAO();
	RoleModel role=new RoleModel();
	IssueModel iss=new IssueModel();
	TicketDetailsModel tic=new TicketDetailsModel();
//	tic.setId(1);
//	iss.setTic(tic);
	//iss.setSolution("Go to eclipse marketplace and download");
	//ser.replyToTicket("janani@gmail.com", "jana", iss);
	//ser.reassignTicket("nareshkumar@gmail.com","naresh", 5, 1);
	//System.out.println(ticDAO.getDeptId(2));
	System.out.println(ser.assignTicket("nithya@gmail.com", "niths", 4, 2));
	//System.out.println(ser.logIn("janani@gmail.com", "jana"));
//	List<TicketDetailsModel> list=ser.viewAssignedTicket("janani@gmail.com", "jana");
//	 for(TicketDetailsModel s:list)
//	 {
//	 System.out.println(s);
//	}
	//System.out.println(ser.viewAssignedTicket("janani@gmail.com", "jana"));
	//ser.assignTicket("janani@gmail.com", "jana", 1);
//	role.setId(2);
//	emp.setEmailId("priyan@gmail.com");
//	emp.setPassword("priyan");
//	dep.setId(2);
//	emp.setDept(dep);
//	emp.setName("Priyan");
//	emp.setRole(role);
//	ser.registerEmployee(emp);
//	System.out.println(ser.registerEmployee(emp));
}
}
