package DAO.studentDAO;

import bean.domain.StudentBean;
import bean.domain.TeacherBean;

import java.sql.SQLException;

/**
 * Created by GR on 2016/12/4.
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

}
