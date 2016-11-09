package DAO.userDAO;



import bean.domain.User;

import java.sql.SQLException;

public interface UserDAO {
	
	/**
	 * 添加用户，内部生成id，返回用户id
	 * @param user
	 * @return int
	 * @throws SQLException
	 */
	public int addUser(User user) throws SQLException;

	/**
	 * 根据id寻找用户，返回User
	 * @param userId
	 * @return User
	 * @throws SQLException
	 */
	public User getUserInfo(int userId) throws SQLException;

	/**
	 * 修改用户信息
	 * @param user
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(User user) throws SQLException;

	/**
	 * 删除用户
	 * @param userId
	 * @return User
	 * @throws SQLException
	 */
	public User deleteById(int userId) throws SQLException;
	
	/**
	 * 通过id获取用户信息
	 * @param userId
	 * @return User
	 * @throws SQLException
	 */
	public User getInfoById(int userId) throws SQLException;
	
}
