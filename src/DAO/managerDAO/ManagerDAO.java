package DAO.managerDAO;

import bean.domain.ManagerBean;

import java.sql.SQLException;

/**
 * Created by GR on 2016/12/5.
 */
public interface ManagerDao {
    /**
     * 添加管理员，返回管理员id
     * @param managerBean
     * @return int
     * @throws SQLException
     */
    public boolean addManager(ManagerBean managerBean) throws SQLException;

    /**
     * 根据id寻找管理员，返回Manager
     * @param managerId
     * @return ManagerBean
     * @throws SQLException
     */
    public ManagerBean getManagerInfo(int managerId) throws SQLException;

    /**
     * 修改管理员信息
     * @param managerBean
     * @return boolean
     * @throws SQLException
     */
    public boolean updateInfo(ManagerBean managerBean) throws SQLException;

    /**
     * 删除管理员
     * @param managerId
     * @return ManagerBean
     * @throws SQLException
     */
    public ManagerBean deleteById(int managerId) throws SQLException;

    /**
     * 通过id获取管理员信息
     * @param managerId
     * @return ManagerBean
     * @throws SQLException
     */
    public ManagerBean getInfoById(int managerId) throws SQLException;
}
