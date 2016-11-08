package DAO.taskDAO;

import bean.Task;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by geyao on 2016/11/7.
 */
public interface TaskDAO {
	/**
	 * 添加任务，获取新任务id
	 * @param task
	 * @return
	 * @throws SQLException
	 */
	public Integer addTask(Task task) throws SQLException;

	/**
	 * 删除任务，获取此任务Task对象
	 * @param taskId
	 * @return
	 * @throws SQLException
	 */
	public Task deleteTask(int taskId) throws SQLException;

	/**
	 * 修改任务，获取bool变量
	 * @param task
	 * @return
	 * @throws SQLException
	 */
	public boolean updateTask(Task task) throws SQLException;

	/**
	 * 获取Task对象，通过TaskId
	 * @param taskId
	 * @return
	 * @throws SQLException
	 */
	public Task getTaskInfo(int taskId) throws SQLException;


	/**
	 * 获取任务id列表，通过团队id
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTaskIdListByTeamId(int teamId)throws SQLException;

	/**
	 * 获取任务id列表，通过教师id
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTaskIdListByTeacherId(int teacherId) throws SQLException;

	/**
	 * 获取任务id列表，通过项目id
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTaskIdListByProjectId(int projectId) throws SQLException;

	/**
	 * 获取任务id列表，通过团队id，项目id
	 * @param teamId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTaskIdListByTeamIdProjectId(int teamId, int projectId) throws SQLException;

	/**
	 * 获取任务id列表，通过团队id，教师id
	 * @param teamId
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTaskIdListByTeamIdTeacherId(int teamId, int teacherId) throws SQLException;

	/**
	 * 获取任务Id, 通过项目id，教师id
	 * @param projectId
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTaskIdListByProjectIdTeacherId(int projectId, int teacherId) throws SQLException;

	/**
	 * 获取任务id，通过教师id，项目id，团队id
	 * @param teacherId
	 * @param projectId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTaskIdListByTeacherIdProjectIdTeamId(int teacherId, int projectId, int teamId) throws SQLException;


	/**
	 * 获取团队id列表，通过任务id
	 * @param taskId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdList(int taskId) throws SQLException;

	/**
	 * 获取团队id列表，通过任务id,项目id
	 * @param taskId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByProjectId(int taskId, int projectId) throws SQLException;

	/**
	 * 获取团队id列表，通过任务id，教师id
	 * @param taskId
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByTeacherId(int taskId, int teacherId) throws SQLException;

	/**
	 * 获取团队id列表，通过任务id，教师id，项目id
	 * @param taskId
	 * @param teacherId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByTeacherIdProjectId(int taskId, int teacherId, int projectId) throws SQLException;

	/**
	 * 获取教师id列表，通过任务id
	 * @param taskId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeacherIdList(int taskId) throws SQLException;

	/**
	 * 获取教师id列表，通过任务id，团队id
	 * @param taskId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeacherIdListByTeamId(int taskId, int teamId) throws SQLException;

	/**
	 * 获取教师id列表，通过任务id，项目id
	 * @param taskId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeacherIdListByProjectId(int taskId, int projectId)throws SQLException;

	/**
	 * 获取教师id列表，通过任务id，团队id，项目id
	 * @param taskId
	 * @param teamId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeacherIdListByTeamIdProjectId(int taskId, int teamId, int projectId) throws SQLException;

	/**
	 * 获取项目id列表，通过任务id
	 * @param taskId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdList(int taskId) throws SQLException;

	/**
	 * 获取项目id列表，通过任务id，项目id
	 * @param taskId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByTeamId(int taskId, int teamId) throws SQLException;
	/**
	 * 获取项目id列表，通过任务id，教师id
	 * @param taskId
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByTeacherId(int taskId, int teacherId) throws SQLException;

	/**
	 * 获取项目id列表，通过任务id，团队id，教师id
	 * @param taskId
	 * @param teamId
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByTeamIdTeacherId(int taskId, int teamId, int teacherId) throws SQLException;

	/**
	 * 获取项目id列表，通过任务id,团队id
	 * @param taskId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByTeamIdTaskId(int taskId, int teamId)throws SQLException;


	}
