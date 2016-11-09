package DAO.teacherDAO;

import bean.domain.*;

import java.sql.SQLException;

public interface TeacherDAO {

	/**
	 * �����ʦ��������ʦid
	 * @param teacher
	 * @return int
	 * @throws SQLException
	 */
	public int addTeacher(Teacher teacher) throws SQLException;

	/**
	 * ����idѰ����ʦ������Teacher
	 * @param teacherId
	 * @return Teacher
	 * @throws SQLException
	 */
	public Teacher getTeacherInfo(int teacherId) throws SQLException;

	/**
	 * �޸���ʦ��Ϣ
	 * @param teacher
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(Teacher teacher) throws SQLException;

	/**
	 * ɾ����ʦ
	 * @param teacherId
	 * @return Teacher
	 * @throws SQLException
	 */
	public Teacher deleteById(int teacherId) throws SQLException;
	
	/**
	 * ͨ��id��ȡ��ʦ��Ϣ
	 * @param teacherId
	 * @return Teacher
	 * @throws SQLException
	 */
	public Teacher getInfoById(int teacherId) throws SQLException;

}
