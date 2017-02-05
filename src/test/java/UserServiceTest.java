import org.springframework.dao.DataAccessException;

import com.ticket.dao.TicketGenerationDAO;
import com.ticket.dao.UserModule;
import com.ticket.exception.PersistenceException;
import com.ticket.exception.ServiceException;
import com.ticket.model.DepartmentModel;
import com.ticket.model.PriorityModel;
import com.ticket.model.TicketDetailsModel;
import com.ticket.model.UserModel;
import com.ticket.service.UserService;

public class UserServiceTest {
	public static void main(String[] args) throws ServiceException {
		
	
TicketGenerationDAO ticGen=new TicketGenerationDAO();
TicketDetailsModel tic=new TicketDetailsModel();
DepartmentModel dept=new DepartmentModel();
PriorityModel pri=new PriorityModel();
UserModel user=new UserModel();
UserModule mod=new UserModule();
UserService useServ=new UserService();
//user.setEmailId("haritha@gmail.com");
//user.setName("Haritha");
//user.setPassword("harithak");
//System.out.println(useServ.register(user));
//System.out.println(useServ.logIn("priyasankaran95@gmail.com", "priyas"));
//mod.updateTicket("priyasankaran95@gmail.com", "priyas", 1,"Pending");
//user.setId(1);
//dept.setId(2);
//tic.setDept(dept);
//dept.setId(1);
//tic.setDept(dept);
//tic.setDescription("What will be the condition to apply for intership?");
//pri.setId(2);
//
//tic.setPrior(pri);
//tic.setSubject("Applying Intership");
//System.out.println(useServ.ticketGeneration(tic, "haritha@gmail.com", "harithak"));
System.out.println(useServ.updateTicketStatus("priyasankaran95@gmail.com", "priyas",1,"Open"));
//tic.setDescription("Whenever I am committing my project in github, the code smells are not updating");
//tic.setUser(user);
//tic.setSubject("Doubt in SonarCube Installation");
//ticGen.ticketGenerate(tic);
}
}
