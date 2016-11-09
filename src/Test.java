import DAO.ECFileDAO.ECFileDAO;
import DAO.ECFileDAO.ECFileDAOImpl;
import bean.domain.ECFile;
import smallTools.Time;
import smallTools.TimeImpl;

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
//		System.out.println(a==b);

		ECFileDAO ecFileDAO = new ECFileDAOImpl();
		ECFile ecFile = null;
		try {


			ecFile = ecFileDAO.getFileInfo(2);
			ecFile.setFileName("photo");
			int id = ecFileDAO.addPhoto(ecFile, 2);
			System.out.println(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
