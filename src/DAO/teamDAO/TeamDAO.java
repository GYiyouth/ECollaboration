package DAO.teamDAO;

import bean.domain.TeamBean;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 与student, code, project, task, teacher相连
 * Created by geyao on 2016/11/7.
 */
public interface TeamDAO {
	/**
	 * 添加团队，返回团队id
	 *
	 * @param teamBean
	 * @return
	 * @throws SQLException
	 */
	public Integer addTeam(TeamBean teamBean) throws SQLException;

	/**
	 * 更新团队信息，返回布尔值
	 *
	 * @param teamBean
	 * @return
	 * @throws SQLException
	 */
	public boolean updateTeam(TeamBean teamBean) throws SQLException;

	/**
	 * 查询队伍信息，返回team对象
	 *
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public TeamBean getTeamInfo(int teamId) throws SQLException;

	/**
	 * 删除团队，返回被删除的这个team对象
	 *
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public TeamBean deleteTeam(int teamId) throws SQLException;


	/**
	 * 获取团队id列表，通过项目id
	 *
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByProjectId(int projectId) throws SQLException;


	/**
	 * 获取学生在团队身份
	 * @param teamId
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public Integer getleadFlagByTeamIdStudentId(int teamId, int studentId) throws Exception;
	/**
	 * 获取团队id列表，通过学生id，
	 *
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByStudentId(int studentId) throws SQLException;

	/**
	 * 获取团队id列表，通过学生id，代码id
	 *
	 * @param studentId
	 * @param codeId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByStudentIdCodeId(int studentId, int codeId) throws SQLException;

	/**
	 * 获取团队id列表，通过学生ID，项目id
	 *
	 * @param studentId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByStudentIdProjectId(int studentId, int projectId) throws SQLException;

	/**
	 * 获取团队id列表，通过代码id
	 *
	 * @param codeId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByCodeId(int codeId) throws SQLException;

	/**
	 * 获取团队id列表，通过教师id
	 *
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByTeacherId(int teacherId) throws SQLException;

	/**
	 * 获取团队id列表，通过教师id，项目id
	 *
	 * @param teacherId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByTeacherIdProjectId(int teacherId, int projectId) throws SQLException;

	/**
	 * 获取团队id列表，通过教师id，任务id
	 *
	 * @param teacherId
	 * @param taskId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByTeacherIdTaskId(int teacherId, int taskId) throws SQLException;

	/**
	 * 获取团队id,通过任务id，项目id
	 *
	 *
	 * @param taskId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
//	public ArrayList<Integer> getTeamIdListByTaskIdProjectId(int taskId, int projectId) throws SQLException;

	/**
	 * 获取团队id列表，通过任务id
	 *
	 * @param taskId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByTaskId(int taskId) throws SQLException;


	/**
	 * 添加关系
	 * @param teamId
	 * @param projectId
	 * @return
	 */
	public boolean setTeamProject(int teamId, int projectId) throws SQLException;

	/**
	 * 删除关系
	 * @param teamId
	 * @param projectId
	 * @return
	 */
	public boolean deleteTeamProject(int teamId, int projectId) throws SQLException;

	/**
	 * 删除关系
	 * @param teamId
	 * @return
	 */
	public boolean deleteTeam_Project(int teamId) throws SQLException;

	/**
	 * 删除关系
	 * @param teamId
	 * @return
	 */
	public boolean deleteProject_Team(int teamId) throws SQLException;

	/**
	 * 获取团队创建人id
	 * @param teamId
	 * @return
	 * @throws Exception
	 */
	public Integer getCreatorIdByTeamId(int teamId) throws Exception;

	/**
	 * 根据学生id，项目id，获取团队id
	 * @param studentId
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	public Integer getTeamIdByStudentIdProjectId(int studentId, int projectId) throws Exception;

	/**
	 * 获取申请进入团队的人的id
	 * @param teamId
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Integer> getApplyStudentIdByTeamId(int teamId) throws Exception;
}
