package DAO.teacherDAO;

import bean.domain.*;

import java.sql.SQLException;

public interface TeacherDAO {

	/**
	 * 添加老师，返回老师id
	 * @param teacherBean
	 * @return int
	 * @throws SQLException
	 */
	public int addTeacher(TeacherBean teacherBean) throws SQLException;

	/**
	 * 根据id寻找老师，返回Teacher
	 * @param teacherId
	 * @return TeacherBean
	 * @throws SQLException
	 */
	public TeacherBean getTeacherInfo(int teacherId) throws SQLException;

	/**
	 * 修改老师信息
	 * @param teacherBean
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(TeacherBean teacherBean) throws SQLException;

	/**
	 * 删除老师
	 * @param teacherId
	 * @return TeacherBean
	 * @throws SQLException
	 */
	public TeacherBean deleteById(int teacherId) throws SQLException;
	
	/**
	 * 通过id获取老师信息
	 * @param teacherId
	 * @return TeacherBean
	 * @throws SQLException
	 */
	public TeacherBean getInfoById(int teacherId) throws SQLException;

}
