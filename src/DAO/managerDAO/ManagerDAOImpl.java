package DAO.managerDAO;

import DAO.com.util.db.DBUtils;
import bean.domain.ManagerBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by GR on 2016/12/5.
 */
public class ManagerDAOImpl implements ManagerDAO {
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
        String sql = "insert into ecollaborationweb.manager (id,role) values(?,?);";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, managerBean.getId());
            ps.setInt(2, managerBean.getRole());
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
     * 修改管理员信息
     *
     * @param managerBean
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean updateInfo(ManagerBean managerBean) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = " UPDATE ecollaborationweb.manager set role = ? where id = ?;";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, managerBean.getRole());
            ps.setInt(2, managerBean.getId());
            int i = ps.executeUpdate();
            if (i == 0) {
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(null, ps, conn);
        }
        return flag;
    }

    /**
     * 删除管理员
     *
     * @param managerId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean deleteById(int managerId) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from ecollaborationweb.manager where id=?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, managerId);
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
     * 通过id获取管理员信息
     *
     * @param managerId
     * @return ManagerBean
     * @throws SQLException
     */
    @Override
    public ManagerBean getInfoById(int managerId) throws SQLException {
        ManagerBean manager = new ManagerBean();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from ecollaborationweb.manager where id = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, managerId);
            rs = ps.executeQuery();
            if (rs.next()) {
                manager.setId(rs.getInt("id"));
                manager.setRole(rs.getInt("role"));
                return manager;
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
    }
}
