package DAO.taskDAO;

import bean.domain.TaskBean;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 任务发布给项目，计划可以发布给团队
 * Created by geyao on 2016/11/7.
 */
public interface TaskDAO {
	/**
	 * 添加任务，获取新任务id
	 * @param taskBean
	 * @param projects
	 * @return
	 * @throws SQLException
	 */
	public Integer addTask(TaskBean taskBean,ArrayList<Integer>projects) throws Exception;

	/**
	 * 删除任务，获取此任务Task对象
	 * @param taskId
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteTask(int taskId) throws SQLException;

	/**
	 * 修改任务，获取bool变量
	 * @param taskBean
	 * @return
	 * @throws SQLException
	 */
	public boolean updateTask(TaskBean taskBean) throws SQLException;

	/**
	 * 获取Task对象，通过TaskId
	 * @param taskId
	 * @return
	 * @throws SQLException
	 */
	public TaskBean getTaskInfo(int taskId) throws SQLException;


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
	 * 获取当前执行中任务id，通过项目id
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public Integer getExecutingTaskIdByProjectId(int projectId) throws SQLException;

//	/**
//	 * 获取任务id列表，通过团队id，项目id
//	 * @param teamId
//	 * @param projectId
//	 * @return
//	 * @throws SQLException
//	 */
//	public ArrayList<Integer> getTaskIdListByTeamIdProjectId(int teamId, int projectId) throws SQLException;

	/**
	 * 获取任务id列表，通过团队id，教师id
	 * @param teamId
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTaskIdListByTeamIdTeacherId(int teamId, int teacherId) throws SQLException;

//	/**
//	 * 获取任务id
//	 * @param teamId
//	 * @param projectId
//	 * @return
//	 * @throws SQLException
//	 */
//	public ArrayList<Integer> getTaskIdListByTeamIdProjectId(int teamId, int projectId) throws SQLException;

//	/**
//	 * 获取任务Id, 通过项目id，教师id
//	 * @param projectId
//	 * @param teacherId
//	 * @return
//	 * @throws SQLException
//	 */
//	public ArrayList<Integer> getTaskIdListByProjectIdTeacherId(int projectId, int teacherId) throws SQLException;
//
//	/**
//	 * 获取任务id，通过教师id，项目id，团队id
//	 * @param teacherId
//	 * @param projectId
//	 * @param teamId
//	 * @return
//	 * @throws SQLException
//	 */
//	public ArrayList<Integer> getTaskIdListByTeacherIdProjectIdTeamId(int teacherId, int projectId, int teamId) throws SQLException;



}
