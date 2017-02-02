import com.ticket.dao.TicketGenerationDAO;
import com.ticket.model.DepartmentModel;
import com.ticket.model.TicketDetailsModel;
import com.ticket.model.UserModel;

public class TestUserReg {
	public static void main(String[] args) {
		
	
TicketGenerationDAO ticGen=new TicketGenerationDAO();
TicketDetailsModel tic=new TicketDetailsModel();
DepartmentModel dept=new DepartmentModel();
UserModel user=new UserModel();
user.setId(1);
dept.setId(2);
tic.setDept(dept);
tic.setDescription("Whenever I am committing my project in github, the code smells are not updating");
tic.setUser(user);
tic.setSubject("Doubt in SonarCube Installation");
ticGen.ticketGenerate(tic);
}
}
