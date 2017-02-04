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
	RoleModel role=new RoleModel();
	IssueModel iss=new IssueModel();
	TicketDetailsModel tic=new TicketDetailsModel();
//	tic.setId(1);
//	iss.setTic(tic);
	//iss.setSolution("Go to eclipse marketplace and download");
	//ser.replyToTicket("janani@gmail.com", "jana", iss);
	ser.reassignTicket("janani@gmail.com", "jana", 1, 1);
	//System.out.println(ser.viewAssignedTicket("janani@gmail.com", "jana"));
	//ser.assignTicket("janani@gmail.com", "jana", 1);
//	role.setId(2);
//	emp.setEmailId("janani@gmail.com");
//	emp.setPassword("jana");
//	dep.setId(2);
//	emp.setDept(dep);
//	emp.setName("Janani");
//	emp.setRole(role);
//	ser.registerEmployee(emp);
}
}
