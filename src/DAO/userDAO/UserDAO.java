package DAO.userDAO;



import bean.domain.UserBean;

import java.sql.SQLException;

public interface UserDAO {



	/**
	 * �û���¼���ɹ�����User��ʧ�ܷ��ؿ�
	 *
	 * @param logName,passWord
	 * @return User
	 * @throws SQLException
	 */
	public UserBean getLogerInfo(String logName,String passWord) throws SQLException;

	/**
	 * ����û����ڲ�����id�������û�id
	 *
	 * @param user
	 * @return int
	 * @throws SQLException
	 */
	public int addUser(UserBean user) throws SQLException;

	/**
	 * ͨ��id��ȡ�û���Ϣ
	 *
	 * @param userId
	 * @return User
	 * @throws SQLException
	 */
	public UserBean getUserInfoById(int userId) throws SQLException;

	/**
	 * �޸��û���Ϣ
	 *
	 * @param user
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(UserBean user) throws SQLException;

	/**
	 * ɾ���û�
	 *
	 * @param userId
	 * @return User
	 * @throws SQLException
	 */
	public UserBean deleteById(int userId) throws SQLException;


}

