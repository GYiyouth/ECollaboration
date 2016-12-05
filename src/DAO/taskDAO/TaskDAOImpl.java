package DAO.taskDAO;

import bean.domain.TaskBean;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by GR on 2016/12/5.
 */
public class TaskDAOImpl implements TaskDAO{
    /**
     * 添加任务，获取新任务id
     *
     * @param taskBean
     * @return
     * @throws SQLException
     */
    @Override
    public Integer addTask(TaskBean taskBean) throws SQLException {
        return null;
    }

    /**
     * 删除任务，获取此任务Task对象
     *
     * @param taskId
     * @return
     * @throws SQLException
     */
    @Override
    public TaskBean deleteTask(int taskId) throws SQLException {
        return null;
    }

    /**
     * 修改任务，获取bool变量
     *
     * @param taskBean
     * @return
     * @throws SQLException
     */
    @Override
    public boolean updateTask(TaskBean taskBean) throws SQLException {
        return false;
    }

    /**
     * 获取Task对象，通过TaskId
     *
     * @param taskId
     * @return
     * @throws SQLException
     */
    @Override
    public TaskBean getTaskInfo(int taskId) throws SQLException {
        return null;
    }

    /**
     * 获取任务id列表，通过团队id
     *
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTaskIdListByTeamId(int teamId) throws SQLException {
        return null;
    }

    /**
     * 获取任务id列表，通过教师id
     *
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTaskIdListByTeacherId(int teacherId) throws SQLException {
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
    public ArrayList<Integer> getTaskIdListByProjectId(int projectId) throws SQLException {
        return null;
    }

    /**
     * 获取任务id列表，通过团队id，项目id
     *
     * @param teamId
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTaskIdListByTeamIdProjectId(int teamId, int projectId) throws SQLException {
        return null;
    }

    /**
     * 获取任务id列表，通过团队id，教师id
     *
     * @param teamId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTaskIdListByTeamIdTeacherId(int teamId, int teacherId) throws SQLException {
        return null;
    }

    /**
     * 获取任务Id, 通过项目id，教师id
     *
     * @param projectId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTaskIdListByProjectIdTeacherId(int projectId, int teacherId) throws SQLException {
        return null;
    }

    /**
     * 获取任务id，通过教师id，项目id，团队id
     *
     * @param teacherId
     * @param projectId
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTaskIdListByTeacherIdProjectIdTeamId(int teacherId, int projectId, int teamId) throws SQLException {
        return null;
    }

    /**
     * 获取团队id列表，通过任务id
     *
     * @param taskId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeamIdList(int taskId) throws SQLException {
        return null;
    }

    /**
     * 获取团队id列表，通过任务id,项目id
     *
     * @param taskId
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeamIdListByProjectId(int taskId, int projectId) throws SQLException {
        return null;
    }

    /**
     * 获取团队id列表，通过任务id，教师id
     *
     * @param taskId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeamIdListByTeacherId(int taskId, int teacherId) throws SQLException {
        return null;
    }

    /**
     * 获取团队id列表，通过任务id，教师id，项目id
     *
     * @param taskId
     * @param teacherId
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeamIdListByTeacherIdProjectId(int taskId, int teacherId, int projectId) throws SQLException {
        return null;
    }

    /**
     * 获取教师id列表，通过任务id
     *
     * @param taskId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeacherIdList(int taskId) throws SQLException {
        return null;
    }

    /**
     * 获取教师id列表，通过任务id，团队id
     *
     * @param taskId
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeacherIdListByTeamId(int taskId, int teamId) throws SQLException {
        return null;
    }

    /**
     * 获取教师id列表，通过任务id，项目id
     *
     * @param taskId
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeacherIdListByProjectId(int taskId, int projectId) throws SQLException {
        return null;
    }

    /**
     * 获取教师id列表，通过任务id，团队id，项目id
     *
     * @param taskId
     * @param teamId
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeacherIdListByTeamIdProjectId(int taskId, int teamId, int projectId) throws SQLException {
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
    public ArrayList<Integer> getProjectIdList(int taskId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过任务id，项目id
     *
     * @param taskId
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTeamId(int taskId, int teamId) throws SQLException {
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
    public ArrayList<Integer> getProjectIdListByTeacherId(int taskId, int teacherId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过任务id，团队id，教师id
     *
     * @param taskId
     * @param teamId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTeamIdTeacherId(int taskId, int teamId, int teacherId) throws SQLException {
        return null;
    }

    /**
     * 获取项目id列表，通过任务id,团队id
     *
     * @param taskId
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTeamIdTaskId(int taskId, int teamId) throws SQLException {
        return null;
    }
}
