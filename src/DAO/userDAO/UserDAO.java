package DAO.userDAO;



import bean.domain.User;

import java.sql.SQLException;

public interface UserDAO {
	
	/**
	 * ����û����ڲ�����id�������û�id
	 * @param user
	 * @return int
	 * @throws SQLException
	 */
	public int addUser(User user) throws SQLException;

	/**
	 * ����idѰ���û�������User
	 * @param userId
	 * @return User
	 * @throws SQLException
	 */
	public User getUserInfo(int userId) throws SQLException;

	/**
	 * �޸��û���Ϣ
	 * @param user
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(User user) throws SQLException;

	/**
	 * ɾ���û�
	 * @param userId
	 * @return User
	 * @throws SQLException
	 */
	public User deleteById(int userId) throws SQLException;
	
	/**
	 * ͨ��id��ȡ�û���Ϣ
	 * @param userId
	 * @return User
	 * @throws SQLException
	 */
	public User getInfoById(int userId) throws SQLException;
	
}
