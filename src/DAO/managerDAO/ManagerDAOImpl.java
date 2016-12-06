package DAO.managerDAO;

import DAO.com.util.db.DBUtils;
import bean.domain.ManagerBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by GR on 2016/12/5.
 */
public class ManagerDAOImpl implements  ManagerDao{
    /**
     * 添加管理员，返回管理员id
     *
     * @param managerBean
     * @return int
     * @throws SQLException
     */
    @Override
    public boolean addManager(ManagerBean managerBean) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into student (id,character) values(?,?);";
        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, managerBean.getId());
            ps.setInt(2, managerBean.getCharacter());
            int i = ps.executeUpdate();
            if (i == 0) {
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, ps, conn);
        }
        return flag;
    }

    /**
     * 根据id寻找管理员，返回Manager
     *
     * @param managerId
     * @return ManagerBean
     * @throws SQLException
     */
    @Override
    public ManagerBean getManagerInfo(int managerId) throws SQLException {
        return null;
    }

    /**
     * 修改管理员信息
     *
     * @param managerBean
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean updateInfo(ManagerBean managerBean) throws SQLException {
        return false;
    }

    /**
     * 删除管理员
     *
     * @param managerId
     * @return ManagerBean
     * @throws SQLException
     */
    @Override
    public ManagerBean deleteById(int managerId) throws SQLException {
        return null;
    }

    /**
     * 通过id获取管理员信息
     *
     * @param managerId
     * @return ManagerBean
     * @throws SQLException
     */
    @Override
    public ManagerBean getInfoById(int managerId) throws SQLException {
        return null;
    }
}
