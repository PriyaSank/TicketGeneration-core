import com.ticket.dao.UserLoginDAO;

public class TestLogin {
public static void main(String[] args) {
	UserLoginDAO log=new UserLoginDAO();
	System.out.println(log.logIn("priyasankaran95@gmail.com", "priyas"));
}
}
