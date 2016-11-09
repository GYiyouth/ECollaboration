package DAO.userDAO;



import bean.domain.UserBean;

import java.sql.SQLException;

public interface UserDAO {
	
	/**
	 * 添加用户，内部生成id，返回用户id
	 * @param userBean
	 * @return int
	 * @throws SQLException
	 */
	public int addUser(UserBean userBean) throws SQLException;

	/**
	 * 根据id寻找用户，返回User
	 * @param userId
	 * @return UserBean
	 * @throws SQLException
	 */
	public UserBean getUserInfo(int userId) throws SQLException;

	/**
	 * 修改用户信息
	 * @param userBean
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(UserBean userBean) throws SQLException;

	/**
	 * 删除用户
	 * @param userId
	 * @return UserBean
	 * @throws SQLException
	 */
	public UserBean deleteById(int userId) throws SQLException;
	
	/**
	 * 通过id获取用户信息
	 * @param userId
	 * @return UserBean
	 * @throws SQLException
	 */
	public UserBean getInfoById(int userId) throws SQLException;
	
}
