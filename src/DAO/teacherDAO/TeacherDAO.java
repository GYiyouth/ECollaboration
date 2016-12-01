package DAO.teacherDAO;

import bean.domain.*;

import java.sql.SQLException;

public interface TeacherDAO {

	/**
	 * 添加老师
	 * @param teacherBean
	 * @return Integer
	 * @throws SQLException
	 */
	public Integer addTeacher(TeacherBean teacherBean) throws SQLException;
//
//	/**
//	 * ����idѰ����ʦ������Teacher
//	 * @param teacherId
//	 * @return TeacherBean
//	 * @throws SQLException
//	 */
//	public TeacherBean getTeacherInfo(Integer teacherId) throws SQLException;

	/**
	 * 更新老师信息
	 * @param teacherBean
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(TeacherBean teacherBean) throws SQLException;

	public boolean updateInfoByTeacher(TeacherBean teacherBean) throws SQLException;

	/**
	 * 删除老师信息
	 * @param teacherId
	 * @return TeacherBean
	 * @throws SQLException
	 */
	public TeacherBean deleteById(Integer teacherId) throws SQLException;
	
	/**
	 * 获取TeacherBean，通过teacherId
	 * @param teacherId
	 * @return TeacherBean
	 * @throws SQLException
	 */
	public TeacherBean getInfoById(Integer teacherId) throws SQLException;

}
