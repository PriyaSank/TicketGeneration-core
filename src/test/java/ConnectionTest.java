import com.ticket.util.ConnectionUtil;

public class ConnectionTest {
	public static void main(String[] args) {
		
		System.out.println(ConnectionUtil.getDataSource());
		System.out.println(ConnectionUtil.getJdbcTemplate());
	}
}
