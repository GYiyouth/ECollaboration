package DAO.teamDAO;

import DAO.com.smallTools.ComGetListBy2DAO;
import DAO.com.smallTools.ComGetListBy2DAOImpl;
import DAO.com.smallTools.ComGetListValueDAO;
import DAO.com.smallTools.ComGetListValueDAOImpl;
import DAO.com.util.db.DBUtils;
import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import bean.domain.ProjectBean;
import bean.domain.TeamBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
				"(teamName, creatorId, createDate, memberMax, description) " +
				"VALUES (?, ?, ?, ?, ?);";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(    1, teamBean.getTeamName());
			preparedStatement.setInt(       2, teamBean.getCreatorId());
			preparedStatement.setString(    3, teamBean.getCreateDate());
			if (teamBean.getMemberMax() != null)
				preparedStatement.setInt(4, teamBean.getMemberMax());
			else
				preparedStatement.setInt(4, 0);
			if (teamBean.getDescription() != null)
				preparedStatement.setString(5, teamBean.getDescription());
			else
				preparedStatement.setString(5, "");
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
				"SET teamName = ?, creatorId = ?, createDate = ?, memberMax = ?, description = ? " +
				"WHERE id = ?;";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, teamBean.getTeamName());
			preparedStatement.setInt(   2, teamBean.getCreatorId());
			preparedStatement.setString(3, teamBean.getCreateDate());
			preparedStatement.setInt(   4, teamBean.getMemberMax());
			preparedStatement.setString(5, teamBean.getDescription());
			preparedStatement.setInt(   6, teamBean.getId());
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
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				TeamBean teamBean = new TeamBean();
				teamBean.setCreateDate(resultSet.getString("createDate"));
				teamBean.setCreatorId(  resultSet.getInt("creatorId"));
				teamBean.setTeamName(   resultSet.getString("teamName"));
				teamBean.setMemberMax(  resultSet.getInt("memberMax"));
				teamBean.setDescription(resultSet.getString("description"));
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
			connection = DBUtils.getConnection();
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
			connection = DBUtils.getConnection();
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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Integer> list = new ArrayList<>();
		String sql = "SELECT teamId FROM ECollaborationWeb.teacher_project AS A ," +
				" ECollaborationWeb.team_project AS B " +
				"WHERE A.teacherId = ? AND A.projectId = B.projectId;";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teacherId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getInt(1));
			}
			if (list.size() >= 1) {
				HashSet h = new HashSet(list);
				list.clear();
				list.addAll(h);
				return list;
			} else
				return null;
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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Integer> list = new ArrayList<>();
		String sql = "SELECT teamId FROM ECollaborationWeb.teacher_project AS A ," +
				" ECollaborationWeb.team_project AS B " +
				"WHERE A.teacherId = ? AND A.projectId = ? AND B.projectId = ?;";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teacherId);
			preparedStatement.setInt(2, projectId);
			preparedStatement.setInt(3, projectId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getInt(1));
			}
			if (list.size() >= 1) {
				HashSet h = new HashSet(list);
				list.clear();
				list.addAll(h);
				return list;
			} else
				return null;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
	}
//没有teacher_team这个表了
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
		String sql = "SELECT teamPro.teamId FROM ECollaborationWeb.project_task AS proT, " +
				" ECollaborationWeb.teacher_project AS teaP, ECollaborationWeb.team_project AS teamPro " +
				" WHERE teaP.teacherId = ? AND teaP.projectId = proT.projectId AND proT.taskId = ? " +
				" AND teamPro.projectId = teaP.projectId";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teacherId);
			preparedStatement.setInt(2, taskId);
//			preparedStatement.setInt(3, task);
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
	 * @param taskId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
