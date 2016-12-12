package DAO.studentDAO;

import bean.domain.StudentBean;
import bean.domain.TeacherBean;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by GR on 2016/12/4.?
 */
public interface StudentDAO {

    /**
     * 添加学生
     * @param studentBean
     * @return boolean
     * @throws SQLException
     */
    public boolean addStudent(StudentBean studentBean) throws SQLException;

    /**
     * 更新学生信息
     * @param studentBean
     * @return boolean
     * @throws SQLException
     */

    public boolean updateInfoByStudent(StudentBean studentBean) throws SQLException;

    /**
     * 删除学生信息
     * @param id
     * @return boolean
     * @throws SQLException
     */
    public boolean deleteById(Integer id) throws SQLException;

    /**
     * 获取StudentBean，通过id
     * @param id
     * @return StudentBean
     * @throws SQLException
     */
    public StudentBean getInfoById(Integer id) throws SQLException;

    /**
     * 设定文件关系表
     * @param studentId
     * @param teamId
     * @param projectId
     * @param fileId
     * @return
     * @throws SQLException
     */
    public boolean setFile(int studentId, int teamId, int projectId, int fileId) throws SQLException;

    /**
     * 设定计划关系表
     * @param studentId
     * @param teamId
     * @param projectId
     * @param planId
     * @return
     * @throws SQLException
     */
    public boolean setPlan(int studentId, int teamId, int projectId, int planId) throws SQLException;

    /**
     * 删除文件关系，因为这个表的关键就是文件，所以不通过别的来删除了。
     * @param fileId
     * @return
     * @throws SQLException
     */
    public boolean deleteFile(int fileId) throws SQLException;

    /**
     * 删除计划关系
     * @param planId
     * @return
     * @throws SQLException
     */
    public boolean deletePlan(int planId) throws SQLException;

    /**
     * 添加学生进某个团队
     * @param studentId,teamId,leaderFlag
     * @return boolean
     * @throws SQLException
     */
    public boolean addStudentToTeam(int studentId, int teamId, int leaderFlag) throws SQLException;

    /**
     * 获得学生所在团队
     * @param studentId
     * @return teamId
     * @throws SQLException
     */
    public ArrayList<Integer> getTeamIdByStudentId(int studentId) throws SQLException;

    /**
     * 修改学生所在团队
     * @param studentId,teamId,leaderFlag
     * @return boolean
     * @throws SQLException
     */
    public boolean updateStudentToTeam(int studentId, int teamId, int leaderFlag) throws SQLException;

    /**
     * 删除团队某个学生
     *
     * @param teamId,studentId
     * @return
     * @throws SQLException
     */
    public boolean deleteStudentFormTeam(int teamId, int studentId) throws SQLException;
    /**
     * 删除团队所有学生
     *
     * @param teamId
     * @return
     * @throws SQLException
     */
    public boolean deleteAllStudentFormTeam(int teamId) throws SQLException;
}
