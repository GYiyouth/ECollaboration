package DAO.teacherDAO;

import bean.domain.*;

import java.sql.SQLException;

public interface TeacherDAO {

	/**
	 * 添加老师，返回老师id
	 * @param teacher
	 * @return int
	 * @throws SQLException
	 */
	public int addTeacher(Teacher teacher) throws SQLException;

	/**
	 * 根据id寻找老师，返回Teacher
	 * @param teacherId
	 * @return Teacher
	 * @throws SQLException
	 */
	public Teacher getTeacherInfo(int teacherId) throws SQLException;

	/**
	 * 修改老师信息
	 * @param teacher
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(Teacher teacher) throws SQLException;

	/**
	 * 删除老师
	 * @param teacherId
	 * @return Teacher
	 * @throws SQLException
	 */
	public Teacher deleteById(int teacherId) throws SQLException;
	
	/**
	 * 通过id获取老师信息
	 * @param teacherId
	 * @return Teacher
	 * @throws SQLException
	 */
	public Teacher getInfoById(int teacherId) throws SQLException;

}
