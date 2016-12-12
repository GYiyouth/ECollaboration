package DAO.studentDAO;

import DAO.com.util.db.DBUtils;
import bean.domain.StudentBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by GR on 2016/12/4.
 */
public class StudentDaoImpl implements StudentDAO {
    /**
     * 添加学生
     *
     * @param studentBean
     * @return studentId
     * @throws SQLException
     */
    @Override
    public boolean addStudent(StudentBean studentBean) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into ecollaborationweb.student (id,grade,isOnproject,isNeedProject," +
                "graduatedSchool,tecKeyWord,homePageUrl,codeScore1,codeScore2,presentationScore,finalScore) " +
                "values(?,?,?,?,?,?,?,?,?,?,?);";
        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentBean.getId());
            ps.setString(2, studentBean.getGrade());
            ps.setInt(3, studentBean.getIsOnProject());
            ps.setInt(4, studentBean.getIsNeedProject());
            ps.setString(5, studentBean.getGraduatedSchool());
            ps.setString(6, studentBean.getTecKeyWord());
            ps.setString(7, studentBean.getHomePageUrl());
            ps.setInt(8,studentBean.getCodeScore1());
            ps.setInt(9,studentBean.getCodeScore2());
            ps.setInt(10,studentBean.getPresentationScore());
            ps.setInt(11, studentBean.getFinalScore());
            int i = ps.executeUpdate();
            if (i == 0) {
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, ps, conn);
        }
        return flag;
    }

    /**
     * 更新学生信息
     *
     * @param studentBean
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean updateInfoByStudent(StudentBean studentBean) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = " UPDATE ecollaborationweb.student set grade = ?,isOnproject = ?,isNeedProject = ?," +
                "graduatedSchool = ?,tecKeyWord = ?,homePageUrl = ?,codeScore1 = ?,codeScore2 = ?," +
                "presentationScore = ?,finalScore = ? where id = ?;";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setString(1, studentBean.getGrade());
            ps.setInt(2, studentBean.getIsOnProject());
            ps.setInt(3, studentBean.getIsNeedProject());
            ps.setString(4, studentBean.getGraduatedSchool());
            ps.setString(5, studentBean.getTecKeyWord());
            ps.setString(6, studentBean.getHomePageUrl());
            ps.setInt(7,studentBean.getCodeScore1());
            ps.setInt(8,studentBean.getCodeScore2());
            ps.setInt(9,studentBean.getPresentationScore());
            ps.setInt(10, studentBean.getFinalScore());
            ps.setInt(11, studentBean.getId());
            int i = ps.executeUpdate();
            if (i == 0) {
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(null, ps, conn);
        }
        return flag;
    }

    /**
     * 删除学生信息
     *
     * @param id
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean deleteById(Integer id) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from ecollaborationweb.student where id=?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if (i == 0) {
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, ps, conn);
        }

        return flag;

    }

    /**
     * 获取StudentBean，通过studentId
     *
     * @param id
     * @return StudentBean
     * @throws SQLException
     */
    @Override
    public StudentBean getInfoById(Integer id) throws SQLException {
        StudentBean student = new StudentBean();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from ecollaborationweb.student where id = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                student.setId(rs.getInt("id"));
                student.setGrade(rs.getString("grade"));
                student.setIsOnProject(rs.getInt("isOnProject"));
                student.setIsNeedProject(rs.getInt("isNeedProject"));
                student.setGraduatedSchool(rs.getString("graduatedSchool"));
                student.setTecKeyWord(rs.getString("tecKeyWord"));
                student.setHomePageUrl(rs.getString("homePageUrl"));
                student.setCodeScore1(rs.getInt("codeScore1"));
                student.setCodeScore2(rs.getInt("codeScore2"));
                student.setFinalScore(rs.getInt("finalScore"));
                return student;
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }


    }

    /**
     * 设定文件关系表
     *
     * @param studentId
     * @param teamId
     * @param projectId
     * @param fileId
     * @return
     * @throws SQLException
     */
    @Override
    public boolean setFile(int studentId, int teamId, int projectId, int fileId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO ECollaborationWeb.student_team_project_file " +
                " (creatorId, projectId, fileId, teamId) VALUES (?,?,?,?);";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, projectId);
            preparedStatement.setInt(3, fileId);
            preparedStatement.setInt(4, teamId);
            int flag = preparedStatement.executeUpdate();
            if (flag ==1 ){
                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            DBUtils.close(null, preparedStatement, connection);
        }
    }

    /**
     * 设定计划关系表
     *
     * @param studentId
     * @param teamId
     * @param projectId
     * @param planId
     * @return
     * @throws SQLException
     */
    @Override
    public boolean setPlan(int studentId, int teamId, int projectId, int planId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO ECollaborationWeb.student_team_project_plan " +
                " (studentId, projectId, planId, teamId) VALUES (?,?,?,?);";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, projectId);
            preparedStatement.setInt(3, planId);
            preparedStatement.setInt(4, teamId);
            int flag = preparedStatement.executeUpdate();
            if (flag ==1 ){
                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            DBUtils.close(null, preparedStatement, connection);
        }
    }

    /**
     * 删除文件关系，因为这个表的关键就是文件，所以不通过别的来删除了。
     *
     * @param fileId
     * @return
     * @throws SQLException
     */
    @Override
    public boolean deleteFile(int fileId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM ECollaborationWeb.student_team_project_file " +
                "WHERE fileId =? ;";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, fileId);
            int flag = preparedStatement.executeUpdate();
            if (flag >= 1)
                return true;
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            DBUtils.close(null, preparedStatement, connection);
        }
    }

    /**
     * 删除计划关系
     *
     * @param planId
     * @return
     * @throws SQLException
     */
    @Override
    public boolean deletePlan(int planId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM ECollaborationWeb.student_team_project_plan " +
                " WHERE planId = ?;";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, planId);
            int flag = preparedStatement.executeUpdate();
            if (flag >= 1)
                return true;
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            DBUtils.close(null, preparedStatement, connection);
        }
    }

    /**
     * 添加学生进某个团队
     *
     * @param studentId
     * @param teamId
     * @param leaderFlag
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean addStudentToTeam(int studentId, int teamId, int leaderFlag) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into ecollaborationweb.student_team (studentId, teamId, leaderFlag) VALUES (?,?,?)";
        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, teamId);
            ps.setInt(3, leaderFlag);
            int i = ps.executeUpdate();
            if (i == 0) {
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, ps, conn);
        }
        return flag;
    }

    /**
     * 获得学生所在团队
     *
     * @param studentId
     * @return teamId
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTeamIdByStudentId(int studentId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select teamId from ecollaborationweb.student_team where studentId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("planId"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
    }

    /**
     * 修改学生所在团队
     *
     * @param studentId
     * @param teamId
     * @param leaderFlag @return boolean
     * @throws SQLException
     */
    @Override
    public boolean updateStudentToTeam(int studentId, int teamId, int leaderFlag) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = " UPDATE ecollaborationweb.student_team set teamId = ?,leaderFlag = ? WHERE studentId = ?;";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setInt(2, leaderFlag);
            ps.setInt(3, studentId);
            int i = ps.executeUpdate();
            if (i == 0) {
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(null, ps, conn);
        }
        return flag;
    }

    /**
     * 删除团队某个学生
     *
     * @param teamId
     * @param studentId
     * @return
     * @throws SQLException
     */
    @Override
    public boolean deleteStudentFormTeam(int teamId, int studentId) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from ecollaborationweb.student_team where studentId=? AND  teamId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, teamId);
            int i = ps.executeUpdate();
            if (i == 0) {
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, ps, conn);
        }
        return flag;
    }

    /**
     * 删除团队所有学生
     *
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public boolean deleteAllStudentFormTeam(int teamId) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from ecollaborationweb.student_team where teamId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teamId);
            int i = ps.executeUpdate();
            if (i == 0) {
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, ps, conn);
        }
        return flag;
    }
}

