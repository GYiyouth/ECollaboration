package DAO.ECFileDAO;

import DAO.com.util.db.DBUtils;
import bean.domain.ECFileBean;
import smallTools.Time;
import smallTools.TimeImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 注意，改了一下，photo不再返回路径，而是把头像存储在数据库中
 * 接口，实现，都还没改
 * Created by geyao on 2016/11/8.
 */
public class ECFileDAOImpl implements ECFileDAO {
	/**
	 * 添加文件，返回id
	 *
	 * @param ecFileBean
	 * @return id or null
	 * @throws SQLException
	 */
	@Override
	public Integer addFile(ECFileBean ecFileBean) throws SQLException {
		if (ecFileBean == null )
			return null;
		else
			if (getFileInfo( ecFileBean.getId() ) != null)                              //文件已经存在
				return null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "insert into ECollaborationWeb.ecfile(fileName, createDate, deadDate, downLoadTimes," +
				" creatorId, path) values(?,?,?,?,?,?);";
		Time time = new TimeImpl();
		ecFileBean.setCreateDate(time.getDateStr());
		try{
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,  ecFileBean.getFileName());
			preparedStatement.setString(2,  ecFileBean.getCreateDate());
			preparedStatement.setString(3,  ecFileBean.getDeadDate());
			preparedStatement.setInt(   4,  ecFileBean.getDownLoadTimes());
			preparedStatement.setInt(   5,  ecFileBean.getCreatorId());
			preparedStatement.setString(6,  ecFileBean.getPath());
			int doneFlag = preparedStatement.executeUpdate();
			if (doneFlag == 0) {
				throw new SQLException("添加文件进数据库失败");
			}else {
				String sql2 = "select LAST_INSERT_ID();";                           //返回id
				preparedStatement = connection.prepareStatement(sql2);
				preparedStatement.executeQuery();
				resultSet = preparedStatement.getResultSet();
				resultSet.next();
				return resultSet.getInt(1);

			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 添加头像，返回file id,通过用户id
	 *
	 * @param ecFileBean
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
//	@Override
//	public Integer addPhoto(ECFileBean ecFileBean, Integer userId) throws SQLException {
//		if (ecFileBean == null)
//			return null;
//		try {                                                                   // 删除旧头像
//			if (getPhotoId(userId) != null){
//				deleteFile(getPhotoId(userId));
//			}
//		}catch (SQLException e){
//			e.printStackTrace();
//			throw e;
//		}
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		String sql = "insert into ECollaborationWeb.ecfile(fileName, createDate, deadDate, downLoadTimes," +
//				"priority, creatorId, path) values(?,?,?,?,?,?,?);";
//		try {
//			connection = DBUtils.getConnection();
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1,  ecFileBean.getFileName());
//			preparedStatement.setString(2,  ecFileBean.getCreateDate());
//			preparedStatement.setString(3,  ecFileBean.getDeadDate());
//			preparedStatement.setInt(   4,  ecFileBean.getDownLoadTimes());
//			preparedStatement.setInt(   5,  4);
//			preparedStatement.setInt(   6,  userId);
//			preparedStatement.setString(7,  ecFileBean.getPath());
//			int row = preparedStatement.executeUpdate();
//			if (row != 1){
//				throw new SQLException("添加头像出错");
//			}else {
//				String sql2 = "select LAST_INSERT_ID();";                           //返回id
//				preparedStatement = connection.prepareStatement(sql2);
//				preparedStatement.executeQuery();
//				resultSet = preparedStatement.getResultSet();
//				resultSet.next();
//				return resultSet.getInt(1);
//
//			}
//		}catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		}finally {
//			DBUtils.close(resultSet, preparedStatement, connection);
//		}
//	}

	/**
	 * 获取头像id，通过用户id
	 *
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
//	@Override
//	public Integer getPhotoId(Integer userId) throws SQLException {
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		String sql = "select id from ECollaborationWeb.ecfile where creatorId = ? and priority = 4";
//		try {
//			connection = DBUtils.getConnection();
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt(1, userId);
//			resultSet = preparedStatement.executeQuery();
//			resultSet.next();
//			return resultSet.getInt(1);
//		}catch (SQLException e){
//			e.printStackTrace();
//			throw e;
//		}finally {
//			DBUtils.close(resultSet, preparedStatement, connection);
//		}
//	}

	/**
	 * 获取文件，通过文件id
	 *
	 * @param fileId
	 * @return ECFileBean or null
	 * @throws SQLException
	 */
	@Override
	public ECFileBean getFileInfo(Integer fileId) throws SQLException {
		if (fileId == null)
			return null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select * from ECollaborationWeb.ecfile where id = ?;";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, fileId);
			preparedStatement.executeQuery();
			resultSet = preparedStatement.getResultSet();
			if (resultSet.next()){
				ECFileBean ecFileBean = new ECFileBean();
				ecFileBean.setCreateDate(   resultSet.getString("createDate"));
				ecFileBean.setDeadDate(     resultSet.getString("deadDate"));
				ecFileBean.setDownLoadTimes(resultSet.getInt("downLoadTimes"));
				ecFileBean.setId(           resultSet.getInt("id"));
				ecFileBean.setFileName(     resultSet.getString("fileName"));
				ecFileBean.setPath(         resultSet.getString("path"));
//				ecFileBean.setPriority(     resultSet.getInt("priority"));
				ecFileBean.setCreatorId(     resultSet.getInt("creatorId"));
				return ecFileBean;
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
	 * 某个学生该项目的文件总数
	 *
	 * @param studentId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Integer getFileSum(int studentId, int projectId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select count(*) from student_team_project_file where creatorId = ? and projectId = ?";

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
	 * 修改文件
	 *
	 * @param ecFileBean
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean updateFile(ECFileBean ecFileBean) throws SQLException {
		if (ecFileBean == null)
			return false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "update ECollaborationWeb.ecfile set fileName = ?, createDate = ?, deadDate = ?, downLoadTimes =?," +
				" creatorId = ?, path = ? where id = ?;";
		try {
			connection =DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,  ecFileBean.getFileName());
			preparedStatement.setString(  2,  ecFileBean.getCreateDate());
			preparedStatement.setString(  3,  ecFileBean.getDeadDate());
			preparedStatement.setInt(   4,  ecFileBean.getDownLoadTimes());
//			preparedStatement.setInt(   5,  ecFileBean.getPriority());
			preparedStatement.setInt(   5,  ecFileBean.getCreatorId());
			preparedStatement.setString(6,  ecFileBean.getPath());
			preparedStatement.setInt(   7,  ecFileBean.getId());
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
	public ECFileBean deleteFile(Integer fileId) throws SQLException {
		ECFileBean ecFileBean = getFileInfo(fileId);
		if (ecFileBean == null)
			return ecFileBean;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM ECollaborationWeb.ecfile WHERE id = ? ;";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, fileId);
			int row = preparedStatement.executeUpdate();
			if (row == 1)
				return ecFileBean;
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
	 * 获取头像id，通过用户id
	 * ecfile表中，根据userId和priority = 4来查找
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
//	@Override
//	public getPhotoId(Integer userId) throws SQLException {
//		if (userId == null)
//			return null;
//		Connection connection =null;
//		PreparedStatement preparedStatement = null;
//		String sql = "select id from ECollaborationWeb.ecfile where creatorId = ? and priority = 4";
//		ResultSet resultSet =null;
//		try {
//			connection = DBUtils.getConnection();
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt( 1, userId);
//			resultSet = preparedStatement.executeQuery();
//			if (!resultSet.next())
//				return null;
//			else
//				return resultSet.getInt("id");
//		}catch (SQLException e){
//			e.printStackTrace();
//			throw e;
//		}finally {
//			DBUtils.close(resultSet, preparedStatement, connection);
//		}
//	}

	/**
	 * 获取文件id列表，通过学生id
	 *
	 * @param creatorId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getFileIdListByCreatorId(Integer creatorId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id FROM ecollaborationweb.ecfile WHERE creatorId = ?";
		ArrayList<Integer> fileIdList = new ArrayList<>();
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(   1, creatorId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				Integer i = resultSet.getInt(1);
				fileIdList.add(i);
			}
			return fileIdList;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT fileid FROM ecollaborationweb.student_team_project_file AS spf, " +
				"ecollaborationweb.teacher_project AS tp " +
				"WHERE spf.projectId = tp.projectId AND tp.teacherId = ? ;";
		ArrayList<Integer> fileIdList = new ArrayList<>();
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(   1, teacherId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				Integer i = resultSet.getInt(1);
				fileIdList.add(i);
			}
			return fileIdList;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT fileId FROM ecollaborationweb.student_team_project_file WHERE projectId = ?";
		ArrayList<Integer> fileIdList = new ArrayList<>();
		try{
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(   1,  projectId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				fileIdList.add(resultSet.getInt(1));
			}
			return fileIdList;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql =
				"SELECT fileId " +
				"FROM ecollaborationweb.student_team_project_file AS spf," +
					"ecollaborationweb.team_project AS tp " +
				"WHERE spf.projectId = tp.projectId " +
					"AND spf.teamId = ? " +
					"AND tp.teamId = ?";
		ArrayList<Integer> fileIdList = new ArrayList<>();
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(   1, teamId);
			preparedStatement.setInt(   2, teamId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				fileIdList.add(resultSet.getInt(1));
			}
			return fileIdList;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 获取文件id列表，通过创建者id，项目id
	 *
	 * @param creatorId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Integer> getFileIdListByCreatorIdProjectId(Integer creatorId, Integer projectId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql =
				"SELECT fileId FROM ecollaborationweb.student_team_project_file " +
				"WHERE projectId = ? " +
				"AND creatorId = ?";
		ArrayList<Integer> fileIdList = new ArrayList<>();
		try{
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(   1,  projectId);
			preparedStatement.setInt(   2,  creatorId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				fileIdList.add(resultSet.getInt(1));
			}
			return fileIdList;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql =
				"SELECT fileId " +
				"FROM ecollaborationweb.student_team_project_file AS spf," +
				"ecollaborationweb.teacher_project AS tp " +
				"WHERE spf.projectId = ? " +
				"AND tp.projectId = ? " +
				"AND  tp.teacherId = ?;";
		ArrayList<Integer> fileIdList = new ArrayList<>();
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(   1, projectId);
			preparedStatement.setInt(   2, projectId);
			preparedStatement.setInt(   3, teacherId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				fileIdList.add(resultSet.getInt(1));
			}
			return fileIdList;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT fileId " +
				"FROM ecollaborationweb.student_team_project_file " +
				"WHERE teamId = ? AND projectId = ? ;";
		ArrayList<Integer> fileList = new ArrayList<>();
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1  ,teamId);
			preparedStatement.setInt(2  ,projectId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				fileList.add(resultSet.getInt(1));
			}
			return fileList ;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}
}

