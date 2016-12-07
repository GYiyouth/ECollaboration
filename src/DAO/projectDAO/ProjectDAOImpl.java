package DAO.projectDAO;

import DAO.com.smallTools.ComGetListValueDAO;
import DAO.com.smallTools.ComGetListValueDAOImpl;
import DAO.com.util.db.DBUtils;
import bean.domain.ProjectBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by GR on 2016/11/30.
 */
public class ProjectDAOImpl implements ProjectDAO{
    /**
     * 添加项目，内部生成id，返回项目id
     *
     * @param projectBean
     * @return int
     * @throws SQLException
     */
    @Override
    public Integer addProject(ProjectBean projectBean) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO ECollaborationWeb.project (name) VALUES (?);";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, projectBean.getName());
            int flag = preparedStatement.executeUpdate();
            if (flag == 1){
                sql = "SELECT LAST_INSERT_ID();";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    projectBean.setId(resultSet.getInt(1));
                    updateInfo(projectBean, projectBean.getId());
                }
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            DBUtils.close(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 根据id寻找项目，返回project
     *
     * @param projectId
     * @return ProjectBean
     * @throws SQLException
     */
    @Override
    public ProjectBean getProjectInfo(int projectId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ProjectBean projectBean = null;
        String sql = "SELECT * FROM ECollaborationWeb.project WHERE id = ?;";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, projectId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                projectBean = new ProjectBean();
                projectBean.setId(projectId);
                projectBean.setName(            resultSet.getString("name"));
                projectBean.setApplyBeforeDate( resultSet.getString("applyBeforeDate"));
                projectBean.setFinishDate(      resultSet.getString("finishDate"));
                projectBean.setSurvivalDate(    resultSet.getString("survivalDate"));
                projectBean.setTeamNumber(      resultSet.getInt("teamNumber"));
                projectBean.setTeamMax(         resultSet.getInt("teamMax"));
                projectBean.setMemberMax(       resultSet.getInt("memberMax"));
                projectBean.setCreateDate(      resultSet.getString("createDate"));
                projectBean.setGrade(           resultSet.getString("grade"));
                projectBean.setKeyWord(         resultSet.getString("keyWord"));
                projectBean.setInfo(            resultSet.getString("info"));
                projectBean.setRequirement(         resultSet.getString("requirement"));
                projectBean.setGain(            resultSet.getString("gain"));
                projectBean.setPriority(        resultSet.getInt("priority"));
                projectBean.setStatus(          resultSet.getInt("status"));
                projectBean.setCreatorId(       resultSet.getInt("creatorId"));
                projectBean.setTeacherId(       resultSet.getInt("teacherId"));
                return projectBean;
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            DBUtils.close(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 修改项目的属性，如果project属性不为null，则更新数据库
     *
     * @param projectBean
     * @param id
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean updateInfo(ProjectBean projectBean, int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "UPDATE ECollaborationWeb.project SET ";
        try {
            connection = DBUtils.getConnetction();
            if (projectBean.getName() != null){
                sql = sql + "name = '" + projectBean.getName() + "' ,";
            }
            if (projectBean.getApplyBeforeDate() != null ){
                sql = sql + "applyBeforeDate = '" + projectBean.getApplyBeforeDate() + "' ,";
            }
            if (projectBean.getFinishDate() != null){
                sql = sql + "finishDate = '" + projectBean.getFinishDate() + "' ,";
            }
            if (projectBean.getSurvivalDate() != null){
                sql = sql + "survivalDate = '" + projectBean.getSurvivalDate() + "' ;";
            }
            if (projectBean.getTeamNumber() !=null ){
                sql = sql + "teamNumber = " + projectBean.getTeamNumber() + " ,";
            }
            if (projectBean.getTeamMax() != null){
                sql = sql + "teamMax = " + projectBean.getTeamMax() + " ,";
            }
            if (projectBean.getMemberMax() != null){
                sql = sql + "memberMax = " + projectBean.getMemberMax() + " ,";
            }
            if (projectBean.getCreateDate() != null){
                sql = sql + "createDate = '" + projectBean.getCreateDate() + "' ,";
            }
            if (projectBean.getGrade() != null){
                sql = sql + "grade = '" + projectBean.getGrade() + "' ,";
            }
            if (projectBean.getKeyWord() != null){
                sql = sql + "keyWord = '" + projectBean.getKeyWord() + "' ,";
            }
            if (projectBean.getInfo() != null){
                sql = sql + "info = '" + projectBean.getInfo() + "' ,";
            }
            if (projectBean.getRequirement() != null){
                sql = sql + "require = '" + projectBean.getRequirement() + "' ,";
            }
            if (projectBean.getGain() != null){
                sql = sql + "gain = '" + projectBean.getGain() + "' ,";
            }
            if (projectBean.getPriority() != null){
                sql = sql + "priority = " + projectBean.getPriority() + " ,";
            }
            if (projectBean.getStatus() != null){
                sql = sql + "status = " + projectBean.getStatus() + " ,";
            }
            if (projectBean.getCreatorId() != null){
                sql = sql + "creatorId = " + projectBean.getCreatorId() + " ,";
            }
            if (projectBean.getTeacherId() != null){
                sql = sql + "teacherId = " + projectBean.getTeacherId() + " ,";
            }
            sql = sql.substring(0, sql.lastIndexOf(","));
            sql = sql + "where id = " + id;
            preparedStatement = connection.prepareStatement(sql);
            int flag = preparedStatement.executeUpdate();
            if (flag == 1){
                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            DBUtils.close(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 删除项目
     *
     * @param projectId
     * @return ProjectBean
     * @throws SQLException
     */
    @Override
    public boolean deleteById(int projectId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM ECollaborationWeb.project WHERE id = ?";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, projectId);
            int flag = preparedStatement.executeUpdate();
            if (flag == 1){
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
     * 获取项目id列表，通过教师id
     *
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTeacherId(int teacherId) throws SQLException {
        ComGetListValueDAO comGetListValueDAO = new ComGetListValueDAOImpl<Integer, Integer>();
        return comGetListValueDAO.getListAfromBbyC("projectId", "teacherId", teacherId, "teacher_project");
    }

    /**
     * 获取项目id列表，通过文件id
     *
     * @param fileId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByFileId(int fileId) throws SQLException {
        ComGetListValueDAO comGetListValueDAO = new ComGetListValueDAOImpl<Integer, Integer>();
        return comGetListValueDAO.getListAfromBbyC("projectId", "fileId", fileId, "student_team_project_file");
    }

//    /**
//     * 获取项目id列表，通过文件id，教师id
//     *
//     * @param fileId
//     * @param teacherId
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public ArrayList<Integer> getProjectIdListByFileIdTeacherId(int fileId, int teacherId) throws SQLException {
//        return null;
//    }
//
//    /**
//     * 获取项目id列表，通过文件id，学生id
//     *
//     * @param fileId
//     * @param studentId
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public ArrayList<Integer> getProjectIdListByFileIdStudentId(int fileId, int studentId) throws SQLException {
//        return null;
//    }

    /**
     * 获取项目id列表，通过计划id
     *
     * @param planId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByPlanId(int planId) throws SQLException {
        ComGetListValueDAO comGetListValueDAO = new ComGetListValueDAOImpl<Integer, Integer>();
        return comGetListValueDAO.getListAfromBbyC("projectId", "planId", planId, "student_team_project_plan");
    }

//    /**
//     * 获取项目id列表，通过计划id，教师id
//     *
//     * @param planId
//     * @param teacherId
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public ArrayList<Integer> getProjectIdListByPlanIdTeacherId(int planId, int teacherId) throws SQLException {
//        return null;
//    }

//    /**
//     * 获取项目id列表，通过计划id，学生id
//     *
//     * @param planId
//     * @param studentId
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public ArrayList<Integer> getProjectIdListByPlanIdStudentId(int planId, int studentId) throws SQLException {
//        return null;
//    }

    /**
     * 获取项目id列表，通过学生id
     *
     * @param studentId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByStudentId(int studentId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Integer> list = new ArrayList<>();
        String sql = "SELECT projectId FROM ECollaborationWeb.team_project AS tp," +
                " ECollaborationWeb.student_team AS st " +
                "WHERE st.studentId = ? AND tp.teamId = st.teamId;";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, studentId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getInt(1));
            }
            if (list.size()>0){
                HashSet h = new HashSet(list);
                list.clear();
                list.addAll(h);
                return list;
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            DBUtils.close(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 通过项目id列表，通过代码id
     *
     * @param codeId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByCodeId(int codeId) throws SQLException {
        ComGetListValueDAO comGetListValueDAO = new ComGetListValueDAOImpl<Integer, Integer>();
        return comGetListValueDAO.getListAfromBbyC("projectId", "id", codeId, "code");
    }

//    /**
//     * 获取项目id列表，通过代码id，学生id
//     *
//     * @param codeId
//     * @param StudentId
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public ArrayList<Integer> getProjectIdListByCodeIdStudentId(int codeId, int StudentId) throws SQLException {
//        return null;
//    }

    /**
     * 获取项目id列表，通过团队id
     *
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTeamId(int teamId) throws SQLException {
        ComGetListValueDAO comGetListValueDAO = new ComGetListValueDAOImpl<Integer, Integer>();
        return comGetListValueDAO.getListAfromBbyC("projectId", "teamId", teamId, "team_project");
    }

    /**
     * 获取项目id列表，通过任务id
     *
     * @param taskId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTaskId(int taskId) throws SQLException {
        ComGetListValueDAO comGetListValueDAO = new ComGetListValueDAOImpl<Integer, Integer>();
        return comGetListValueDAO.getListAfromBbyC("projectId", "taskId", taskId, "project_task");
    }

//    /**
//     * 获取项目id列表，通过任务id，团队id
//     *
//     * @param taskId
//     * @param teamId
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public ArrayList<Integer> getProjectIdListByTaskIdTeamId(int taskId, int teamId) throws SQLException {
//        return null;
//    }

//    /**
//     * 获取项目id列表，通过任务id，教师id
//     *
//     * @param taskId
//     * @param teacherId
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public ArrayList<Integer> getProjectIdListByTaskIdTeacherId(int taskId, int teacherId) throws SQLException {
//        return null;
//    }

//    /**
//     * 获取项目id列表，通过任务id，教师id，团队id
//     *
//     * @param taskId
//     * @param teacherId
//     * @param teamId
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public ArrayList<Integer> getProjectIdListByTaskIdTeacherIdTeamId(int taskId, int teacherId, int teamId) throws SQLException {
//        return null;
//    }

    /**
     * 获取项目id列表，通过教师id，团队id
     *
     * @param teacherId
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getProjectIdListByTeacherIdTeamId(int teacherId, int teamId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Integer>list = new ArrayList<>();
        String sql = "SELECT tmp.projectId FROM ECollaborationWeb.team_project AS tmp," +
                " ECollaborationWeb.teacher_project AS tcp " +
                " WHERE tcp.teacherId = ? AND tmp.teamId = ? AND tmp.projectId = tcp.projectId;";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, teacherId);
            preparedStatement.setInt(2, teamId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getInt(1));
            }
            if (list.size()>0){
                HashSet h = new HashSet(list);
                list.clear();
                list.addAll(h);
                return list;
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            DBUtils.close(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 设定项目和任务的关系
     *
     * @param projectId
     * @param taskId
     * @return
     * @throws SQLException
     */
    @Override
    public boolean setProjectTask(int projectId, int taskId) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO ECollaborationWeb.project_task (projectId, taskId) VALUES (?,?);";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, projectId);
            preparedStatement.setInt(2, taskId);
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
     * 删除关系
     *
     * @param projectId
     * @param taskId
     * @return
     * @throws SQLException
     */
    @Override
    public boolean deleteProjectTask(int projectId, int taskId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM ECollaborationWeb.project_task WHERE taskId =? AND projectId = ?;";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, taskId);
            preparedStatement.setInt(2, projectId);
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
     * 删除关系，通过projectId
     *
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public boolean deleteProject_Task(int projectId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM ECollaborationWeb.project_task WHERE projectId =?;";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, projectId);
            if (preparedStatement.executeUpdate() >= 1){
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
     * 删除关系，通过taskId
     *
     * @param taskId
     * @return
     * @throws SQLException
     */
    @Override
    public boolean deleteTask_Project(int taskId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM ECollaborationWeb.project_task WHERE taskId =?;";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, taskId);
            if (preparedStatement.executeUpdate() >= 1){
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
}
