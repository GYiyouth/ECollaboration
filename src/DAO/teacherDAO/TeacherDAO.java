package DAO.teacherDAO;

import bean.domain.*;

import java.sql.SQLException;
import java.util.HashMap;

public interface TeacherDAO {

	/**
	 * 添加老师
	 * @param teacherBean
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean addTeacher(TeacherBean teacherBean) throws SQLException;


	/**
	 * 更新老师信息
	 * @param teacherBean
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(TeacherBean teacherBean) throws SQLException;


	/**
	 * 删除老师信息
	 * @param teacherId
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean deleteById(Integer teacherId) throws SQLException;
	
	/**
	 * 获取TeacherBean，通过teacherId
	 * @param teacherId
	 * @return TeacherBean
	 * @throws SQLException
	 */
	public TeacherBean getInfoById(Integer teacherId) throws SQLException;

	/**
	 * 设定教师项目关系
	 * @param teacherId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public boolean setTeacherProject(int teacherId, int projectId)throws SQLException;

	/**
	 * 删除关系，通过projectId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteProject_Teacher(int projectId) throws SQLException;

	/**
	 * 删除关系，通过teacherId
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteTeacher_Project(int teacherId) throws SQLException;

	/**
	 * 获取所有老师的信息，哈希表为id_name对
	 * @return
	 * @throws SQLException
	 */
	public HashMap<Integer, String>getTeacherID_Name() throws SQLException;

	/**
	 * 获取所有老师的信息，返回Bean
	 * @return
	 * @throws SQLException
	 */
	public HashMap<Integer, TeacherBean> getAllTeacher()throws SQLException;
}
