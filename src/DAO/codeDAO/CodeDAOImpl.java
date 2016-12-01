package DAO.codeDAO;

import DAO.com.util.db.DBUtils;
import bean.domain.CodeBean;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by geyao on 2016/11/19.
 */
public class CodeDAOImpl implements CodeDAO {
	/**
	 * 添加文件，返回id
	 *
	 * @param codeBean
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Integer add(CodeBean codeBean) throws SQLException {
		Integer codeId = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "INSERT INTO ECollaborationWeb.Code(" +
				"codeRows, createDate, deadDate, downLoadTimes, score, studentId, projectId, teamId, path)" +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(   1,  codeBean.getRow());
			preparedStatement.setString(2,  codeBean.getCreateDate());
			preparedStatement.setString(3,  codeBean.getDeadDate());
			preparedStatement.setInt(   4,  codeBean.getDownLoadTimes());
			preparedStatement.setInt(   5,  codeBean.getScore());
			preparedStatement.setInt(   6,  codeBean.getStudentId());
			preparedStatement.setInt(   7,  codeBean.getProjectId());
			preparedStatement.setInt(   8,  codeBean.getTeamId());
			preparedStatement.setString(9,  codeBean.getPath());
			int flag = preparedStatement.executeUpdate();

			if (flag == 1){
				//添加成功，返回id
				sql = "SELECT LAST_INSERT_ID();";
				preparedStatement = connection.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				if ( resultSet.next() ){
					codeId = resultSet.getInt(1);
					return codeId;
				}
			}
			throw new SQLException("添加代码失败");
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 获取文件，通过id
	 *
	 * @param codeId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public CodeBean getCodeInfo(int codeId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM ECollaborationWeb.code WHERE id = ?";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, codeId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				CodeBean codeBean = new CodeBean();
				codeBean.setRow(            resultSet.getInt("codeRows"));
				codeBean.setTeamId(         resultSet.getInt("teamId"));
				codeBean.setScore(          resultSet.getInt("score"));
				codeBean.setProjectId(      resultSet.getInt("projectId"));
				codeBean.setId(             resultSet.getInt("id"));
				codeBean.setStudentId(      resultSet.getInt("studentId"));
				codeBean.setPath(           resultSet.getString("path"));
				codeBean.setDownLoadTimes(  resultSet.getInt("downLoadTimes"));
				codeBean.setCreateDate(     resultSet.getString("createDate"));
				codeBean.setDeadDate(       resultSet.getString("deadDate"));
				return codeBean;
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
	 * 修改文件，通过CodeBean,id
	 *
	 * @param codeId
	 * @param codeBean
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean updateCode(CodeBean codeBean, int codeId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "UPDATE ECollaborationWeb.code " +
				"set codeRows = ?, createDate = ?, deadDate = ?," +
				"downLoadTimes = ?, score = ?, studentId = ?," +
				"projectId = ?, teamId = ?, path = ? WHERE id = ?;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(   1, codeBean.getRow());
			preparedStatement.setString(2, codeBean.getCreateDate());
			preparedStatement.setString(3, codeBean.getDeadDate());
			preparedStatement.setInt(   4, codeBean.getDownLoadTimes());
			preparedStatement.setInt(   5, codeBean.getScore());
			preparedStatement.setInt(   6, codeBean.getStudentId());
			preparedStatement.setInt(   7, codeBean.getProjectId());
			preparedStatement.setInt(   8, codeBean.getTeamId());
			preparedStatement.setString(9, codeBean.getPath());
			preparedStatement.setInt(   10, codeId);
			int flag = preparedStatement.executeUpdate();
			if (flag == 1 ){
				return true;
			}
			return false;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 删除文件，通过id
	 * 不存在，则返回null
	 * @param codeId
	 * @return CodeBean 或者 null
	 * @throws SQLException
	 */
	@Override
	public CodeBean deleteCode(int codeId) throws SQLException {
		CodeBean codeBean = getCodeInfo(codeId);
		if (codeBean == null){
			return codeBean;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "DELETE  FROM ECollaborationWeb.code WHERE id = ?";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, codeId);
			int flag = preparedStatement.executeUpdate();
			return codeBean;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}

	/**
	 * 获取代码id列表，通过学生id
	 *
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getCodeIdListByStudentId(int studentId) throws SQLException {
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
	public ArrayList<Integer> getCodeIdListByProjectId(int projectId) throws SQLException {
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
	public ArrayList<Integer> getCodeIdListByTeamId(int teamId) throws SQLException {
		return null;
	}

	/**
	 * 获取代码id列表，通过学生id，项目id
	 *
	 * @param studentId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getCodeIdListByStudentIdProjectId(int studentId, int projectId) throws SQLException {
		return null;
	}

	/**
	 * 获取代码id列表，通过学生id，团队id
	 *
	 * @param studentId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getCodeIdListByStudentIdTeamId(int studentId, int teamId) throws SQLException {
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
	public ArrayList<Integer> getCodeIdListByProjectIdTeamId(int projectId, int teamId) throws SQLException {
		return null;
	}

	/**
	 * 获取代码id列表，通过项目id，团队id，学生id
	 *
	 * @param projectId
	 * @param teamId
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getCodeIdListByProjectIdTeamIdStudentId(int projectId, int teamId, int studentId) throws SQLException {
		return null;
	}
}
