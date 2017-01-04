package DAO.team_project;

import DAO.com.smallTools.ComGetSingleBy2DAO;
import DAO.com.smallTools.ComGetSingleBy2DAOImpl;
import DAO.com.util.db.DBUtils;
import bean.domain.TeamBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by geyao on 2016/12/25.
 */
public class TeamProjectDAOImpl implements Team_ProjectDAO {
	/**
	 * 添加评价，通过team_projectId, taskId, int access
	 *
	 * @param team_projectId
	 * @param taskId
	 * @param access
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean addAccess(int team_projectId, int taskId, int access) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "INSERT INTO ECollaborationWeb.team_project_access " +
				" (team_project_id, taskId, access) VALUES (?,?,?) ";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, team_projectId);
			preparedStatement.setInt(2, taskId);
			preparedStatement.setInt(3, access);
			int flag = preparedStatement.executeUpdate();
			if (flag == 1){
				return true;
			}else
				return false;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 获取team_projectId，通过teamId，projectId
	 *
	 * @param teamId
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer getIdByTeamIdProjectId(int teamId, int projectId) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id FROM ECollaborationWeb.team_project WHERE  " +
				" teamId = ? AND  projectId = ?; ";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teamId);
			preparedStatement.setInt(2, projectId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				int id = resultSet.getInt(1);
				return id;
			}else
				return null;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 更新评价，通过teamId，projectId，taskID，access
	 *
	 * @param team_projectId
	 * @param taskId
	 * @param access
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean updateAccess(int team_projectId, int taskId, int access) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
		String sql = "UPDATE ECollaborationWeb.team_project_access " +
				"SET  taskId = ?, access =? WHERE team_project_id = ?";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, taskId);
			preparedStatement.setInt(2, access);
			preparedStatement.setInt(3, team_projectId);
			int flag = preparedStatement.executeUpdate();
			if (flag == 1){
				return true;
			}else
				return false;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}

	/**
	 * 获取评价，通过teamID，projectId，taskId
	 *
	 * @param team_projectId
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer getAccess(int team_projectId, int taskId) throws Exception {
		ComGetSingleBy2DAO<Integer, Integer, Integer> comGetSingleBy2DAO = new ComGetSingleBy2DAOImpl<>();
		return comGetSingleBy2DAO.getListAfromBbyC("access", "team_project_id", team_projectId,
				"taskId", taskId, "team_project_access");
	}

	/**
	 * 获取
	 *
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<TeamBean> getTeamBeanByProjectId(int projectId) throws SQLException {
		ArrayList<TeamBean> arrayList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM team_project, team WHERE team.id = team_project.id " +
				" AND team_project.projectId = ? ORDER BY id;";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, projectId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				TeamBean teamBean = new TeamBean();
				teamBean.setId( resultSet.getInt("id"));
				teamBean.setTeamName( resultSet.getString("teamName"));
				teamBean.setCreatorId( resultSet.getInt("creatorId"));
				teamBean.setCreateDate( resultSet.getString("createDate"));
				teamBean.setMemberMax( resultSet.getInt("memberMax"));
				teamBean.setDescription( resultSet.getString("description"));
				arrayList.add(teamBean);
			}
			return arrayList;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}
}
