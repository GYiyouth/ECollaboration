package DAO.projectDAO;

import bean.domain.ProjectBean;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by GR on 2016/11/30.
 */
public class ProjectDAOImpl implements ProjectDAO{
    /**
     * 添加项目，内部生成id，返回项目id
     *
     * @param projectBean
     * @return int
     * @throws SQLException
     */
    @Override
    public int addProject(ProjectBean projectBean) throws SQLException {
        return 0;
    }

    /**
     * 根据id寻找项目，返回project
     *
     * @param projectId
     * @return ProjectBean
     * @throws SQLException
     */
    @Override
    public ProjectBean getProjectInfo(int projectId) throws SQLException {
        return null;
    }

    /**
     * 修改项目的属性，如果project属性不为null，则更新数据库
     *
     * @param projectBean
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean updateInfo(ProjectBean projectBean) throws SQLException {
        return false;
    }

    /**
     * 删除项目
     *
     * @param projectId
     * @return ProjectBean
     * @throws SQLException
     */
    @Override
    public ProjectBean deleteById(int projectId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过教师id
     *
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTeacherId(int teacherId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过文件id
     *
     * @param fileId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByFileId(int fileId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过文件id，教师id
     *
     * @param fileId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByFileIdTeacherId(int fileId, int teacherId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过文件id，学生id
     *
     * @param fileId
     * @param studentId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByFileIdStudentId(int fileId, int studentId) throws SQLException {
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
    public ArrayList<Integer> getProjectIdListByPlanId(int planId) throws SQLException {
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
    public ArrayList<Integer> getProjectIdListByPlanIdTeacherId(int planId, int teacherId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过计划id，学生id
     *
     * @param planId
     * @param studentId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByPlanIdStudentId(int planId, int studentId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过学生id
     *
     * @param studentId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByStudentId(int studentId) throws SQLException {
        return null;
    }

    /**
     * 通过项目id列表，通过代码id
     *
     * @param codeId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByCodeId(int codeId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过代码id，学生id
     *
     * @param codeId
     * @param StudentId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByCodeIdStudentId(int codeId, int StudentId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过团队id
     *
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTeamId(int teamId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过任务id
     *
     * @param taskId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTaskId(int taskId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过任务id，团队id
     *
     * @param taskId
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTaskIdTeamId(int taskId, int teamId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过任务id，教师id
     *
     * @param taskId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTaskIdTeacherId(int taskId, int teacherId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过任务id，教师id，团队id
     *
     * @param taskId
     * @param teacherId
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTaskIdTeacherIdTeamId(int taskId, int teacherId, int teamId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过教师id，团队id
     *
     * @param teacherId
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTeacherIdTeamId(int teacherId, int teamId) throws SQLException {
        return null;
    }

    /**
     * 获取团队id列表，通过项目id
     *
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeamIdList(int projectId) throws SQLException {
        return null;
    }

    /**
     * 获取任务id列表，通过项目id
     *
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getAllTaskIdList(int projectId) throws SQLException {
        return null;
    }

    /**
     * 获取任务id列表，通过项目id，团队id
     *
     * @param projectId
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTaskIdListOfOneTeam(int projectId, int teamId) throws SQLException {
        return null;
    }

    /**
     * 获取教师id列表，通过项目id，如果没有则返回0
     *
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeacherId(int projectId) throws SQLException {
        return null;
    }

    /**
     * 获取代码id列表，通过项目id
     *
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getAllCodeIdList(int projectId) throws SQLException {
        return null;
    }

    /**
     * 获取代码id列表，通过项目id，团队id
     *
     * @param projectId
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getCodeIdListOfOneTeam(int projectId, int teamId) throws SQLException {
        return null;
    }

    /**
     * 获取代码id列表，通过项目id，团队id
     *
     * @param projectId
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getCodeIdListOfOneStudent(int projectId, int teamId) throws SQLException {
        return null;
    }

    /**
     * 获取学生id列表，通过项目id
     *
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getStudentIdList(int projectId) throws SQLException {
        return null;
    }

    /**
     * 获取计划id列表，通过项目id，学生id
     *
     * @param projectId
     * @param studentId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getPlanIdListByStudentId(int projectId, int studentId) throws SQLException {
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
    public ArrayList<Integer> getAllPlanIdList(int projectId) throws SQLException {
        return null;
    }

    /**
     * 获取文件id列表，通过项目id，学生id
     *
     * @param projectId
     * @param studentId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getFileIdListByStudentId(int projectId, int studentId) throws SQLException {
        return null;
    }

    /**
     * 获取文件id列表，通过项目id
     *
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getAllFileIdList(int projectId) throws SQLException {
        return null;
    }
}
