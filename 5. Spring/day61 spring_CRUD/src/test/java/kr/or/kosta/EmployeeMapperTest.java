package kr.or.kosta;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.kosta.spring.employee.mapper.EmployeeMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//Java 설정을 이용하는 경우
//@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class EmployeeMapperTest{
   
   @Setter(onMethod_ = {@Autowired})
   private DataSource dataSource;
   @Setter(onMethod_ = {@Autowired})
   private SqlSessionFactory sqlSessionFactory;
   
   @Inject
   private EmployeeMapper employeeMapper;
	@Test
	public void testConnection() {
		try (Connection con = dataSource.getConnection()) {
			log.info("연결 : "+ con);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

   @Test
   public void testMybatis() throws SQLException{
	   SqlSession session = sqlSessionFactory.openSession();
	   log.info("Mybatis DB 연결 : " + session.getConnection());
   }
   @Test
   public void testMappler() throws SQLException{
	   log.info(employeeMapper);
	   String today = employeeMapper.getTime();
	   log.info("수업 끝 : " + today);
   }
}