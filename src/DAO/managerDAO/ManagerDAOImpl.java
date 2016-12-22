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
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlU = "INSERT INTO ECollaborationWeb.user ( " +
                " schoolId, name, sex, role, email, phoneNumber, " +
                " logName, passWord, createDate, " +
                " lastLogTime, activeBefore, newsFlag) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
        String sqlT = "insert into ECollaborationWeb.manager (id,mRole) " +
                "values(?,?)";
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sqlU, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,managerBean.getSchoolId());
            ps.setString(2,managerBean.getName());
            ps.setInt(3,managerBean.getSex());
            ps.setInt(4,managerBean.getRole());
            ps.setString(5,managerBean.getEmail());
            ps.setString(6,managerBean.getPhoneNumber());
            ps.setString(7,managerBean.getLogName());
            ps.setString(8,managerBean.getPassWord());
            ps.setString(9,managerBean.getCreateDate());
            ps.setString(10,managerBean.getLastLogTime());
            ps.setString(11,managerBean.getActiveBefore());
            ps.setInt(12,managerBean.getNewFlag());

            int z  = ps.executeUpdate();
            if(z!=0){
                ResultSet ids = ps.getGeneratedKeys();
                if(ids.next())
                    System.out.println("插入user表成功");
                managerBean.setId(ids.getInt(1));
            }else{
                System.out.println("插入teacher表失败");
                return false;
            }
            ps = conn.prepareStatement(sqlT);
            ps.setInt(1, managerBean.getId());
            ps.setInt(2, managerBean.getmRole());
            int i = ps.executeUpdate();
            if (i == 0) {
                System.out.println("插入manager表失败");
                return false;
            }else{
                System.out.println("插入manager表成功");
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("出错！");
            conn.rollback();
            return false;
        } finally {
            DBUtils.close(null, ps, conn);
        }

        return true;
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

        String sql = " UPDATE ecollaborationweb.manager set mRole = ? where id = ?;";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, managerBean.getmRole());
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
                manager.setmRole(rs.getInt("mRole"));
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
