package DAO.managerDAO;



import bean.domain.Manager;

import java.sql.SQLException;

public interface ManagerDAO {

	/**
	 * 添加管理员，返回管理员id
	 * @param manager
	 * @return int
	 * @throws SQLException
	 */
	public int addManager(Manager manager) throws SQLException;

	/**
	 * 根据id寻找管理员，返回Manager
	 * @param managerId
	 * @return Manager
	 * @throws SQLException
	 */
	public Manager getManagerInfo(int managerId) throws SQLException;

	/**
	 * 修改管理员信息
	 * @param manager
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(Manager manager) throws SQLException;

	/**
	 * 删除管理员
	 * @param managerId
	 * @return Manager
	 * @throws SQLException
	 */
	public Manager deleteById(int managerId) throws SQLException;
	
	/**
	 * 通过id获取管理员信息
	 * @param managerId
	 * @return Manager
	 * @throws SQLException
	 */
	public Manager getInfoById(int managerId) throws SQLException;
	
}
