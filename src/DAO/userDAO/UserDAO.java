package DAO.userDAO;



import bean.domain.UserBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public interface UserDAO {



	/**
	 * 获取UserBean，通过登录名，密码
	 *
	 * @param logName,passWord
	 * @return User
	 * @throws SQLException
	 */
	public UserBean getLogInfo(String logName, String passWord) throws SQLException;

	/**
	 * 添加用户，内部生成id，返回用户id
	 *
	 * @param user
	 * @return int
	 * @throws SQLException
	 */
	public Integer addUser(UserBean user) throws SQLException;

	/**
	 * 通过id获取用户信息
	 *
	 * @param userId
	 * @return User
	 * @throws SQLException
	 */
	public UserBean getUserInfoById(int userId) throws SQLException;

	/**
	 * 修改用户信息
	 *
	 * @param user
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(UserBean user) throws SQLException;

	/**
	 * 删除用户
	 *
	 * @param userId
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean deleteById(int userId) throws SQLException;

	/**
	 * 设置用户头像，根据userBean，file
	 * @param userBean
	 * @param file
	 * @return
	 * @throws SQLException
	 */
	public boolean setUserPhoto(UserBean userBean, File file) throws SQLException, FileNotFoundException;

	/**
	 * 获取File，头像，通过UserBean
	 * @param userBean
	 * @return
	 * @throws SQLException
	 */
	public File getUserPhoto(UserBean userBean) throws SQLException,FileNotFoundException;

}