//	@Override
//	public ArrayList<Integer> getTeamIdListByTaskIdProjectId(int taskId, int projectId) throws SQLException {
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		ArrayList<Integer> list = new ArrayList<>();
//		String sql = "";
//		try{
//			connection = DBUtils.getConnection();
//			preparedStatement = connection.prepareStatement(sql);
//
//			preparedStatement.setInt(2, taskId);
//			preparedStatement.setInt(3, projectId);
//			resultSet = preparedStatement.executeQuery();
//			while (resultSet.next()){
//				list.add(resultSet.getInt(1));
//			}
//			if (list.size() > 0){
//				//去除重复的teamId
//				HashSet h = new HashSet(list);
//				list.clear();
//				list.addAll(h);
//				return list;
//			}else
//				return null;
//		}catch (SQLException e){
//			e.printStackTrace();
//			throw e;
//		}
//	}

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
	 * 接下来是关系表的操作
	 */

	/**
	 * 添加关系
	 *
	 * @param teamId
	 * @param projectId
	 * @return
	 */
	@Override
	public boolean setTeamProject(int teamId, int projectId) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO ECollaborationWeb.team_project (teamId, projectId, applyFlag) VALUES (?,?,1);";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamId);
			preparedStatement.setInt(2, projectId);
			int flag = preparedStatement.executeUpdate();
			if (flag ==1 ){
				return true;
			}
			return false;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}

	/**
	 * 删除关系
	 *
	 * @param teamId
	 * @param projectId
	 * @return
	 */
	@Override
	public boolean deleteTeamProject(int teamId, int projectId) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM ECollaborationWeb.team_project WHERE teamId =? AND projectId = ?;";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamId);
			preparedStatement.setInt(2, projectId);
			int flag = preparedStatement.executeUpdate();
			if (flag >= 1)
				return true;
			return false;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}

	/**
	 * 删除关系
	 *
	 * @param teamId
	 * @return
	 */
	@Override
	public boolean deleteTeam_Project(int teamId) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM ECollaborationWeb.team_project WHERE teamId =?;";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamId);
			if (preparedStatement.executeUpdate() >= 1){
				return true;
			}
			return false;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}

	/**
	 * 删除关系
	 *
	 * @param projectId
	 * @return
	 */
	@Override
	public boolean deleteProject_Team(int projectId) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM ECollaborationWeb.team_project WHERE projectId =?;";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, projectId);
			if (preparedStatement.executeUpdate() >= 1){
				return true;
			}
			return false;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}

	/**
	 * 获取学生在团队身份
	 *
	 * @param teamId
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer getleadFlagByTeamIdStudentId(int teamId, int studentId) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = "SELECT leaderFlag from student_team where student_team.teamId = ? and studentId = ?";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamId);
			preparedStatement.setInt(2, studentId);
//			preparedStatement.setInt(3, task);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				return resultSet.getInt(1);
			}
			return null;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取团队创建人id
	 *
	 * @param teamId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer getCreatorIdByTeamId(int teamId) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Integer> list = new ArrayList<>();
		String sql = "SELECT creatorId FROM ecollaborationweb.team WHERE id = ?";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}

			return null;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 根据学生id，项目id，获取团队id
	 *
	 * @param studentId
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer getTeamIdByStudentIdProjectId(int studentId, int projectId) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Integer> list = new ArrayList<>();
		String sql = "SELECT st.teamId FROM ecollaborationweb.team_project as tp,ecollaborationweb.student_team as st" +
				" WHERE tp.teamId = st.teamId AND st.studentId = ? AND tp.projectId = ? " +
				"GROUP BY st.teamId";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, studentId);
			preparedStatement.setInt(2, projectId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}

			return null;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取申请进入团队的人的id
	 *
	 * @param teamId
	 * @return
	 * @throws Exception
	 */
	@Override
	public ArrayList<Integer> getApplyStudentIdByTeamId(int teamId) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Integer> list = new ArrayList<>();
		String sql = "SELECT studentId FROM ecollaborationweb.student_team WHERE teamId = ? AND leaderFlag = ?";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamId);
			preparedStatement.setInt(2, 0);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getInt("studentId"));
			}
			return list;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 接受某个团队的申请，将applyFlag从1置0
	 * 同时，将项目的teamNumber加1
	 * @param teamId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean acceptTeamApplyToProject(int teamId, int projectId) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ProjectDAO projectDAO = new ProjectDAOImpl();
		ProjectBean projectBean = projectDAO.getProjectInfo(projectId);
		if ( projectBean.getTeamMax() <= projectBean.getTeamNumber() )
			return false;
		String sql1 = "UPDATE EcollaborationWeb.team_project " +
				"SET applyFlag = 0 WHERE teamId = ? AND projectId = ?; ";
		String sql2 =" UPDATE ECollaborationWeb.project SET teamNumber = (teamNumber +1) WHERE Id = ?;";

		try {
			conn = DBUtils.getConnection();

			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql1);
			ps.setInt(1, teamId);
			ps.setInt(2, projectId);
			ps.executeUpdate();
			ps = conn.prepareStatement(sql2);
			ps.setInt(1, projectId);
			ps.executeUpdate();

			conn.commit();
			return true;
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
			throw e;
		}finally {
			DBUtils.close(null, ps, conn);
		}
	}

	/**
	 * 同意某人加入团队
	 *
	 * @param teamId    ，studentId
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean acceptApplyJoinByTeamIdStudentId(int teamId, int studentId, int leaderFlag) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE ecollaborationweb.student_team " +
				"SET leaderFlag = ? WHERE teamId = ? AND studentId = ?;";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, leaderFlag);
			ps.setInt(2, teamId);
			ps.setInt(3, studentId);
			int flag = ps.executeUpdate();
			if (flag ==1 )
				return true;
			else
				return false;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(null, ps, conn);
		}
	}
}

