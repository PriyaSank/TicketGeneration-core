import com.ticket.exception.ServiceException;
import com.ticket.model.UserModel;
import com.ticket.service.UserService;

public class TestUserService {
	public static void main(String[] args) throws ServiceException {
		
	
UserService serv=new UserService();
UserModel user=new UserModel();
user.setEmailId("bharat@gmail.com");
user.setPassword("Bhary");
user.setName("Bharat");
//serv.save(user);
}
}
