import com.ticket.dao.UserLoginDAO;
import com.ticket.exception.PersistenceException;

public class TestLogin {
public static void main(String[] args) throws PersistenceException {
	UserLoginDAO log=new UserLoginDAO();
	System.out.println(log.logIn("priyasankaran95@gmail.com", "priyas"));
}
}
