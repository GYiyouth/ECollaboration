package DAO.userDAO;



import bean.domain.UserBean;

import java.sql.SQLException;

public interface UserDAO {



	/**
	 * 用户登录，成功返回User，失败返回空
	 *
	 * @param logName,passWord
	 * @return User
	 * @throws SQLException
	 */
	public UserBean getLogerInfo(String logName,String passWord) throws SQLException;

	/**
	 * 添加用户，内部生成id，返回用户id
	 *
	 * @param user
	 * @return int
	 * @throws SQLException
	 */
	public int addUser(UserBean user) throws SQLException;

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
	 * @return User
	 * @throws SQLException
	 */
	public UserBean deleteById(int userId) throws SQLException;


}

