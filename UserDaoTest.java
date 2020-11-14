package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.UserDao;
import dao.UserDaoImpl;
import pojo.User;

public class UserDaoTest {

	public static void main(String[] args) throws IOException {
		UserDao userDao;
		SqlSession sqlSession;
		String resource = "mybatis-config.xml";
		// 读取配置文件
		InputStream is = Resources.getResourceAsStream(resource);
		// 构建SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		// 获取sqlSession
		sqlSession = sqlSessionFactory.openSession();
		userDao = new UserDaoImpl(sqlSession);
		
		System.out.println(userDao.queryUserById("100"));
		
		List<User> userList = userDao.queryUserAll();
		for (User user : userList) {
			System.out.println(user);
		}
		
		
		User user = new User();
		user.setId("105");
		user.setAge(16);
		user.setBirthday(new Date("1990/09/02"));
		user.setName("大鹏");
		user.setPassword("123456");
		user.setSex(1);
		user.setUserName("evan");
		userDao.insertUser(user);
		sqlSession.commit();
		
		
		
		User user1 = new User();
		user1.setBirthday(new Date());
		user1.setName("静鹏");
		user1.setPassword("654321");
		user1.setSex(1);
		user1.setUserName("evanjin");
		user1.setId("100");
		userDao.updateUser(user1);
		sqlSession.commit();
		
		
		userDao.deleteUser("104");
		
		
		
		sqlSession.commit();
		
	}


}