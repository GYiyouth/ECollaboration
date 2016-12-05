package DAO.planDAO;
import bean.domain.PlanBean;

import java.sql.SQLException;
import java.util.ArrayList;

    public interface PlanDAO {
        /**
         * 添加计划，获取新计划id
         * @param planBean
         * @return
         * @throws SQLException
         */
        public Integer addPlan(PlanBean planBean) throws SQLException;

        /**
         * 删除计划，获取此计划Plan对象
         * @param planId
         * @return
         * @throws SQLException
         */
        public PlanBean deletePlan(int planId) throws SQLException;

        /**
         * 修改计划，获取bool变量
         * @param planBean
         * @return
         * @throws SQLException
         */
        public boolean updatePlan(PlanBean planBean) throws SQLException;

        /**
         * 获取Plan对象，通过PlanId
         * @param planId
         * @return
         * @throws SQLException
         */
        public PlanBean getPlanInfo(int planId) throws SQLException;


        /**
         * 获取计划id列表，通过团队id
         * @param teamId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getPlanIdListByTeamId(int teamId)throws SQLException;

        /**
         * 获取计划id列表，通过教师id
         * @param teacherId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getPlanIdListByTeacherId(int teacherId) throws SQLException;

        /**
         * 获取计划id列表，通过项目id
         * @param projectId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getPlanIdListByProjectId(int projectId) throws SQLException;

        /**
         * 获取计划id列表，通过团队id，项目id
         * @param teamId
         * @param projectId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getPlanIdListByTeamIdProjectId(int teamId, int projectId) throws SQLException;

        /**
         * 获取计划id列表，通过团队id，教师id
         * @param teamId
         * @param teacherId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getPlanIdListByTeamIdTeacherId(int teamId, int teacherId) throws SQLException;

        /**
         * 获取计划Id, 通过项目id，教师id
         * @param projectId
         * @param teacherId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getPlanIdListByProjectIdTeacherId(int projectId, int teacherId) throws SQLException;

        /**
         * 获取计划id，通过教师id，项目id，团队id
         * @param teacherId
         * @param projectId
         * @param teamId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getPlanIdListByTeacherIdProjectIdTeamId(int teacherId, int projectId, int teamId) throws SQLException;


        /**
         * 获取团队id列表，通过计划id
         * @param planId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getTeamIdList(int planId) throws SQLException;

        /**
         * 获取团队id列表，通过计划id,项目id
         * @param planId
         * @param projectId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getTeamIdListByProjectId(int planId, int projectId) throws SQLException;

        /**
         * 获取团队id列表，通过计划id，教师id
         * @param planId
         * @param teacherId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getTeamIdListByTeacherId(int planId, int teacherId) throws SQLException;

        /**
         * 获取团队id列表，通过计划id，教师id，项目id
         * @param planId
         * @param teacherId
         * @param projectId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getTeamIdListByTeacherIdProjectId(int planId, int teacherId, int projectId) throws SQLException;

        /**
         * 获取教师id列表，通过计划id
         * @param planId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getTeacherIdList(int planId) throws SQLException;

        /**
         * 获取教师id列表，通过计划id，团队id
         * @param planId
         * @param teamId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getTeacherIdListByTeamId(int planId, int teamId) throws SQLException;

        /**
         * 获取教师id列表，通过计划id，项目id
         * @param planId
         * @param projectId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getTeacherIdListByProjectId(int planId, int projectId)throws SQLException;

        /**
         * 获取教师id列表，通过计划id，团队id，项目id
         * @param planId
         * @param teamId
         * @param projectId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getTeacherIdListByTeamIdProjectId(int planId, int teamId, int projectId) throws SQLException;

        /**
         * 获取项目id列表，通过计划id
         * @param planId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getProjectIdList(int planId) throws SQLException;

        /**
         * 获取项目id列表，通过计划id，项目id
         * @param planId
         * @param teamId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getProjectIdListByTeamId(int planId, int teamId) throws SQLException;
        /**
         * 获取项目id列表，通过计划id，教师id
         * @param planId
         * @param teacherId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getProjectIdListByTeacherId(int planId, int teacherId) throws SQLException;

        /**
         * 获取项目id列表，通过计划id，团队id，教师id
         * @param planId
         * @param teamId
         * @param teacherId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getProjectIdListByTeamIdTeacherId(int planId, int teamId, int teacherId) throws SQLException;

        /**
         * 获取项目id列表，通过计划id,团队id
         * @param planId
         * @param teamId
         * @return
         * @throws SQLException
         */
        public ArrayList<Integer> getProjectIdListByTeamIdPlanId(int planId, int teamId)throws SQLException;
    }


