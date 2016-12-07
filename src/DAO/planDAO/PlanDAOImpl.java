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
     * 添加到 计划-收件人 的关系表中，
     *
     * @param planId
     * @param receiverIds
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean addPlanReceiver(int planId, List<Integer> receiverIds) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into ecollaborationweb.plan_receiver (planId, receiverId, finishDate) values(?,?,?);";
        try {
            conn = DBUtils.getConnetction();
            for (int i = 0; i < receiverIds.size(); i++) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, planId);
                ps.setInt(2, receiverIds.get(i));
                ps.setInt(3, 0);
                if (ps.executeUpdate() == 0) {
                    return false;
                }
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
     * 通过receiverId,获取所有计划id
     *
     * @param receiverId
     * @return planId
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdByReceiverId(int receiverId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select noticeId from ecollaborationweb.plan_receiver where receiverId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, receiverId);
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
     * 获取接受人Id列表，通过planId
     *
     * @param planId
     * @return ArrayList<Integer>
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getReceiverIdByPlanId(int planId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select receiverId from ecollaborationweb.plan_receiver where planId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, planId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("receiverId"));
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
     * 根据receiverId,planId设置已读计划
     *
     * @param receiverId
     * @param planId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean updateFinishDateByReceiverIdPlanId(int receiverId, int planId, String finishDate) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update ecollaborationweb.notice_receiver set finishDate=? where receiverId = ? and planId=?;";
        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setString(1, finishDate);
            ps.setInt(2, receiverId);
            ps.setInt(3, planId);
            if (ps.executeUpdate() == 0)
                return false;
            else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(null, ps, conn);
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
     * 删除计划-接受者里面所有的该计划
     *
     * @param planId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean deletePlanReceiverByPlanId(int planId) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from ecollaborationweb.plan_receiver where planId=?";

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

//    暂未实现下面的方法！！！！！！！！！！！！！！！！！！！！！！！
//    暂未实现下面的方法！！！！！！！！！！！！！！！！！！！！！！！
//    暂未实现下面的方法！！！！！！！！！！！！！！！！！！！！！！！
//    暂未实现下面的方法！！！！！！！！！！！！！！！！！！！！！！！

    /**
     * 获取计划id列表，通过团队id
     *
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdListByTeamId(int teamId) throws SQLException {
        return null;
    }

    /**
     * 获取计划id列表，通过教师id
     *
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdListByTeacherId(int teacherId) throws SQLException {
        return null;
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
        return null;
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
        return null;
    }

    /**
     * 获取计划id列表，通过团队id，教师id
     *
     * @param teamId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdListByTeamIdTeacherId(int teamId, int teacherId) throws SQLException {
        return null;
    }

    /**
     * 获取计划Id, 通过项目id，教师id
     *
     * @param projectId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdListByProjectIdTeacherId(int projectId, int teacherId) throws SQLException {
        return null;
    }

    /**
     * 获取计划id，通过教师id，项目id，团队id
     *
     * @param teacherId
     * @param projectId
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdListByTeacherIdProjectIdTeamId(int teacherId, int projectId, int teamId) throws SQLException {
        return null;
    }

    /**
     * 获取团队id列表，通过计划id
     *
     * @param planId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeamIdList(int planId) throws SQLException {
        return null;
    }

    /**
     * 获取团队id列表，通过计划id,项目id
     *
     * @param planId
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeamIdListByProjectId(int planId, int projectId) throws SQLException {
        return null;
    }

    /**
     * 获取团队id列表，通过计划id，教师id
     *
     * @param planId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeamIdListByTeacherId(int planId, int teacherId) throws SQLException {
        return null;
    }

    /**
     * 获取团队id列表，通过计划id，教师id，项目id
     *
     * @param planId
     * @param teacherId
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeamIdListByTeacherIdProjectId(int planId, int teacherId, int projectId) throws SQLException {
        return null;
    }

    /**
     * 获取教师id列表，通过计划id
     *
     * @param planId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeacherIdList(int planId) throws SQLException {
        return null;
    }

    /**
     * 获取教师id列表，通过计划id，团队id
     *
     * @param planId
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeacherIdListByTeamId(int planId, int teamId) throws SQLException {
        return null;
    }

    /**
     * 获取教师id列表，通过计划id，项目id
     *
     * @param planId
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeacherIdListByProjectId(int planId, int projectId) throws SQLException {
        return null;
    }

    /**
     * 获取教师id列表，通过计划id，团队id，项目id
     *
     * @param planId
     * @param teamId
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeacherIdListByTeamIdProjectId(int planId, int teamId, int projectId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过计划id
     *
     * @param planId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdList(int planId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过计划id，项目id
     *
     * @param planId
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTeamId(int planId, int teamId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过计划id，教师id
     *
     * @param planId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTeacherId(int planId, int teacherId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过计划id，团队id，教师id
     *
     * @param planId
     * @param teamId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTeamIdTeacherId(int planId, int teamId, int teacherId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过计划id,团队id
     *
     * @param planId
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTeamIdPlanId(int planId, int teamId) throws SQLException {
        return null;
    }
}
