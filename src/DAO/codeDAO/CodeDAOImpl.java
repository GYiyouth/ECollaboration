package DAO.codeDAO;

import DAO.com.smallTools.ComGetListBy2DAO;
import DAO.com.smallTools.ComGetListBy2DAOImpl;
import DAO.com.smallTools.ComGetListValueDAO;
import DAO.com.smallTools.ComGetListValueDAOImpl;
import DAO.com.util.db.DBUtils;
import bean.domain.CodeBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
				"codeRows, createDate, deadDate, downLoadTimes,  studentId, projectId, teamId, path, codeName)" +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(   1,  codeBean.getRow());
			preparedStatement.setString(2,  codeBean.getCreateDate());
			preparedStatement.setString(3,  codeBean.getDeadDate());
			preparedStatement.setInt(   4,  codeBean.getDownLoadTimes());
//			preparedStatement.setInt(   5,  codeBean.getScore());
			preparedStatement.setInt(   5,  codeBean.getStudentId());
			preparedStatement.setInt(   6,  codeBean.getProjectId());
			preparedStatement.setInt(   7,  codeBean.getTeamId());
			preparedStatement.setString(8,  codeBean.getPath());
			preparedStatement.setString(9,  codeBean.getCodeName());
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
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, codeId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				CodeBean codeBean = new CodeBean();
				codeBean.setRow(            resultSet.getInt("codeRows"));
				codeBean.setTeamId(         resultSet.getInt("teamId"));
//				codeBean.setScore(          resultSet.getInt("score"));
				codeBean.setProjectId(      resultSet.getInt("projectId"));
				codeBean.setId(             resultSet.getInt("id"));
				codeBean.setStudentId(      resultSet.getInt("studentId"));
				codeBean.setPath(           resultSet.getString("path"));
				codeBean.setDownLoadTimes(  resultSet.getInt("downLoadTimes"));
				codeBean.setCreateDate(     resultSet.getString("createDate"));
				codeBean.setDeadDate(       resultSet.getString("deadDate"));
				codeBean.setCodeName((      resultSet.getString("codeName")));
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
				"downLoadTimes = ?, studentId = ?," +
				"projectId = ?, teamId = ?, path = ?, codeName = ? WHERE id = ?;";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(   1, codeBean.getRow());
			preparedStatement.setString(2, codeBean.getCreateDate());
			preparedStatement.setString(3, codeBean.getDeadDate());
			preparedStatement.setInt(   4, codeBean.getDownLoadTimes());
//			preparedStatement.setInt(   5, codeBean.getScore());
			preparedStatement.setInt(   5, codeBean.getStudentId());
			preparedStatement.setInt(   6, codeBean.getProjectId());
			preparedStatement.setInt(   7, codeBean.getTeamId());
			preparedStatement.setString(8, codeBean.getPath());
			preparedStatement.setString(9, codeBean.getCodeName());
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
			connection = DBUtils.getConnection();
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
	 * 获取某个学生在某个项目所有的代码行数
	 *
	 * @param studentId
	 * @param projectId
	 * @return
	 */
	@Override
	public Integer getCodeRowsSum(int studentId, int projectId) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT SUM(codeRows) FROM ecollaborationweb.code WHERE studentId = ? and projectId = ?";

		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, studentId);
			ps.setInt(2, projectId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.close(rs, ps, conn);
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
		ArrayList<Integer> list = new ArrayList<>();
		ComGetListValueDAO comGetListValueDAO = new ComGetListValueDAOImpl<Integer, Integer>();
		try {
			list = comGetListValueDAO.getListAfromBbyC("id", "studentId", studentId, "code");
			return list;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
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
		ArrayList<Integer> list = null;
		ComGetListValueDAO comGetListValueDAO = new ComGetListValueDAOImpl<Integer, Integer>();
		try {
			list = comGetListValueDAO.getListAfromBbyC("id", "projectId", projectId, "code");
			return list;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
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
		ArrayList<Integer> list = null;
		ComGetListValueDAO comGetListValueDAO = new ComGetListValueDAOImpl<Integer, Integer>();
		try {
			list = comGetListValueDAO.getListAfromBbyC("id", "teamId", teamId, "code");
			return list;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
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
		ArrayList<Integer> list = null;
		ComGetListBy2DAO comGetListBy2DAO = new ComGetListBy2DAOImpl<Integer, Integer, Integer>();
		try {
			list = comGetListBy2DAO.getListAfromBbyC("id", "studentId", studentId, "projectId", projectId, "code");
			return list;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
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
		ArrayList<Integer> list = null;
		ComGetListBy2DAO comGetListBy2DAO = new ComGetListBy2DAOImpl<Integer, Integer, Integer>();
		try {
			list = comGetListBy2DAO.getListAfromBbyC("id", "studentId", studentId, "teamId", teamId, "code");
			return list;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
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
		ArrayList<Integer> list = null;
		ComGetListBy2DAO comGetListBy2DAO = new ComGetListBy2DAOImpl<Integer, Integer, Integer>();
		try {
			list = comGetListBy2DAO.getListAfromBbyC("id", "projectId", projectId, "teamId", teamId, "code");
			return list;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id FROM ECollaborationWeb.code WHERE projectId = ? AND teamId = ? AND  studentId = ? ;";
		ArrayList<Integer> arrayList = new ArrayList<>();
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, projectId);
			preparedStatement.setInt(2, teamId);
			preparedStatement.setInt(3, studentId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				arrayList.add(resultSet.getInt(1));
			}
			if (arrayList.size() > 0){
				return arrayList;
			}else
				return null;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取一个团队在一个项目下的codeBean列表
	 * @param projectId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public HashMap<Integer, CodeBean> getCodeBeans(int projectId, int teamId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql1 = "SELECT * FROM code, student_team WHERE " +
				" code.teamId = ? AND code.projectId = ? AND " +
				" code.studentId = student_team.studentId ORDER BY " +
				" leaderFlag DESC, studentId ;";
		String sql2 = "";
		String sql3 = "";
		HashMap<Integer, CodeBean> hashMap = new HashMap<>();
		try {
			connection = DBUtils.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.setInt(1, teamId);
			preparedStatement.setInt(2, projectId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				CodeBean codeBean = new CodeBean();
				codeBean.setId(resultSet.getInt("id"));
				codeBean.setCodeName(resultSet.getString("fileName"));
				codeBean.setRow(resultSet.getInt("codeRows"));
				codeBean.setCreateDate(resultSet.getString("createDate"));
				codeBean.setDeadDate(resultSet.getString("deadDate"));
				codeBean.setDownLoadTimes(resultSet.getInt("downLoadTimes"));
				codeBean.setStudentId(resultSet.getInt("studentId"));
				codeBean.setProjectId(resultSet.getInt("projectId"));
				codeBean.setTeamId(resultSet.getInt("teamId"));
				codeBean.setPath(resultSet.getString("path"));
				hashMap.put(codeBean.getId(), codeBean);
			}
			connection.commit();
			return hashMap;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}
}
