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
public class StudentDaoImpl implements StudentDAO{
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
        Connection conn=null;
        PreparedStatement ps=null;
        String sql= "insert into student (id,studentId,name,grade,isOnproject,isNeedProject,finalScore," +
                "properties1,properties2,worked,isPunished,graduatedSchool,tecKeyWord,homePageUrl) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conn=DBUtils.getConnetction();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,studentBean.getId());
            ps.setString(2, studentBean.getStudentId());
            ps.setString(3, studentBean.getName());
            ps.setInt(4,studentBean.getGrade());
            ps.setInt(5,studentBean.getIsOnProject());
            ps.setInt(6,studentBean.getIsNeedProject());
            ps.setInt(7,studentBean.getFinalScore());
            ps.setString(8,studentBean.getProperties1());
            ps.setString(9,studentBean.getProperties2());
            ps.setInt(10,studentBean.getWorked());
            ps.setInt(11,studentBean.getIsPunished());
            ps.setString(12,studentBean.getGraduatedSchool());
            ps.setString(13,studentBean.getTecKeyWord());
            ps.setString(14,studentBean.getHomePageUrl());
            int i=ps.executeUpdate();
            if(i==0){
                flag=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
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
        boolean flag=true;
        Connection conn=null;
        PreparedStatement ps = null;

        String sql = "UPDATE student set studentId = '?',name = '?',grade = ?,isOnproject = ?,isNeedProject = ?," +
                "finalScore = ?,properties1 = '?',properties2 = '?',worked = ?,isPunished = ?,graduatedSchool = '?'," +
                "tecKeyWord = '?',homePageUrl = '?' where id = ?";

        try{
            conn = DBUtils.getConnetction();
            ps=conn.prepareStatement(sql);
            ps.setString(1, studentBean.getStudentId());
            ps.setString(2, studentBean.getName());
            ps.setInt(3,studentBean.getGrade());
            ps.setInt(4,studentBean.getIsOnProject());
            ps.setInt(5,studentBean.getIsNeedProject());
            ps.setInt(6,studentBean.getFinalScore());
            ps.setString(7,studentBean.getProperties1());
            ps.setString(8,studentBean.getProperties2());
            ps.setInt(9,studentBean.getWorked());
            ps.setInt(10,studentBean.getIsPunished());
            ps.setString(11,studentBean.getGraduatedSchool());
            ps.setString(12,studentBean.getTecKeyWord());
            ps.setString(13,studentBean.getHomePageUrl());
            ps.setInt(14,studentBean.getId());
            int i=ps.executeUpdate();
            if(i==0) {
                flag = false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
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
            boolean flag=true;
            Connection conn=null;
            PreparedStatement ps=null;
            String sql="delete from student where id=?";

            try {
                conn=DBUtils.getConnetction();
                ps=conn.prepareStatement(sql);
                ps.setInt(1, id);
                int i=ps.executeUpdate();
                if(i==0){
                    flag=false;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
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
            Connection conn=null;
            PreparedStatement ps=null;
            ResultSet rs=null;
            String sql="select * from student where id = ?";

            try {
                conn=DBUtils.getConnetction();
                ps=conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs=ps.executeQuery();
                while(rs.next()){

                    student.setId(rs.getInt("id"));
                    student.setStudentId(rs.getString("studentId"));
                    student.setName(rs.getString("name"));
                    student.setGrade(rs.getInt("grade"));
                    student.setIsOnProject(rs.getInt("isOnProject"));
                    student.setIsNeedProject(rs.getInt("isNeedProject"));
                    student.setFinalScore(rs.getInt("finalScore"));
                    student.setProperties1(rs.getString("properties1"));
                    student.setProperties2(rs.getString("properties2"));
                    student.setWorked(rs.getInt("worked"));
                    student.setIsPunished(rs.getInt("isPunished"));
                    student.setGraduatedSchool(rs.getString("graduatedSchool"));
                    student.setTecKeyWord(rs.getString("tecKeyWord"));
                    student.setHomePageUrl(rs.getString("homePageUrl"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                DBUtils.close(rs, ps, conn);
            }

            return student;
        }
    }
}
