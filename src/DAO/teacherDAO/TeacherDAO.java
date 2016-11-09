package DAO.teacherDAO;

import bean.domain.*;

import java.sql.SQLException;

public interface TeacherDAO {

	/**
	 * �����ʦ��������ʦid
	 * @param teacherBean
	 * @return int
	 * @throws SQLException
	 */
	public int addTeacher(TeacherBean teacherBean) throws SQLException;

	/**
	 * ����idѰ����ʦ������Teacher
	 * @param teacherId
	 * @return TeacherBean
	 * @throws SQLException
	 */
	public TeacherBean getTeacherInfo(int teacherId) throws SQLException;

	/**
	 * �޸���ʦ��Ϣ
	 * @param teacherBean
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(TeacherBean teacherBean) throws SQLException;

	/**
	 * ɾ����ʦ
	 * @param teacherId
	 * @return TeacherBean
	 * @throws SQLException
	 */
	public TeacherBean deleteById(int teacherId) throws SQLException;
	
	/**
	 * ͨ��id��ȡ��ʦ��Ϣ
	 * @param teacherId
	 * @return TeacherBean
	 * @throws SQLException
	 */
	public TeacherBean getInfoById(int teacherId) throws SQLException;

}
