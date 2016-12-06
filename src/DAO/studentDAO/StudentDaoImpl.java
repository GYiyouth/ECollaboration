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
        String sql = "insert into ecollaborationweb.student (id,grade,isOnproject,isNeedProject," +
                "graduatedSchool,tecKeyWord,homePageUrl,codeScore1,codeScore2,presentationScore,finalScore) " +
                "values(?,?,?,?,?,?,?,?,?,?,?);";
        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentBean.getId());
            ps.setInt(2, studentBean.getGrade());
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
            ps.setInt(1, studentBean.getGrade());
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
                student.setGrade(rs.getInt("grade"));
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
}

