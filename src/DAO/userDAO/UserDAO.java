package DAO.userDAO;



import bean.domain.UserBean;

import java.sql.SQLException;

public interface UserDAO {
	
	/**
	 * ����û����ڲ�����id�������û�id
	 * @param userBean
	 * @return int
	 * @throws SQLException
	 */
	public int addUser(UserBean userBean) throws SQLException;

	/**
	 * ����idѰ���û�������User
	 * @param userId
	 * @return UserBean
	 * @throws SQLException
	 */
	public UserBean getUserInfo(int userId) throws SQLException;

	/**
	 * �޸��û���Ϣ
	 * @param userBean
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(UserBean userBean) throws SQLException;

	/**
	 * ɾ���û�
	 * @param userId
	 * @return UserBean
	 * @throws SQLException
	 */
	public UserBean deleteById(int userId) throws SQLException;
	
	/**
	 * ͨ��id��ȡ�û���Ϣ
	 * @param userId
	 * @return UserBean
	 * @throws SQLException
	 */
	public UserBean getInfoById(int userId) throws SQLException;
	
}
