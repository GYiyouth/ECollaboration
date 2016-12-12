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
     * 添加计划给某个学生
     *
     * @param planId,projectId,studentId
     * @return boolean
     * @throws SQLException
     */
    public boolean addPlanToStudent(int planId, int studentId, int projectId) throws SQLException;

    /**
     * 添加计划给某个项目(发布人应该是老师)
     *
     * @param planId,projectId
     * @return boolean
     * @throws SQLException
     */
    public boolean addPlanToProject(int planId, int projectId) throws SQLException;

    /**
     * 发计划给团队(组长)
     *
     * @param planId,teamId,projectId
     * @return boolean
     * @throws SQLException
     */
    public boolean addPlanToTeam(int planId, int teamId, int projectId) throws SQLException;

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
     * @param creatorId
     * @return planId
     * @throws SQLException
     */
    public ArrayList<Integer> getPlanIdByCreatorId(int creatorId) throws SQLException;

    /**
     * 通过studentId,获取所有计划id
     *
     * @param studentId
     * @return planId
     * @throws SQLException
     */
    public ArrayList<Integer> getPlanIdByStudentId(int studentId) throws SQLException;

    /**
     * 通过studentId,projectId 获取所有计划id
     *
     * @param studentId,projectId
     * @return planId
     * @throws SQLException
     */
    public ArrayList<Integer> getPlanIdByStudentIdProjectId(int studentId, int projectId) throws SQLException;

    /**
     * 通过projectId,获取所有计划id
     *
     * @param projectId
     * @return planId
     * @throws SQLException
     */
    public ArrayList<Integer> getPlanIdByProjectId(int projectId) throws SQLException;

    /**
     * 通过projectId,teacherId获取所有计划id
     *  老师发给某个项目的所有planid
     * @param projectId,teacherId
     * @return planId
     * @throws SQLException
     */
    public ArrayList<Integer> getPlanIdByProjectIdTeacherId(int projectId, int teacherId) throws SQLException;

    /**
     * 通过teamId,获取所有计划id
     *
     * @param teamId
     * @return planId
     * @throws SQLException
     */
    public ArrayList<Integer> getPlanIdByTeamId(int teamId) throws SQLException;

    /**
     *  组长发给某个团队的所有planid
     * @param studentId,teamId
     * @return planId
     * @throws SQLException
     */
    public ArrayList<Integer> getPlanIdByTeamIdStudentId(int teamId, int studentId) throws SQLException;

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
     * 删除计划
     *
     * @param planId
     * @return boolean
     * @throws SQLException
     */
    public boolean deletePlanByPlanId(int planId) throws SQLException;

    /**
     * 删除student_team_project_plan表中的某个计划
     *
     * @param planId
     * @return boolean
     * @throws SQLException
     */
    public boolean deletePlanInrelationByPlanId(int planId) throws SQLException;

    /**
     * 删除发给某个项目所有计划
     *
     * @param projectId
     * @return boolean
     * @throws SQLException
     */
    public boolean deletePlanToProjectByPlanId(int projectId) throws SQLException;

    /**
     * 删除发给某个团队所有计划
     *
     * @param teamId
     * @return boolean
     * @throws SQLException
     */
    public boolean deletePlanToTeamByPlanId(int teamId) throws SQLException;

    /**
     * 删除发给某个学生所有计划
     *
     * @param studentId
     * @return boolean
     * @throws SQLException
     */
    public boolean deletePlanToStudentByPlanId(int studentId) throws SQLException;

    /**
     * 修改计划
     *
     * @param planBean
     * @return
     * @throws SQLException
     */
    public boolean updatePlanByPlanBean(PlanBean planBean) throws SQLException;

    /**
     * 修改计划发给某个项目的学生
     *
     * @param planId,projectId,studentId
     * @return boolean
     * @throws SQLException
     */
    public boolean updatePlanToStudent(int planId, int studentId, int projectId) throws SQLException;

    /**
     * 修改计划发给某个团队
     *
     * @param planId,projectId,teamId
     * @return boolean
     * @throws SQLException
     */
    public boolean updatePlanToTeam(int planId, int teamId, int projectId) throws SQLException;

    /**
     * 修改计划发给某个项目
     *
     * @param planId,projectId
     * @return boolean
     * @throws SQLException
     */
    public boolean updatePlanToProject(int planId, int projectId) throws SQLException;

}


