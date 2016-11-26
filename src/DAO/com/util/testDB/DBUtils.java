package DAO.com.util.testDB;


import java.sql.*;
import java.util.ResourceBundle;

/**
 * @author  geyao on 2016/10/29.
 */
public class DBUtils {
		//数据库连接地址
     public static String URL;
     //用户名
     public static String USERNAME;
	 //密码
     public static String PASSWORD;
	 //mysql的驱动类
	 public static String DRIVER;
	 private static ResourceBundle rb = ResourceBundle.getBundle("DAO.com.util.db.db-config");
	 private DBUtils(){}

	//使用静态块加载驱动程序
     static{
		    URL = rb.getString("jdbc.url");
            USERNAME = rb.getString("jdbc.username");
            PASSWORD = rb.getString("jdbc.password");
            DRIVER = rb.getString("jdbc.driver");
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
				System.out.println("DBUtils执行错误");
			}
	 }

	  //定义数据库连接方式
	public static Connection getConnetction(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}catch (SQLException e){
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
		return connection;
	}

	/**
	* 关闭数据库连接
	* @param resultSet
	* @param statement
	* @param connection
	*/
	public static void close(ResultSet resultSet, PreparedStatement statement, Connection connection){
		try {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			System.out.println("关闭连接失败");
		}
	}

//	public static void main(String[] args){
//		UserDAO userDAO = new UserDAOImpl();
//		try {
//
//			UserBean user = userDAO.findByAccount("1", "2");
//			System.out.println(user.getId());
//			System.out.println(userDAO.check("1", "1"));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
