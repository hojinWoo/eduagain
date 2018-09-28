package pattern;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Dao 패턴 적용을 위한 인터페이스 선언
 * @author hojin
 *
 */
public interface UserDao {
	
	public void create(User user) throws Exception;
	
	public User read(String id) throws Exception;
	
	public void update(User user) throws Exception;
	
	public void delete(String id) throws Exception;
	
	public List<User> listAll() throws Exception;
	
	public User certify(String id, String passwd) throws Exception; //로그인을 위한 method
	
	public List<Map<String, String>> employeeList() throws Exception; //사원의 정보를 List로 가져올 때 보여주고자 하는 각각의 column data. (사원번호, 사원이름, 급여, 부서이름, 도시명)
	
	public Connection getConnection() throws Exception;
	
	
}
