package DAO.planDAO;

import DAO.com.util.db.DBUtils;
import bean.domain.PlanBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                "createDate,finishDate,beginDate,targetDate) values(?,?,?,?,?,?,?)";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, planBean.getTitle());
            ps.setString(2, planBean.getContent());
            ps.setInt(3, planBean.getCreatorId());
            ps.setString(4, planBean.getCreateDate());
            ps.setString(5, planBean.getFinishDate());
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
     * 添加自己的计划
     *
     * @param planId,projectId,studentId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean addPlanToStudent(int planId, int studentId,int projectId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into ecollaborationweb.student_team_project_plan (planId, studentId, projectId) values(?,?,?);";
        try {
            conn = DBUtils.getConnection();
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
     * 添加计划给某个项目(发布人应该是老师)
     *
     * @param planId
     * @param projectId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean addPlanToProject(int planId, int projectId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into ecollaborationweb.student_team_project_plan (planId, projectId) values(?,?);";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, planId);
            ps.setInt(2, projectId);
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
     * 添加计划给某个团队(组长)
     *
     * @param planId
     * @param teamId
     * @return boolean
     * @throws SQLException
     */

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
        String sql = "insert into ecollaborationweb.student_team_project_plan (planId, teamId, projectId) values(?,?,?);";
        try {
            conn = DBUtils.getConnection();
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
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, planId);
            rs = ps.executeQuery();
            if (rs.next()) {
                planBean.setId(rs.getInt("id"));
                planBean.setTitle(rs.getString("title"));
                planBean.setContent(rs.getString("content"));
                planBean.setCreatorId(rs.getInt("creatorId"));
                planBean.setCreateDate(rs.getString("createDate"));
                planBean.setFinishDate(rs.getString("finishDate"));
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
            conn = DBUtils.getConnection();
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
            conn = DBUtils.getConnection();
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
            conn = DBUtils.getConnection();
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
     * 通过studentId,projectId 获取所有计划id
     *
     * @param studentId
     * @param projectId
     * @return planId
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdByStudentIdProjectId(int studentId, int projectId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select planId from ecollaborationweb.student_team_project_plan where studentId = ? and projectId = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
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

    /**
     * 通过projectId,获取所有计划id
     *
     * @param projectId
     * @return planId
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdByProjectId(int projectId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select planId from ecollaborationweb.student_team_project_plan where projectId = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, projectId);
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
     * 通过projectId,teacherId获取所有计划id
     * 老师发给某个项目的所有planid
     * @param projectId
     * @param teacherId
     * @return planId
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdByProjectIdTeacherId(int projectId, int teacherId) throws SQLException {
//        SELECT planId from plan,student_team_project_plan where plan.id=student_team_project_plan.planId and student_team_project_plan.projectId = 1 and plan.creatorId=1
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT planId from plan,student_team_project_plan where plan.id=student_team_project_plan.planId " +
                "and student_team_project_plan.projectId = ? and plan.creatorId = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, projectId);
            ps.setInt(2, teacherId);
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
            conn = DBUtils.getConnection();
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
     * 组长发给某个团队的所有planid
     *
     * @param teamId
     * @param studentId
     * @return planId
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdByTeamIdStudentId(int teamId, int studentId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT planId from plan,student_team_project_plan where plan.id=student_team_project_plan.planId " +
                "and student_team_project_plan.teamId = ? and plan.creatorId = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setInt(2, studentId);
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
        String sql = "select p.id from ecollaborationweb.student_team_project_plan as stpp,ecollaborationweb.plan as p " +
                "where stpp.planId = p.id and stpp.teamId = ? and stpp.projectId = ? ORDER BY p.targetDate";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setInt(2, projectId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("id"));
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
            conn = DBUtils.getConnection();
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
            conn = DBUtils.getConnection();
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
     * 删除发给某个项目所有计划
     *
     * @param projectId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean deletePlanToProjectByPlanId(int projectId) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from ecollaborationweb.student_team_project_plan where projectId=?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, projectId);
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
     * 删除发给某个团队所有计划
     *
     * @param teamId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean deletePlanToTeamByPlanId(int teamId) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from ecollaborationweb.student_team_project_plan where teamId=?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teamId);
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
     * 删除发给某个学生所有计划
     *
     * @param studentId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean deletePlanToStudentByPlanId(int studentId) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from ecollaborationweb.student_team_project_plan where studentId=?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
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
                "createDate=?,finishDate=?,beginDate=?,targetDate=? where id = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, planBean.getTitle());
            ps.setString(2, planBean.getContent());
            ps.setInt(3, planBean.getCreatorId());
            ps.setString(4, planBean.getCreateDate());
            ps.setString(5, planBean.getFinishDate());
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
     * 修改计划发给某个项目的学生
     *
     * @param planId
     * @param studentId
     * @param projectId @return boolean
     * @throws SQLException
     */
    @Override
    public boolean updatePlanToStudent(int planId, int studentId, int projectId) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = " UPDATE ecollaborationweb.student_team_project_plan set studentId = ?," +
                "projectId = ? where planId = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, projectId);
            ps.setInt(3, planId);
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
     * 修改计划发给某个团队
     *
     * @param planId
     * @param teamId
     * @param projectId @return boolean
     * @throws SQLException
     */
    @Override
    public boolean updatePlanToTeam(int planId, int teamId, int projectId) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = " UPDATE ecollaborationweb.student_team_project_plan set teamId = ?," +
                "projectId = ? where planId = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setInt(2, projectId);
            ps.setInt(3, planId);
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
     * 修改计划发给某个项目
     *
     * @param planId
     * @param projectId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean updatePlanToProject(int planId, int projectId) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = " UPDATE ecollaborationweb.student_team_project_plan SET projectId = ? where planId = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, projectId);
            ps.setInt(2, planId);
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
     * 获取某个时间段之间的，某个人在某个项目所有完成的计划
     *
     * @param beginTime
     * @param endTime
     * @param studentId
     * @param projectId @return
     */
    @Override
    public ArrayList<Integer> getPlanIdBetweenATimeBTime(String beginTime, String endTime, int studentId, int projectId) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from plan,student_team_project_plan " +
                "WHERE plan.finishDate BETWEEN ? AND ? " +
                "AND plan.id = student_team_project_plan.planId " +
                "AND student_team_project_plan.studentId = ? " +
                "AND student_team_project_plan.projectId = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,beginTime);
            ps.setString(2,endTime);
            ps.setInt(3, studentId);
            ps.setInt(4, projectId);
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
