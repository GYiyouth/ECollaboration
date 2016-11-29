import DAO.ECFileDAO.ECFileDAO;
import DAO.ECFileDAO.ECFileDAOImpl;
import DAO.codeDAO.CodeDAO;
import DAO.codeDAO.CodeDAOImpl;
import DAO.com.util.db.DBUtils;
import DAO.userDAO.UserDAO;
import DAO.userDAO.UserDAOImpl;
import bean.domain.CodeBean;
import bean.domain.ECFileBean;
import bean.domain.UserBean;
import smallTools.Time;
import smallTools.TimeImpl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by geyao on 2016/11/9.
 */
public class Test {
	public static  void  main(String[] args) {
//		String a = "123";
//		String b = a ;
//		System.out.println(a==b);
//		a = a + "45";
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(a==b);234234
//
//		ECFileDAO ecFileDAO = new ECFileDAOImpl();
//		ECFileBean ecFileBean = null;
//		try {
//
//发生的发生324234沃尔沃二
//			ecFileBean = ecFileDAO.getFileInfo(2);
//			ecFileBean.setFileName("photo");
//			int id = ecFileDAO.addPhoto(ecFileBean, 2);
//			System.out.println(id);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		Time time = new TimeImpl();
//		System.out.println(time.getDateStr());
//		System.out.println(time.getDeadDateStr());
//		CodeBean codeBean = new CodeBean();
//		codeBean.setDownLoadTimes(0);
//		codeBean.setPath("lala");
//		codeBean.setProjectId(123);
//		codeBean.setStudentId(123);
//		codeBean.setScore(56);
//		codeBean.setRow(123);
//		codeBean.setTeamId(555);
//
//
//		CodeDAO codeDAO = new CodeDAOImpl();
//		try {
//			codeBean = codeDAO.deleteCode(2);
//			System.out.println(codeBean);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		UserDAO  userDAO = new UserDAOImpl();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			UserBean userBean = userDAO.getUserInfoById(1);
			String sql = "SELECT photo FROM ECollaborationWeb.user WHERE id = 1";
			connection = DBUtils.getConnetction();

			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int id = userBean.getId();
			File file = new File("/User/geyao/Desktop/" + id +".jpg");
			if (file.exists()){
				file.delete();
			}
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("/Users/geyao/Desktop/" + id + ".jpg"));
			byte[] buf=new byte[1024];
			BufferedInputStream bufferedInputStream = new BufferedInputStream(resultSet.getBinaryStream(1));
			int count = -1;
			while ((count = bufferedInputStream.read(buf, 0, 1024)) != -1 ){
				bufferedOutputStream.write(buf, 0, count);
			}
			bufferedOutputStream.flush();

		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}
}
