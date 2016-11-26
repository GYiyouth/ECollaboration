package DAO.codeDAO;

import bean.domain.CodeBean;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 与student project team 相连
 * Created by geyao on 2016/11/7.
 */
public interface CodeDAO {
	/**
	 * 添加文件，返回id
	 * @param codeBean
	 * @return
	 * @throws SQLException
	 */
	public Integer add(CodeBean codeBean) throws SQLException;

	/**
	 * 获取文件，通过id
	 * @param codeId
	 * @return
	 * @throws SQLException
	 */
	public CodeBean getCodeInfo(int codeId) throws SQLException;

	/**
	 * 修改文件，通过CodeBean,id
	 * @param codeId
	 * @param codeBean
	 * @return
	 * @throws SQLException
	 */
	public boolean updateCode(CodeBean codeBean, int codeId) throws SQLException;

	/**
	 * 删除文件，通过id
	 * @param codeId
	 * @return
	 * @throws SQLException
	 */
	public CodeBean deleteCode(int codeId) throws SQLException;

	/**
	 * 获取代码id列表，通过学生id
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getCodeIdListByStudentId(int studentId) throws SQLException;

	/**
	 * 获取代码id列表，通过项目id
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getCodeIdListByProjectId(int projectId) throws SQLException;

	/**
	 * 获取代码id列表，通过团队id
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getCodeIdListByTeamId(int teamId) throws SQLException;

	/**
	 * 获取代码id列表，通过学生id，项目id
	 * @param studentId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getCodeIdListByStudentIdProjectId(int studentId, int projectId) throws SQLException;

	/**
	 * 获取代码id列表，通过学生id，团队id
	 * @param studentId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getCodeIdListByStudentIdTeamId(int studentId, int teamId) throws SQLException;

	/**
	 * 获取代码id列表，通过项目id，团队id
	 * @param projectId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getCodeIdListByProjectIdTeamId(int projectId, int teamId) throws SQLException;

	/**
	 * 获取代码id列表，通过项目id，团队id，学生id
	 * @param projectId
	 * @param teamId
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getCodeIdListByProjectIdTeamIdStudentId(int projectId, int teamId, int studentId)throws SQLException;
}
