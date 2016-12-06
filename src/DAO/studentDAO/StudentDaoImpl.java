package DAO.studentDAO;

import DAO.com.util.db.DBUtils;
import bean.domain.StudentBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String sql = "insert into student (id,schoolId,name,grade,isOnproject,isNeedProject," +
                "graduatedSchool,tecKeyWord,homePageUrl,codeScore1,codeScore2,presentationScore,finalScore) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentBean.getId());
            ps.setString(2, studentBean.getSchoolId());
            ps.setString(3, studentBean.getName());
            ps.setInt(4, studentBean.getGrade());
            ps.setInt(5, studentBean.getIsOnProject());
            ps.setInt(6, studentBean.getIsNeedProject());
            ps.setString(7, studentBean.getGraduatedSchool());
            ps.setString(8, studentBean.getTecKeyWord());
            ps.setString(9, studentBean.getHomePageUrl());
            ps.setInt(10,studentBean.getCodeScore1());
            ps.setInt(11,studentBean.getCodeScore2());
            ps.setInt(12,studentBean.getPresentationScore());
            ps.setInt(13, studentBean.getFinalScore());
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

        String sql = " UPDATE student set schoolId = ?,name = ?,grade = ?,isOnproject = ?,isNeedProject = ?," +
                "graduatedSchool = ?,tecKeyWord = ?,homePageUrl = ?,codeScore1 = ?,codeScore2 = ?," +
                "presentationScore = ?,finalScore = ? where id = ?;";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setString(1, studentBean.getSchoolId());
            ps.setString(2, studentBean.getName());
            ps.setInt(3, studentBean.getGrade());
            ps.setInt(4, studentBean.getIsOnProject());
            ps.setInt(5, studentBean.getIsNeedProject());
            ps.setString(6, studentBean.getGraduatedSchool());
            ps.setString(7, studentBean.getTecKeyWord());
            ps.setString(8, studentBean.getHomePageUrl());
            ps.setInt(9,studentBean.getCodeScore1());
            ps.setInt(10,studentBean.getCodeScore2());
            ps.setInt(11,studentBean.getPresentationScore());
            ps.setInt(12, studentBean.getFinalScore());
            ps.setInt(13, studentBean.getId());
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
        String sql = "delete from student where id=?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if (i == 0) {
                flag = false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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
        String sql = "select * from student where id = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {

                student.setId(rs.getInt("id"));
                student.setSchoolId(rs.getString("schoolId"));
                student.setName(rs.getString("name"));
                student.setGrade(rs.getInt("grade"));
                student.setIsOnProject(rs.getInt("isOnProject"));
                student.setIsNeedProject(rs.getInt("isNeedProject"));
                student.setGraduatedSchool(rs.getString("graduatedSchool"));
                student.setTecKeyWord(rs.getString("tecKeyWord"));
                student.setHomePageUrl(rs.getString("homePageUrl"));
                student.setCodeScore1(rs.getInt("codeScore1"));
                student.setCodeScore2(rs.getInt("codeScore2"));
                student.setFinalScore(rs.getInt("finalScore"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, ps, conn);
        }

        return student;
    }
}

