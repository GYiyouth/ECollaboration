package DAO.managerDAO;



import bean.domain.ManagerBean;

import java.sql.SQLException;

public interface ManagerDAO {

	/**
	 * ��ӹ���Ա�����ع���Աid
	 * @param managerBean
	 * @return int
	 * @throws SQLException
	 */
	public int addManager(ManagerBean managerBean) throws SQLException;

	/**
	 * ����idѰ�ҹ���Ա������Manager
	 * @param managerId
	 * @return ManagerBean
	 * @throws SQLException
	 */
	public ManagerBean getManagerInfo(int managerId) throws SQLException;

	/**
	 * �޸Ĺ���Ա��Ϣ
	 * @param managerBean
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(ManagerBean managerBean) throws SQLException;

	/**
	 * ɾ������Ա
	 * @param managerId
	 * @return ManagerBean
	 * @throws SQLException
	 */
	public ManagerBean deleteById(int managerId) throws SQLException;
	
	/**
	 * ͨ��id��ȡ����Ա��Ϣ
	 * @param managerId
	 * @return ManagerBean
	 * @throws SQLException
	 */
	public ManagerBean getInfoById(int managerId) throws SQLException;
	
}
