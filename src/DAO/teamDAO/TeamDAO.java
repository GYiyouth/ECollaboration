package DAO.teamDAO;

import bean.domain.Team;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 与student, code, project, task, teacher相连
 * Created by geyao on 2016/11/7.
 */
public interface TeamDAO {
	/**
	 * 添加团队，返回团队id
	 * @param team
	 * @return
	 * @throws SQLException
	 */
	public Integer addTeam(Team team) throws SQLException;

	/**
	 * 更新团队信息，返回布尔值
	 * @param team
	 * @return
	 * @throws SQLException
	 */
	public boolean updateTeam(Team team) throws SQLException;

	/**
	 * 查询队伍信息，返回team对象
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public Team getTeamInfo(int teamId) throws SQLException;

	/**
	 * 删除团队，返回被删除的这个team对象
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public Team deleteTeam(int teamId) throws SQLException;





	/**
	 * 获取团队id列表，通过项目id
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByProjectId(int projectId) throws SQLException;

	/**
	 * 获取团队id列表，通过学生id，
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByStudentId(int studentId) throws SQLException;

	/**
	 * 获取团队id列表，通过学生id，代码id
	 * @param studentId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByStudentIdCodeId(int studentId, int projectId) throws SQLException;

	/**
	 * 获取团队id列表，通过学生ID，项目id
	 * @param studentId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByStudentIdProjectId(int studentId, int projectId) throws SQLException;

	/**
	 * 获取团队id列表，通过代码id
	 * @param codeId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByCodeId(int codeId) throws SQLException;

	/**
	 * 获取团队id列表，通过教师id
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByTeacherId(int teacherId) throws SQLException;

	/**
	 * 获取团队id列表，通过教师id，项目id
	 * @param teacherId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByTeacherIdProjectId(int teacherId, int projectId) throws SQLException;

	/**
	 * 获取团队id列表，通过教师id，任务id
	 * @param teacherId
	 * @param taskId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByTeacherIdTaskId(int teacherId, int taskId) throws SQLException;

	/**
	 * 获取团队id,通过教师id，任务id，项目id
	 * @param teacherId
	 * @param taskId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByTeacherIdTaskIdProjectId(int teacherId, int taskId, int projectId) throws SQLException;

	/**
	 * 获取团队id列表，通过任务id
	 * @param taskId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByTaskId(int taskId) throws SQLException;



	/**
	 * 获取项目id列表，通过团队id
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdList(int teamId) throws SQLException;




	/**
	 * 获取学生的id列表，通过团队id
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getStudentIdList(int teamId) throws SQLException;


	/**
	 * 获取代码id列表，通过团队id
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getCodeIdList(int teamId) throws SQLException;


	/**
	 * 获取老师id列表，通过团队id。没有返回null,
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeacherIdList(int teamId) throws SQLException;


	/**
	 * 获取老师id列表，通过团队id与项目id。其实目前阶段一个项目就一个老师，但为了可扩性，这里还是写ArrayList
	 * @param teamId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeacherIdofOneProject(int teamId, int projectId) throws SQLException;



	/**
	 * 获取任务id列表，通过团队id，没有返回null
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTaskIdList(int teamId) throws SQLException;

	/**
	 * 获取任务id列表，通过团队id与项目id
	 * @param teamId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTaskIdListOfProject(int teamId, int projectId) throws SQLException;

}
