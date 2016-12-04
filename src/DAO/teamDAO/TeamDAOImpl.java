package DAO.teamDAO;

import DAO.com.smallTools.ComGetListBy2DAO;
import DAO.com.smallTools.ComGetListBy2DAOImpl;
import DAO.com.smallTools.ComGetListValueDAO;
import DAO.com.smallTools.ComGetListValueDAOImpl;
import DAO.com.util.db.DBUtils;
import bean.domain.TeamBean;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by geyao on 2016/12/1.
 */
public class TeamDAOImpl implements TeamDAO {
	/**
	 * 添加团队，返回团队id
	 *
	 * @param teamBean
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Integer addTeam(TeamBean teamBean) throws SQLException {
		if (teamBean == null)
			return null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "INSERT INTO ecollaborationweb.team " +
				"(teamName, creatorId, createDate) " +
				"VALUES (?, ?, ?);";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(    1, teamBean.getTeamName());
			preparedStatement.setInt(       2, teamBean.getCreatorId());
			preparedStatement.setString(    3, teamBean.getCreateDate());
			int flag = preparedStatement.executeUpdate();
			if (flag == 1) {
				sql = "SELECT LAST_INSERT_ID();";
				preparedStatement = connection.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next())
					return resultSet.getInt(1);
			}
			return null;
		}catch (SQLException e){
			e.printStackTrace();
			throw  e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 更新团队信息，返回布尔值
	 *
	 * @param teamBean
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean updateTeam(TeamBean teamBean) throws SQLException {
		if (getTeamInfo(teamBean.getId()) == null)
			return false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE ecollaborationweb.team " +
				"SET teamName = ?, creatorId = ?, createDate = ? " +
				"WHERE id = ?;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, teamBean.getTeamName());
			preparedStatement.setInt(   2, teamBean.getCreatorId());
			preparedStatement.setString(3, teamBean.getCreateDate());
			preparedStatement.setInt(   4, teamBean.getId());
			int flag = preparedStatement.executeUpdate();
			if (flag ==1 )
				return true;
			else
				return false;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}

	/**
	 * 查询队伍信息，返回team对象
	 *
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public TeamBean getTeamInfo(int teamId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM ecollaborationweb.team WHERE id = ?;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				TeamBean teamBean = new TeamBean();
				teamBean.setCreateDate(resultSet.getString("createDate"));
				teamBean.setCreatorId(  resultSet.getInt("creatorId"));
				teamBean.setTeamName(   resultSet.getString("teamName"));
				teamBean.setId(resultSet.getInt("id"));
				return teamBean;
			}else
				return null;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 删除团队，返回被删除的这个team对象
	 *
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public TeamBean deleteTeam(int teamId) throws SQLException {
		TeamBean teamBean = getTeamInfo(teamId);
		if (teamBean == null){
			return null;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM ecollaborationweb.team WHERE id = ?;";
		try{
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamId);
			int flag = preparedStatement.executeUpdate();
			if (flag == 1)
				return teamBean;
			else
				return null;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}

	/**
	 * 获取团队id列表，通过项目id
	 *
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getTeamIdListByProjectId(int projectId) throws SQLException {
		ComGetListValueDAO<Integer, Integer> comGetListValueDAO = new ComGetListValueDAOImpl<>();
		ArrayList<Integer> teamIdList = new ArrayList<>();
		try {
			teamIdList = comGetListValueDAO.getListAfromBbyC("teamId", "projectId", projectId, "team_project");
			return teamIdList;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * 获取团队id列表，通过学生id，
	 *
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getTeamIdListByStudentId(int studentId) throws SQLException {
		try {
			ArrayList<Integer> list = new ArrayList<>();
			ComGetListValueDAO comGetListValueDAO = new ComGetListValueDAOImpl<Integer, Integer>();
			list = comGetListValueDAO.getListAfromBbyC("teamId", "studentId", studentId, "student_team");
			return list;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取团队id列表，通过学生id，代码id
	 *
	 * @param studentId
	 * @param codeId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getTeamIdListByStudentIdCodeId(int studentId, int codeId) throws SQLException {
		try {
			ArrayList<Integer> list = new ArrayList<>();
			ComGetListBy2DAO comGetListBy2DAO = new ComGetListBy2DAOImpl<Integer, Integer, Integer>();
			list = comGetListBy2DAO.getListAfromBbyC("teamId", "studentId", studentId, "id", codeId, "code");
			return list;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取团队id列表，通过学生ID，项目id
	 *  这个SQL写不好，因为返回的表里包含有相同的团队id，这里采用了转化为哈希集合，再转为列表的方式解决。
	 * @param studentId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getTeamIdListByStudentIdProjectId(int studentId, int projectId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Integer> list = new ArrayList<>();
		String sql = "SELECT tp.teamId FROM ECollaborationWeb.student_team AS st , ECollaborationWeb.team_project AS tp " +
				" WHERE st.studentId = ? AND tp.projectId = ? AND  tp.teamId = tp.teamId;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, studentId);
			preparedStatement.setInt(2, projectId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){

				list.add(resultSet.getInt(1));
			}
			if (list.size() > 0){
				//去除重复的teamId
				HashSet h = new HashSet(list);
				list.clear();
				list.addAll(h);
				return list;
			}else{
				return null;
			}
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取团队id列表，通过代码id
	 *
	 * @param codeId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getTeamIdListByCodeId(int codeId) throws SQLException {
		ArrayList<Integer> list = null;
		ComGetListValueDAO comGetListValueDAO = new ComGetListValueDAOImpl<Integer, Integer>();
		try {
			list = comGetListValueDAO.getListAfromBbyC("teamId", "id", codeId, "code");
			return list;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取团队id列表，通过教师id
	 *
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getTeamIdListByTeacherId(int teacherId) throws SQLException {
		ArrayList<Integer> list = null;
		ComGetListValueDAO comGetListValueDAO = new ComGetListValueDAOImpl<Integer, Integer>();
		try {
			list = comGetListValueDAO.getListAfromBbyC("teamId", "teacherId", teacherId, "teacher_team");
			return list;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取团队id列表，通过教师id，项目id
	 *
	 * @param teacherId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getTeamIdListByTeacherIdProjectId(int teacherId, int projectId) throws SQLException {
		ArrayList<Integer> list = null;
		ComGetListBy2DAO comGetListBy2DAO = new ComGetListBy2DAOImpl<Integer, Integer, Integer>();
		try {
			list = comGetListBy2DAO.getListAfromBbyC("teamId", "teacherId", teacherId, "projectId", projectId, "teacher_team");
			return list;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取团队id列表，通过教师id，任务id
	 *
	 * @param teacherId
	 * @param taskId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getTeamIdListByTeacherIdTaskId(int teacherId, int taskId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Integer> list = new ArrayList<>();
		String sql = "SELECT teachert.teamId FROM ECollaborationWeb.teacher_team AS teachert  " +
				" , ECollaborationWeb.team_task AS taskt " +
				" WHERE teachert.teamId  = taskt.teamId " +
				"AND teachert.teacherId = ? " +
				"AND teachert.teacherId = ? AND taskt.taskId = ?;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teacherId);
			preparedStatement.setInt(2, teacherId);
			preparedStatement.setInt(3, taskId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				list.add(resultSet.getInt(1));
			}
			if (list.size() > 0){
				//去除重复的teamId
				HashSet h = new HashSet(list);
				list.clear();
				list.addAll(h);
				return list;
			}else
				return null;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取团队id,通过教师id，任务id，项目id
	 *
	 * @param teacherId
	 * @param taskId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getTeamIdListByTeacherIdTaskIdProjectId(int teacherId, int taskId, int projectId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Integer> list = new ArrayList<>();
		String sql = "SELECT teamId FROM ECollaborationWeb.team_task " +
				"WHERE teacherId = ? AND taskId = ? AND  projectId = ? ;";
		try{
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teacherId);
			preparedStatement.setInt(2, taskId);
			preparedStatement.setInt(3, projectId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				list.add(resultSet.getInt(1));
			}
			if (list.size() > 0){
				//去除重复的teamId
				HashSet h = new HashSet(list);
				list.clear();
				list.addAll(h);
				return list;
			}else
				return null;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取团队id列表，通过任务id
	 *
	 * @param taskId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getTeamIdListByTaskId(int taskId) throws SQLException {
		ComGetListValueDAO comGetListValueDAO = new ComGetListValueDAOImpl<Integer, Integer>();
		try {
			ArrayList<Integer> list = comGetListValueDAO.getListAfromBbyC("teamId", "taskId", taskId, "team_project");
			return list;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取项目id列表，通过团队id
	 *
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getProjectIdList(int teamId) throws SQLException {
		return null;
	}

	/**
	 * 获取学生的id列表，通过团队id
	 *
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getStudentIdList(int teamId) throws SQLException {
		return null;
	}

	/**
	 * 获取代码id列表，通过团队id
	 *
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getCodeIdList(int teamId) throws SQLException {
		return null;
	}

	/**
	 * 获取老师id列表，通过团队id。没有返回null,
	 *
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getTeacherIdList(int teamId) throws SQLException {
		return null;
	}

	/**
	 * 获取老师id列表，通过团队id与项目id。其实目前阶段一个项目就一个老师，但为了可扩性，这里还是写ArrayList
	 *
	 * @param teamId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getTeacherIdofOneProject(int teamId, int projectId) throws SQLException {
		return null;
	}

	/**
	 * 获取任务id列表，通过团队id，没有返回null
	 *
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getTaskIdList(int teamId) throws SQLException {
		return null;
	}

	/**
	 * 获取任务id列表，通过团队id与项目id
	 *
	 * @param teamId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getTaskIdListOfProject(int teamId, int projectId) throws SQLException {
		return null;
	}
}
