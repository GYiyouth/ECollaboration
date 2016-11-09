package DAO.managerDAO;



import bean.domain.Manager;

import java.sql.SQLException;

public interface ManagerDAO {

	/**
	 * ��ӹ���Ա�����ع���Աid
	 * @param manager
	 * @return int
	 * @throws SQLException
	 */
	public int addManager(Manager manager) throws SQLException;

	/**
	 * ����idѰ�ҹ���Ա������Manager
	 * @param managerId
	 * @return Manager
	 * @throws SQLException
	 */
	public Manager getManagerInfo(int managerId) throws SQLException;

	/**
	 * �޸Ĺ���Ա��Ϣ
	 * @param manager
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(Manager manager) throws SQLException;

	/**
	 * ɾ������Ա
	 * @param managerId
	 * @return Manager
	 * @throws SQLException
	 */
	public Manager deleteById(int managerId) throws SQLException;
	
	/**
	 * ͨ��id��ȡ����Ա��Ϣ
	 * @param managerId
	 * @return Manager
	 * @throws SQLException
	 */
	public Manager getInfoById(int managerId) throws SQLException;
	
}
