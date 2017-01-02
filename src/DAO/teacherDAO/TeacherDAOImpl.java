package DAO.teacherDAO;

import DAO.com.util.db.DBUtils;
import bean.domain.TeacherBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by geyao on 2016/11/9.
 */
public class TeacherDAOImpl implements TeacherDAO {
	/**
	 * 添加老师
	 *
	 * @param teacherBean
	 * @return boolean
	 * @throws SQLException
	 */
	@Override
	public boolean addTeacher(TeacherBean teacherBean) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlU = "INSERT INTO ECollaborationWeb.user ( " +
				" schoolId, name, sex, role, email, phoneNumber, " +
				" logName, passWord, createDate, " +
				" lastLogTime, activeBefore, newsFlag) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
		String sqlT = "insert into ECollaborationWeb.teacher (id,homePageUrl,needStudentsFlag) " +
				"values(?,?,?)";
		try {
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sqlU, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,teacherBean.getSchoolId());
			ps.setString(2,teacherBean.getName());
			ps.setInt(3,teacherBean.getSex());
			ps.setInt(4,teacherBean.getRole());
			ps.setString(5,teacherBean.getEmail());
			ps.setString(6,teacherBean.getPhoneNumber());
			ps.setString(7,teacherBean.getLogName());
			ps.setString(8,teacherBean.getPassWord());
			ps.setString(9,teacherBean.getCreateDate());
			ps.setString(10,teacherBean.getLastLogTime());
			ps.setString(11,teacherBean.getActiveBefore());
			ps.setInt(12,teacherBean.getNewFlag());

			int z  = ps.executeUpdate();
			if(z!=0){
				ResultSet ids = ps.getGeneratedKeys();
				if(ids.next())
					System.out.println("插入user表成功");
				teacherBean.setId(ids.getInt(1));
			}else{
				System.out.println("插入teacher表失败");
				return false;
			}
			ps = conn.prepareStatement(sqlT);
			ps.setInt(1, teacherBean.getId());
			ps.setString(2, teacherBean.getHomePageUrl());
			ps.setInt(3, teacherBean.getNeedStudentsFlag());
			int i = ps.executeUpdate();
			if (i == 0) {
				System.out.println("插入teacher表失败");
				return false;
			}else{
				System.out.println("插入teacher表成功");
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("teacherDAO出错！");
			conn.rollback();
			return false;
		} finally {
			DBUtils.close(null, ps, conn);
		}

		return true;
	}


	/**
	 * 修改老师信息
	 *
	 * @param teacherBean
	 * @return boolean
	 * @throws SQLException
	 */
	@Override
	public boolean updateInfo(TeacherBean teacherBean) throws SQLException {
		/*if (teacherBean == null)
			return false;*/
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE ecollaborationweb.teacher set homePageUrl = ?,needStudentsFlag= ? WHERE id = ?";
		try{
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,teacherBean.getHomePageUrl());
			preparedStatement.setInt(2,teacherBean.getNeedStudentsFlag());
			preparedStatement.setInt(3,teacherBean.getId());
			int row = preparedStatement.executeUpdate();
			if (row != 1)
				return false;
			else
				return true;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}

	/**
	 * 删除老师
	 *
	 * @param teacherId
	 * @return boolean
	 * @throws SQLException
	 */
	@Override
	public boolean deleteById(Integer teacherId) throws SQLException {
		boolean flag = true;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from ecollaborationweb.teacher where id=?";

		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, teacherId);
			int i = ps.executeUpdate();
			if (i == 0) {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(null, ps, conn);
		}

		return flag;
	}

	/**
	 * 通过id获取老师信息
	 *
	 * @param teacherId
	 * @return TeacherBean
	 * @throws SQLException
	 */
	@Override
	public TeacherBean getInfoById(Integer teacherId) throws SQLException {
		if (teacherId == null)
			return null;
		TeacherBean teacherBean = new TeacherBean();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select * from ECollaborationWeb.teacher WHERE id = ?";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teacherId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {


				teacherBean.setId(resultSet.getInt("id"));
				teacherBean.setHomePageUrl(resultSet.getString("homePageUrl"));
				teacherBean.setNeedStudentsFlag(resultSet.getInt("NeedStudentsFlag"));

				return teacherBean;
			}else return null;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 设定教师项目关系
	 *
	 * @param teacherId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean setTeacherProject(int teacherId, int projectId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO ECollaborationWeb.teacher_project (teacherId, projectId) " +
				" VALUES (?,?);";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teacherId);
			preparedStatement.setInt(2, projectId);
			int flag = preparedStatement.executeUpdate();
			if (flag >= 1 ){
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
	 * 删除关系，通过projectId
	 *
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean deleteProject_Teacher(int projectId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM ECollaborationWeb.teacher_project WHERE projectId = ?;";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, projectId);
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
	 * 删除关系，通过teacherId
	 *
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean deleteTeacher_Project(int teacherId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM ECollaborationWeb.teacher_project WHERE teacherId = ?;";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teacherId);
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
	 * 获取所有老师的信息，哈希表为id_name对
	 *
	 * @return
	 * @throws SQLException
	 */
	@Override
	public HashMap<Integer, String> getTeacherID_Name() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		HashMap<Integer, String>hashMap = new HashMap<>();
		String sql = "SELECT id, name FROM user;";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				hashMap.put(id, name);
			}
			return hashMap;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 获取所有老师的信息，返回Bean
	 *
	 * @return
	 * @throws SQLException
	 */
	@Override
	public HashMap<Integer, TeacherBean> getAllTeacher() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		HashMap<Integer, TeacherBean> result = new HashMap<>();
		String sql = " SELECT * FROM user, teacher WHERE user.id = teacher.id; ";
		try {
			TeacherBean teacherBean = new TeacherBean();
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				teacherBean.setId(resultSet.getInt("id"));
				teacherBean.setSchoolId(resultSet.getString("schoolId"));
				teacherBean.setName(resultSet.getString("name"));
				teacherBean.setSex(resultSet.getInt("sex"));
				teacherBean.setRole(resultSet.getInt("role"));
				teacherBean.setEmail(resultSet.getString("email"));
				teacherBean.setPhoneNumber(resultSet.getString("phoneNumber"));
				teacherBean.setLogName(resultSet.getString("logName"));
				teacherBean.setPhoneNumber(resultSet.getString("passWord"));
				teacherBean.setCreateDate(resultSet.getString("createDate"));
				teacherBean.setPhoto(resultSet.getString("photo"));
				teacherBean.setLastLogTime(resultSet.getString("lastLogTime"));
				teacherBean.setActiveBefore(resultSet.getString("activeBefore"));
				teacherBean.setNewFlag(resultSet.getInt("newsFlag"));
				teacherBean.setHomePageUrl(resultSet.getString("homePageUrl"));
				teacherBean.setNeedStudentsFlag(resultSet.getInt("needStudentsFlag"));
				result.put(teacherBean.getId(), teacherBean);
			}
			return result;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}
}

