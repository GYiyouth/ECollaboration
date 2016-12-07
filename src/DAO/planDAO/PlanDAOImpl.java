package DAO.planDAO;

import DAO.com.util.db.DBUtils;
import bean.domain.PlanBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GR on 2016/12/5.
 */
public class PlanDAOImpl implements PlanDAO {
    /**
     * 添加计划，获取新计划id
     *
     * @param planBean
     * @return planId
     * @throws SQLException
     */
    @Override
    public Integer addPlan(PlanBean planBean) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into ecollaborationweb.plan (title,content,creatorId," +
                "createDate,modifyDate,beginDate,targetDate) values(?,?,?,?,?,?,?)";
        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setString(1, planBean.getTitle());
            ps.setString(2, planBean.getContent());
            ps.setInt(3, planBean.getCreatorId());
            ps.setString(4, planBean.getCreateDate());
            ps.setString(5, planBean.getModifyDate());
            ps.setString(6, planBean.getBeginDate());
            ps.setString(7, planBean.getTargetDate());
            int i = ps.executeUpdate();
            if (i != 0) {
                sql = "SELECT LAST_INSERT_ID()";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
    }

    /**
     * 发计划给学生自己
     *
     * @param planId,projectId,studentId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean addPlanToStudentSelf(int planId, int studentId,int projectId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into ecollaborationweb.student_team_project_plan (planId, studentId, projectId) values(?,?,?);";
        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, planId);
            ps.setInt(2, studentId);
            ps.setInt(3, projectId);
            if (ps.executeUpdate() == 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(null, ps, conn);
        }
    }

    /**
     * 组长发计划给团队
     *
     * @param planId,teamId,projectId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean addPlanToTeam(int planId, int teamId, int projectId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into ecollaborationweb.student_team_project_plan (planId, studentId, projectId) values(?,?,?);";
        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, planId);
            ps.setInt(2, teamId);
            ps.setInt(3, projectId);
            if (ps.executeUpdate() == 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(null, ps, conn);
        }
    }

    /**
     * 获取Plan对象，通过PlanId
     *
     * @param planId
     * @return
     * @throws SQLException
     */
    @Override
    public PlanBean getPlanInfoByPlanId(int planId) throws SQLException {
        PlanBean planBean = new PlanBean();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from ecollaborationweb.plan where id = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, planId);
            rs = ps.executeQuery();
            if (rs.next()) {
                planBean.setId(rs.getInt("id"));
                planBean.setTitle(rs.getString("title"));
                planBean.setContent(rs.getString("content"));
                planBean.setCreatorId(rs.getInt("creatorId"));
                planBean.setCreateDate(rs.getString("createDate"));
                planBean.setModifyDate(rs.getString("modifyDate"));
                planBean.setBeginDate(rs.getString("beginDate"));
                planBean.setTargetDate(rs.getString("targetDate"));
                return planBean;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
    }

    /**
     * 获取creatorId，通过planId
     *
     * @param planId
     * @return int
     * @throws SQLException
     */
    @Override
    public Integer getCreatorIdByPlanId(int planId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select creatorId from ecollaborationweb.plan where id = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, planId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("creatorId");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
    }

    /**
     * 通过creatorId,获取所有计划id
     *
     * @param creatorId
     * @return planId
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdByCreatorId(int creatorId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id from ecollaborationweb.plan where creatorId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, creatorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
        return list;
    }

    /**
     * 通过studentId,获取所有计划id
     *
     * @param studentId
     * @return planId
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdByStudentId(int studentId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select planId from ecollaborationweb.student_team_project_plan where studentId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("planId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
        return list;
    }

    /**
     * 通过teamId,获取所有计划id
     *
     * @param teamId
     * @return planId
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdByTeamId(int teamId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select planId from ecollaborationweb.student_team_project_plan where teamId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teamId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("planId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
        return list;
    }

    /**
     * 老师发给某个项目的所有计划
     *
     * @param projectId
     * @param teacherId
     * @return planId
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdByProjectIdTeacherId(int projectId, int teacherId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from plan,student_team_project_plan " +
                "WHERE plan.id=student_team_project_plan.planId " +
                "and plan.creatorId = ? and projectId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teacherId);
            ps.setInt(2, projectId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("planId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
        return list;
    }

    /**
     * 老师、组长发给某个团队的所有计划
     *
     * @param teamID,userId
     * @return planId
     * @throws SQLException
     */
    public ArrayList<Integer> getPlanIdByTeamIdTeacherId(int teamID, int userId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from plan,student_team_project_plan " +
                "WHERE plan.id=student_team_project_plan.planId " +
                "and plan.creatorId = ? and teamID = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, teamID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("planId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
        return list;
    }

    /**
     * 删除计划
     *
     * @param planId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean deletePlanByPlanId(int planId) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from ecollaborationweb.plan where id=?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, planId);
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
     * 删除student_team_project_plan表中的某个计划
     *
     * @param planId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean deletePlanInrelationByPlanId(int planId) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from ecollaborationweb.student_team_project_plan where planId=?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, planId);
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
     * 修改计划
     *
     * @param planBean
     * @return
     * @throws SQLException
     */
    @Override
    public boolean updatePlanByPlanBean(PlanBean planBean) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = " UPDATE ecollaborationweb.plan set title=?,content=?,creatorId=?," +
                "createDate=?,modifyDate=?,beginDate=?,targetDate=? where id = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setString(1, planBean.getTitle());
            ps.setString(2, planBean.getContent());
            ps.setInt(3, planBean.getCreatorId());
            ps.setString(4, planBean.getCreateDate());
            ps.setString(5, planBean.getModifyDate());
            ps.setString(6, planBean.getBeginDate());
            ps.setString(7, planBean.getTargetDate());
            ps.setInt(8, planBean.getId());
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
     * 获取计划id列表，通过团队id
     *
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdListByTeamId(int teamId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select planId from ecollaborationweb.student_team_project_file where teamId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teamId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("planId"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
    }


    /**
     * 获取计划id列表，通过项目id
     *
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdListByProjectId(int projectId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select planId from ecollaborationweb.student_team_project_file where projectId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, projectId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("planId"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
    }

    /**
     * 获取计划id列表，通过团队id，项目id
     *
     * @param teamId
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdListByTeamIdProjectId(int teamId, int projectId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select planId from ecollaborationweb.student_team_project_file where teamId = ? and projectId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setInt(2, projectId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("planId"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
    }

}
