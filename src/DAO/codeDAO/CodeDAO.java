package DAO.codeDAO;

import bean.domain.CodeBean;

import java.io.File;
import java.io.InterruptedIOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 与student project team 相连
 * Created by geyao on 2016/11/7.
 */
public interface CodeDAO {
	/**
	 * 添加代码，返回id
	 * @param codeBean
	 * @return
	 * @throws SQLException
	 */
	public Integer add(CodeBean codeBean) throws SQLException;

	/**
	 * 获取代码，通过id
	 * @param codeId
	 * @return
	 * @throws SQLException
	 */
	public CodeBean getCodeInfo(int codeId) throws SQLException;

	/**
	 * 修改代码，通过CodeBean,id
	 * @param codeId
	 * @param codeBean
	 * @return
	 * @throws SQLException
	 */
	public boolean updateCode(CodeBean codeBean, int codeId) throws SQLException;

	/**
	 * 删除代码，通过id
	 * @param codeId
	 * @return
	 * @throws SQLException
	 */
	public CodeBean deleteCode(int codeId) throws SQLException;

	/**
	 * 获取某个学生在某个项目所有的代码行数
	 * @param studentId
	 * @param projectId
	 * @return
	 */
	public Integer getCodeRowsSum(int studentId,int projectId) throws SQLException;

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

	/**
	 * 获取一个团队在一个项目下的codeBean列表
	 * 1放队长的
	 * @param projectId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public HashMap<Integer, CodeBean> getCodeBeans(int projectId, int teamId) throws SQLException;
}
