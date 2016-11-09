package DAO.projectDAO;

import bean.domain.Project;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 项目的dao接口
 * private Integer id = null;
 private String name = null;
 private Date endDate = null;
 private Date finishDate = null;
 private Date survivalDate = null;
 private Integer teamNumber = null;
 private Integer teamMax = null;
 private Integer memberMax = null;
 private Date createDate = null;
 private Integer grade = null;
 private String keyWord = null;
 private String info = null;
 private String require = null;
 private String gain = null;
 private Integer priority = null;
 private Integer status = null;
 private Integer creatorId = null;
 private Integer teacherId = null;

 team task code student plan file teacher
 * Created by geyao on 2016/11/7.
 */
public interface ProjectDAO {
	/**
	 * 添加项目，内部生成id，返回项目id
	 * @param project
	 * @return int
	 * @throws SQLException
	 */
	public int addProject(Project project) throws SQLException;

	/**
	 * 根据id寻找项目，返回project
	 * @param projectId
	 * @return Project
	 * @throws SQLException
	 */
	public Project getProjectInfo(int projectId) throws SQLException;

	/**
	 * 修改项目的属性，如果project属性不为null，则更新数据库
	 * @param project
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(Project project) throws SQLException;


	/**
	 * 删除项目
	 * @param projectId
	 * @return Project
	 * @throws SQLException
	 */
	public Project deleteById(int projectId) throws SQLException;



	/**
	 * 获取项目id列表，通过教师id
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByTeacherId (int teacherId) throws SQLException;

	/**
	 * 获取项目id列表，通过文件id
	 * @param fileId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByFileId(int fileId) throws SQLException;

	/**
	 * 获取项目id列表，通过文件id，教师id
	 * @param fileId
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByFileIdTeacherId(int fileId, int teacherId) throws SQLException;

	/**
	 * 获取项目id列表，通过文件id，学生id
	 * @param fileId
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByFileIdStudentId(int fileId, int studentId) throws SQLException;

	/**
	 * 获取项目id列表，通过计划id
	 * @param planId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByPlanId(int planId) throws SQLException;

	/**
	 * 获取项目id列表，通过计划id，教师id
	 * @param planId
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByPlanIdTeacherId(int planId, int teacherId) throws SQLException;

	/**
	 * 获取项目id列表，通过计划id，学生id
	 * @param planId
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByPlanIdStudentId(int planId, int studentId) throws SQLException;

	/**
	 * 获取项目id列表，通过学生id
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByStudentId(int studentId) throws SQLException;

	/**
	 * 通过项目id列表，通过代码id
	 * @param codeId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByCodeId(int codeId) throws SQLException;

	/**
	 * 获取项目id列表，通过代码id，学生id
	 * @param codeId
	 * @param StudentId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByCodeIdStudentId(int codeId, int StudentId) throws SQLException;

	/**
	 * 获取项目id列表，通过团队id
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByTeamId(int teamId) throws SQLException;

	/**
	 * 获取项目id列表，通过任务id
	 * @param taskId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByTaskId(int taskId) throws SQLException;

	/**
	 * 获取项目id列表，通过任务id，团队id
	 * @param taskId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByTaskIdTeamId(int taskId, int teamId) throws SQLException;

	/**
	 * 获取项目id列表，通过任务id，教师id
	 * @param taskId
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByTaskIdTeacherId(int taskId, int teacherId) throws SQLException;

	/**
	 * 获取项目id列表，通过任务id，教师id，团队id
	 * @param taskId
	 * @param teacherId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByTaskIdTeacherIdTeamId(int taskId, int teacherId, int teamId)throws SQLException;

	/**
	 * 获取项目id列表，通过教师id，团队id
	 * @param teacherId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByTeacherIdTeamId(int teacherId, int teamId) throws SQLException;





	/**
	 * 获取团队id列表，通过项目id
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdList(int projectId) throws SQLException;

	/**
	 * 获取任务id列表，通过项目id
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getAllTaskIdList(int projectId) throws SQLException;

	/**
	 * 获取任务id列表，通过项目id，团队id
	 * @param projectId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTaskIdListOfOneTeam(int projectId, int teamId) throws SQLException;

	/**
	 * 获取教师id列表，通过项目id，如果没有则返回0
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeacherId(int projectId) throws SQLException;


	/**
	 * 获取代码id列表，通过项目id
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getAllCodeIdList(int projectId) throws SQLException;

	/**
	 * 获取代码id列表，通过项目id，团队id
	 * @param projectId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getCodeIdListOfOneTeam (int projectId, int teamId) throws SQLException;

	/**
	 * 获取代码id列表，通过项目id，团队id
	 * @param projectId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getCodeIdListOfOneStudent (int projectId, int teamId) throws SQLException;

	/**
	 * 获取学生id列表，通过项目id
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getStudentIdList(int projectId) throws SQLException;

	/**
	 * 获取计划id列表，通过项目id，学生id
	 * @param projectId
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getPlanIdListByStudentId(int projectId, int studentId) throws SQLException;

	/**
	 * 获取计划id列表，通过项目id
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getAllPlanIdList(int projectId) throws SQLException;

	/**
	 * 获取文件id列表，通过项目id，学生id
	 * @param projectId
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getFileIdListByStudentId(int projectId, int studentId) throws SQLException;

	/**
	 * 获取文件id列表，通过项目id
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getAllFileIdList(int projectId) throws SQLException;
}
