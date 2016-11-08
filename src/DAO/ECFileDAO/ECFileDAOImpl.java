package DAO.ECFileDAO;

import DAO.com.util.db.DBUtils;
import bean.ECFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by geyao on 2016/11/8.
 */
public class ECFileDAOImpl implements ECFileDAO {
	/**
	 * 添加文件，返回id
	 *
	 * @param ecFile
	 * @return id or null
	 * @throws SQLException
	 */
	@Override
	public Integer addFile(ECFile ecFile) throws SQLException {
		if (ecFile == null )
			return null;
		else
			if (getFileInfo( ecFile.getId() ) != null)                              //文件已经存在
				return null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "insert into ECollaboration.ecfile(fileName, createDate, deadDate, downLoadTimes," +
				"priority, creatorId, path) values(?,?,?,?,?,?,?);";
		try{
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt(   1,  ecFile.getId());
			preparedStatement.setString(1,  ecFile.getFileName());
			preparedStatement.setDate(  2,  (java.sql.Date)ecFile.getCreateDate());
			preparedStatement.setDate(  3,  (java.sql.Date)ecFile.getDeadDate());
			preparedStatement.setInt(   4,  ecFile.getDownLoadTimes());
			preparedStatement.setInt(   5,  ecFile.getPriority());
			preparedStatement.setInt(   6,  ecFile.getCreatorId());
			preparedStatement.setString(7,  ecFile.getPath());
			int doneFlag = preparedStatement.executeUpdate();
			if (doneFlag == 0) {
				throw new SQLException("添加文件进数据库失败");
			}else {
				String sql2 = "select LAST_INSERT_ID();";                           //返回id
				preparedStatement = connection.prepareStatement(sql2);
				preparedStatement.executeQuery();
				resultSet = preparedStatement.getResultSet();
				resultSet.next();
				return resultSet.getInt("id");

			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 获取文件，通过文件id
	 *
	 * @param fileId
	 * @return ECFile or null
	 * @throws SQLException
	 */
	@Override
	public ECFile getFileInfo(Integer fileId) throws SQLException {
		if (fileId == null)
			return null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select * from ECollaboration.ecfile where id = ?;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, fileId);
			preparedStatement.executeQuery();
			resultSet = preparedStatement.getResultSet();
			if (resultSet.next()){
				ECFile ecFile = new ECFile();
				ecFile.setCreateDate(   resultSet.getDate("createDate"));
				ecFile.setDeadDate(     resultSet.getDate("deadDate"));
				ecFile.setDownLoadTimes(resultSet.getInt("downLoadTimes"));
				ecFile.setId(           resultSet.getInt("id"));
				ecFile.setFileName(     resultSet.getString("fileName"));
				ecFile.setPath(         resultSet.getString("path"));
				ecFile.setPriority(     resultSet.getInt("priority"));
				return ecFile;
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
	 * 修改文件
	 *
	 * @param ecFile
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean updateFile(ECFile ecFile) throws SQLException {
		if (ecFile == null)
			return false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "update ECollaboration.ecfile set fileName = ?, createDate = ?, deadDate = ?, downLoadTimes =?," +
				"priority = ?, creatorId = ?, path = ? where id = ?;";
		try {
			connection =DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,  ecFile.getFileName());
			preparedStatement.setDate(  2,  (java.sql.Date)ecFile.getCreateDate());
			preparedStatement.setDate(  3,  (java.sql.Date)ecFile.getDeadDate());
			preparedStatement.setInt(   4,  ecFile.getDownLoadTimes());
			preparedStatement.setInt(   5,  ecFile.getPriority());
			preparedStatement.setInt(   6,  ecFile.getCreatorId());
			preparedStatement.setString(7,  ecFile.getPath());
			preparedStatement.setInt(   8,  ecFile.getId());
			int row = preparedStatement.executeUpdate();
			if (row == 1)
				return true;
			else
				throw new SQLException("更新数据失败");
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}


	/**
	 * 删除文件，返回被删除的文件
	 * 这里性能比较差，因为要先查询
	 * @param fileId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ECFile deleteFile(Integer fileId) throws SQLException {
		ECFile ecFile = getFileInfo(fileId);
		if (ecFile == null)
			return ecFile;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM ECollaboration.ecfile WHERE id = ? ;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, fileId);
			int row = preparedStatement.executeUpdate();
			if (row == 1)
				return ecFile;
			else
				throw new SQLException("删除失败");
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}

	/**
	 * 获取文件id列表，通过学生id
	 *
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getFileIdListByStudentId(Integer studentId) throws SQLException {
		return null;
	}

	/**
	 * 获取文件id列表，通过教师id
	 *
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getFileIdListByTeacherId(Integer teacherId) throws SQLException {
		return null;
	}

	/**
	 * 获取文件id列表，通过项目id
	 *
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getFileIdListByProjectId(Integer projectId) throws SQLException {
		return null;
	}

	/**
	 * 获取文件id列表，通过团队id
	 *
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getFileIdListByTeamId(Integer teamId) throws SQLException {
		return null;
	}

	/**
	 * 获取文件id列表，通过学生id，项目id
	 *
	 * @param studentId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getFileIdListByStudentIdProjectId(Integer studentId, Integer projectId) throws SQLException {
		return null;
	}

	/**
	 * 获取文件列表，通过教师id，项目id
	 *
	 * @param teacherId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getFileIdListByTeacherIdProjectId(Integer teacherId, Integer projectId) throws SQLException {
		return null;
	}

	/**
	 * 获取文件列表，根据团队id，项目id
	 *
	 * @param teamId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getFileIdLIstByTeamIdProjectId(Integer teamId, Integer projectId) throws SQLException {
		return null;
	}
}

