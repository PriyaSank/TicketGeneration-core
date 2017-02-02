import java.util.List;

import com.ticket.dao.DepartmentDAO;
import com.ticket.dao.RoleDAO;
import com.ticket.model.DepartmentModel;
import com.ticket.model.RoleModel;

public class TestDep {
	public static void main(String[] args) {
		
	
RoleModel dep=new RoleModel();
RoleDAO depDAO=new RoleDAO();
//dep.setName("HR");
//dep.setId(1);
//dep.setActive(true);
//depDAO.save(dep);
//depDAO.update(dep);

List<RoleModel> list=depDAO.listAll();
 for(RoleModel s:list)
 {
 System.out.println(s);
}
// DepartmentModel dept = depDAO.listById(1);
//System.out.println(dept.getId()+"\t"+dept.getName()+"\t"+dept.getActive());
//
//dep.setName("Admin");
//depDAO.save(dep);



	}
}
