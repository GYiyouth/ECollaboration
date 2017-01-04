package DAO.studentDAO;

import DAO.com.util.db.DBUtils;
import bean.domain.StudentBean;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlU = "INSERT INTO ECollaborationWeb.user ( " +
                " schoolId, name, sex, role, email, phoneNumber, " +
                " logName, passWord, createDate, " +
                " lastLogTime, activeBefore, newsFlag) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
        String sqlS = "insert into ECollaborationWeb.student (id,grade,isOnproject,isNeedProject," +
                "graduatedSchool,tecKeyWord,homePageUrl) " +
                "values(?,?,?,?,?,?,?)";
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sqlU, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,studentBean.getSchoolId());
            ps.setString(2,studentBean.getName());
            ps.setInt(3,studentBean.getSex());
            ps.setInt(4,studentBean.getRole());
            ps.setString(5,studentBean.getEmail());
            ps.setString(6,studentBean.getPhoneNumber());
            ps.setString(7,studentBean.getLogName());
            ps.setString(8,studentBean.getPassWord());
            ps.setString(9,studentBean.getCreateDate());
            ps.setString(10,studentBean.getLastLogTime());
            ps.setString(11,studentBean.getActiveBefore());
            ps.setInt(12,studentBean.getNewFlag());
            int z  = ps.executeUpdate();
            if(z!=0){
                ResultSet ids = ps.getGeneratedKeys();
                if(ids.next())
                    System.out.println("插入user表成功");
                    studentBean.setId(ids.getInt(1));
            }else{
                return false;
            }
            ps = conn.prepareStatement(sqlS);
            ps.setInt(1, studentBean.getId());
            ps.setInt(2, studentBean.getGrade());
            ps.setInt(3, studentBean.getIsOnProject());
            ps.setInt(4, studentBean.getIsNeedProject());
            ps.setString(5, studentBean.getGraduatedSchool());
            ps.setString(6, studentBean.getTecKeyWord());
            ps.setString(7, studentBean.getHomePageUrl());
//            ps.setInt(8,studentBean.getCodeScore1());
//            ps.setInt(9,studentBean.getCodeScore2());
//            ps.setInt(10,studentBean.getPresentationScore());
//            ps.setInt(11, studentBean.getFinalScore());
            int i = ps.executeUpdate();
            if (i == 0) {
                return false;
            }else{
                System.out.println("插入student表成功");
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
            return false;
        } finally {
            DBUtils.close(null, ps, conn);
        }

        return true;
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
        /*if (studentBean == null)
			return false;*/
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql1 = "UPDATE ecollaborationweb.user set name = ?, sex = ?, email = ?, phoneNumber = ? WHERE id = ?";
        String sql2 = "UPDATE ecollaborationweb.student set homePageUrl = ?, graduatedSchool = ?, tecKeyWord = ? WHERE  id = ?";
        try{
            connection = DBUtils.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1,studentBean.getName());
            preparedStatement.setInt(2,studentBean.getSex());
            preparedStatement.setString(3,studentBean.getEmail());
            preparedStatement.setString(4,studentBean.getPhoneNumber());
            preparedStatement.setInt(5,studentBean.getId());
            int row = preparedStatement.executeUpdate();
            if (row != 0) {
                preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setString(1,studentBean.getHomePageUrl());
                preparedStatement.setString(2,studentBean.getGraduatedSchool());
                preparedStatement.setString(3,studentBean.getTecKeyWord());
                preparedStatement.setInt(4,studentBean.getId());
                int row2 = preparedStatement.executeUpdate();
                if(row2 != 0){
                    connection.commit();
                    return true;
                }else{
                    return false;
                }
            }else
                return false;
        }catch (SQLException e){
            connection.rollback();
            e.printStackTrace();
            throw e;
        }finally {
            DBUtils.close(null, preparedStatement, connection);
        }
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
            conn = DBUtils.getConnection();
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
        String sql = "select * from ecollaborationweb.student,ecollaborationweb.user where student.id = ? and student.id = user.id  ";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                student.setId(rs.getInt("id"));
                student.setGrade(rs.getInt("grade"));
                student.setIsOnProject(rs.getInt("isOnProject"));
                student.setIsNeedProject(rs.getInt("isNeedProject"));
                student.setGraduatedSchool(rs.getString("graduatedSchool"));
                student.setTecKeyWord(rs.getString("tecKeyWord"));
                student.setHomePageUrl(rs.getString("homePageUrl"));
                student.setCodeScore1(rs.getInt("codeScore1"));
                student.setCodeScore2(rs.getInt("codeScore2"));
                student.setFinalScore(rs.getInt("finalScore"));
                student.setSchoolId(rs.getString("schoolId"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getInt("sex"));
                student.setRole(rs.getInt("role"));
                student.setEmail(rs.getString("email"));
                student.setPhoneNumber(rs.getString("phoneNumber"));
                student.setLogName(rs.getString("logName"));
                student.setPassWord(rs.getString("passWord"));
                student.setCreateDate(rs.getString("createDate"));
                student.setPhoto(rs.getString("photo"));
                student.setLastLogTime(rs.getString("lastLogTime"));
                student.setActiveBefore(rs.getString("activeBefore"));
                student.setNewFlag(rs.getInt("newsFlag"));
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
            connection = DBUtils.getConnection();
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
            connection = DBUtils.getConnection();
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
            connection = DBUtils.getConnection();
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
            connection = DBUtils.getConnection();
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
            conn = DBUtils.getConnection();
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
     * 获取团队所有学生，以哈希表存储，1为队长
     *
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public HashMap<Integer, Integer> getStudentIdMapByTeamId(int teamId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null ;
        String sql = "SELECT DISTINCT studentId FROM student_team WHERE teamId = ? " +
                " ORDER BY leaderFlag DESC ;";
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, teamId);
            resultSet = preparedStatement.executeQuery();
            int i = 1;
            while (resultSet.next()){
                hashMap.put(i, resultSet.getInt(1));
                i++;
            }
            return hashMap;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            DBUtils.close(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 获得组长id或组员id
     *
     * @param teamId
     * @param leaderFlag
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<Integer> getTeamLeaderOrMemberByTeamIdLeaderFlag(int teamId, int leaderFlag) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select studentId from ecollaborationweb.student_team where teamId = ? and leaderFlag = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setInt(2, leaderFlag);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("studentId"));
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
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("teamId"));
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
     * 获得团队所有学生
     *
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getStudentIdByTeamId(int teamId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select studentId from ecollaborationweb.student_team where teamId = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teamId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("studentId"));
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
            conn = DBUtils.getConnection();
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
            conn = DBUtils.getConnection();
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
            conn = DBUtils.getConnection();
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

    /**
     * 获取学生id，通过项目  团队
     *
     * @param teamId
     * @param projectId
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<Integer> getStudentIdByTeamIdProjectId(int teamId, int projectId) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select studentId from team_project,student_team " +
                "where team_project.teamId = student_team.teamId and student_team.teamId = ? " +
                "AND team_project.projectId = ? ORDER BY student_team.leaderFlag DESC";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setInt(2, projectId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("studentId"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
    }
}

