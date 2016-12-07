package DAO.planDAO;

import bean.domain.PlanBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PlanDAO {
    /**
     * 添加计划，获取新计划id
     *
     * @param planBean
     * @return planId
     * @throws SQLException
     */
    public Integer addPlan(PlanBean planBean) throws SQLException;

    /**
     * 添加到 计划-收件人 的关系表中，
     *
     * @param planId,receiverId
     * @return boolean
     * @throws SQLException
     */
    public boolean addPlanReceiver(int planId, List<Integer> receiverIds) throws SQLException;

    /**
     * 获取Plan对象，通过PlanId
     *
     * @param planId
     * @return
     * @throws SQLException
     */
    public PlanBean getPlanInfoByPlanId(int planId) throws SQLException;

    /**
     * 获取creatorId，通过planId
     *
     * @param planId
     * @return int
     * @throws SQLException
     */
    public Integer getCreatorIdByPlanId(int planId) throws SQLException;

    /**
     * 通过creatorId,获取所有计划id
     *
     * @param planId
     * @return noticeId
     * @throws SQLException
     */
    public ArrayList<Integer> getPlanIdByCreatorId(int creatorId) throws SQLException;

    /**
     * 通过receiverId,获取所有计划id
     *
     * @param receiverId
     * @return planId
     * @throws SQLException
     */
    public ArrayList<Integer> getPlanIdByReceiverId(int receiverId) throws SQLException;

    /**
     * 获取接受人Id列表，通过planId
     *
     * @param planId
     * @return ArrayList<Integer>
     * @throws SQLException
     */
    public ArrayList<Integer> getReceiverIdByPlanId(int planId) throws SQLException;

    /**
     * 根据receiverId,planId设置实际完成时间
     *
     * @param receiverId,planId
     * @return boolean
     * @throws SQLException
     */
    public boolean updateFinishDateByReceiverIdPlanId(int receiverId, int planId, String finishDate) throws SQLException;

    /**
     * 删除计划
     *
     * @param planId
     * @return boolean
     * @throws SQLException
     */
    public boolean deletePlanByPlanId(int planId) throws SQLException;

    /**
     * 删除计划-接受者里面所有的该计划
     *
     * @param planId
     * @return boolean
     * @throws SQLException
     */
    public boolean deletePlanReceiverByPlanId(int planId) throws SQLException;

    /**
     * 修改计划
     *
     * @param planBean
     * @return
     * @throws SQLException
     */
    public boolean updatePlanByPlanBean(PlanBean planBean) throws SQLException;

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
    public ArrayList<Integer> getPlanIdListByTeamId(int teamId) throws SQLException;

    /**
     * 获取计划id列表，通过教师id
     *
     * @param teacherId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getPlanIdListByTeacherId(int teacherId) throws SQLException;

    /**
     * 获取计划id列表，通过项目id
     *
     * @param projectId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getPlanIdListByProjectId(int projectId) throws SQLException;

    /**
     * 获取计划id列表，通过团队id，项目id
     *
     * @param teamId
     * @param projectId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getPlanIdListByTeamIdProjectId(int teamId, int projectId) throws SQLException;

    /**
     * 获取计划id列表，通过团队id，教师id
     *
     * @param teamId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getPlanIdListByTeamIdTeacherId(int teamId, int teacherId) throws SQLException;

    /**
     * 获取计划Id, 通过项目id，教师id
     *
     * @param projectId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getPlanIdListByProjectIdTeacherId(int projectId, int teacherId) throws SQLException;

    /**
     * 获取计划id，通过教师id，项目id，团队id
     *
     * @param teacherId
     * @param projectId
     * @param teamId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getPlanIdListByTeacherIdProjectIdTeamId(int teacherId, int projectId, int teamId) throws SQLException;


    /**
     * 获取团队id列表，通过计划id
     *
     * @param planId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getTeamIdList(int planId) throws SQLException;

    /**
     * 获取团队id列表，通过计划id,项目id
     *
     * @param planId
     * @param projectId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getTeamIdListByProjectId(int planId, int projectId) throws SQLException;

    /**
     * 获取团队id列表，通过计划id，教师id
     *
     * @param planId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getTeamIdListByTeacherId(int planId, int teacherId) throws SQLException;

    /**
     * 获取团队id列表，通过计划id，教师id，项目id
     *
     * @param planId
     * @param teacherId
     * @param projectId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getTeamIdListByTeacherIdProjectId(int planId, int teacherId, int projectId) throws SQLException;

    /**
     * 获取教师id列表，通过计划id
     *
     * @param planId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getTeacherIdList(int planId) throws SQLException;

    /**
     * 获取教师id列表，通过计划id，团队id
     *
     * @param planId
     * @param teamId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getTeacherIdListByTeamId(int planId, int teamId) throws SQLException;

    /**
     * 获取教师id列表，通过计划id，项目id
     *
     * @param planId
     * @param projectId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getTeacherIdListByProjectId(int planId, int projectId) throws SQLException;

    /**
     * 获取教师id列表，通过计划id，团队id，项目id
     *
     * @param planId
     * @param teamId
     * @param projectId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getTeacherIdListByTeamIdProjectId(int planId, int teamId, int projectId) throws SQLException;

    /**
     * 获取项目id列表，通过计划id
     *
     * @param planId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getProjectIdList(int planId) throws SQLException;

    /**
     * 获取项目id列表，通过计划id，项目id
     *
     * @param planId
     * @param teamId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getProjectIdListByTeamId(int planId, int teamId) throws SQLException;

    /**
     * 获取项目id列表，通过计划id，教师id
     *
     * @param planId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getProjectIdListByTeacherId(int planId, int teacherId) throws SQLException;

    /**
     * 获取项目id列表，通过计划id，团队id，教师id
     *
     * @param planId
     * @param teamId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getProjectIdListByTeamIdTeacherId(int planId, int teamId, int teacherId) throws SQLException;

    /**
     * 获取项目id列表，通过计划id,团队id
     *
     * @param planId
     * @param teamId
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getProjectIdListByTeamIdPlanId(int planId, int teamId) throws SQLException;
}


