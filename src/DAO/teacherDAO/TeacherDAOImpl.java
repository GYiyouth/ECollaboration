package DAO.teacherDAO;

import DAO.com.util.db.DBUtils;
import bean.domain.TeacherBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

		boolean flag = true;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into ecollaborationweb.teacher (id,homePageUrl,needStudentsFlag)values(?,?,?);";
		try {
			conn = DBUtils.getConnetction();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, teacherBean.getId());
			ps.setString(2, teacherBean.getHomePageUrl());
			ps.setInt(3, teacherBean.getNeedStudentsFlag());
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
			connection = DBUtils.getConnetction();
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
			conn = DBUtils.getConnetction();
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
			connection = DBUtils.getConnetction();
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
			connection = DBUtils.getConnetction();
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
			connection = DBUtils.getConnetction();
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
			connection = DBUtils.getConnetction();
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
}

