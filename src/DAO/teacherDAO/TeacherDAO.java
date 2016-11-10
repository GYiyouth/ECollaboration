package DAO.teacherDAO;

import bean.domain.*;

import java.sql.SQLException;

public interface TeacherDAO {

	/**
	 * �����ʦ��������ʦid
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
	 * �޸���ʦ��Ϣ
	 * @param teacherBean
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(TeacherBean teacherBean) throws SQLException;

	public boolean updateInfoByTeacher(TeacherBean teacherBean) throws SQLException;

	/**
	 * ɾ����ʦ
	 * @param teacherId
	 * @return TeacherBean
	 * @throws SQLException
	 */
	public TeacherBean deleteById(Integer teacherId) throws SQLException;
	
	/**
	 * ͨ��id��ȡ��ʦ��Ϣ
	 * @param teacherId
	 * @return TeacherBean
	 * @throws SQLException
	 */
	public TeacherBean getInfoById(Integer teacherId) throws SQLException;

}
