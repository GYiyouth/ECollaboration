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
	 * 添加老师，返回老师id
	 *
	 * @param teacherBean
	 * @return Integer
	 * @throws SQLException
	 */
	@Override
	public Integer addTeacher(TeacherBean teacherBean) throws SQLException {
		return null;
	}

//	/**
//	 * 根据id寻找老师，返回Teacher
//	 *
//	 * @param teacherId
//	 * @return TeacherBean
//	 * @throws SQLException
//	 */
//	@Override
//	public TeacherBean getTeacherInfo(Integer teacherId) throws SQLException {
//		if (teacherId == null)
//			return null;
//		TeacherBean teacherBean = null;
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		String sql = "select * from ECollaborationWeb.teacher WHERE id = ?";
//		try {
//			connection = DBUtils.getConnetction();
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt(1, teacherId);
//			resultSet = preparedStatement.executeQuery();
//			if (!resultSet.next())
//				return null;
//			teacherBean.setHomePageUrl(     resultSet.getString("HomePageUrl"));
//			teacherBean.setId(              resultSet.getInt("id"));
//			teacherBean.setName(            resultSet.getString("name"));
//			teacherBean.setNeedStudentsFlag(resultSet.getInt("NeedStudentsFlag"));
//			teacherBean.setStaffid(         resultSet.getInt("staffId"));
//			return teacherBean;
//		}catch (SQLException e){
//			e.printStackTrace();
//			throw e;
//		}finally {
//			DBUtils.close(resultSet, preparedStatement, connection);
//		}
//	}

	/**
	 * 修改老师信息
	 *
	 * @param teacherBean
	 * @return boolean
	 * @throws SQLException
	 */
	@Override
	public boolean updateInfoByTeacher(TeacherBean teacherBean) throws SQLException {
		if (teacherBean == null)
			return false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE ECollaborationWeb set homePageUrl = ? WHERE id = ?";
		try{
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,  teacherBean.getHomePageUrl());
			preparedStatement.setInt(   2,  teacherBean.getId());
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
	 * 修改老师信息
	 *
	 * @param teacherBean
	 * @return boolean
	 * @throws SQLException
	 */
	@Override
	public boolean updateInfo(TeacherBean teacherBean) throws SQLException {
		return updateInfoByTeacher(teacherBean);
	}

	/**
	 * 删除老师
	 *
	 * @param teacherId
	 * @return TeacherBean
	 * @throws SQLException
	 */
	@Override
	public TeacherBean deleteById(Integer teacherId) throws SQLException {
		return null;
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
				teacherBean.setStaffid(resultSet.getString("staffId"));
				teacherBean.setName(resultSet.getString("name"));
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
}

