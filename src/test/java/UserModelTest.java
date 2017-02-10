import com.ticket.dao.UserDAO;
import com.ticket.dao.UserDAOImpl;
import com.ticket.model.UserModel;

public class UserModelTest {

	public static void main(String[] args) {
		UserModel user=new UserModel();
		UserDAO uDAO=new UserDAOImpl();
		user.setName("Aishu");
		user.setEmailId("aishu@gmail.com");
		//user.setActive(true);
		user.setPassword("aish");
		//uDAO.save(user);
		//uDAO.updateAsInactive(user);
		//uDAO.listById(1);
	}
}
