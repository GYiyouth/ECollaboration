package DAO.taskDAO;

import DAO.com.smallTools.ComGetListValueDAO;
import DAO.com.smallTools.ComGetListValueDAOImpl;
import DAO.com.util.db.DBUtils;
import bean.domain.TaskBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by GR on 2016/12/5.
 */
public class TaskDAOImpl implements TaskDAO {
    /**
     * 添加任务，获取新任务id
     *
     * @param taskBean
     * @return
     * @throws SQLException
     */
    @Override
    public Integer addTask(TaskBean taskBean) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO ECollaborationWeb.task " +
                " (title, content, creatorId, createDate, " +
                " modifyDate, beginDate, targetDate) VALUES (?,?,?,?,?,?,?);";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, taskBean.getTitle());
            preparedStatement.setString(2, taskBean.getContent());
            preparedStatement.setInt(3, taskBean.getCreatorId());
            preparedStatement.setString(4, taskBean.getCreateDate());
            preparedStatement.setString(5, taskBean.getModifyDate());
            preparedStatement.setString(6, taskBean.getBeginDate());
            preparedStatement.setString(7, taskBean.getTargetDate());
            int flag = preparedStatement.executeUpdate();
            if (flag == 1) {
                sql = "SELECT LAST_INSERT_ID()";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                return resultSet.getInt(1);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 删除任务，获取此任务Task对象
     *
     * @param taskId
     * @return
     * @throws SQLException
     */
    @Override
    public boolean deleteTask(int taskId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM ECollaborationWeb.task WHERE id = ?;";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, taskId);
            int flag = preparedStatement.executeUpdate();
            if (flag == 1)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(null, preparedStatement, connection);
        }
    }

    /**
     * 修改任务，获取bool变量
     *
     * @param taskBean
     * @return
     * @throws SQLException
     */
    @Override
    public boolean updateTask(TaskBean taskBean) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE ECollaborationWeb.task SET title = ?, " +
                " content = ?, creatorId = ?, createDate = ?, modifyDate = ?, " +
                "beginDate = ?, targetDate = ? WHERE id = ?;";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, taskBean.getTitle());
            preparedStatement.setString(2, taskBean.getContent());
            preparedStatement.setInt(3, taskBean.getCreatorId());
            preparedStatement.setString(4, taskBean.getCreateDate());
            preparedStatement.setString(5, taskBean.getModifyDate());
            preparedStatement.setString(6, taskBean.getBeginDate());
            preparedStatement.setString(7, taskBean.getTargetDate());
            preparedStatement.setInt(8, taskBean.getId());
            int flag = preparedStatement.executeUpdate();
            if (flag == 1)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(null, preparedStatement, connection);
        }
    }

    /**
     * 获取Task对象，通过TaskId
     *
     * @param taskId
     * @return
     * @throws SQLException
     */
    @Override
    public TaskBean getTaskInfo(int taskId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TaskBean taskBean = new TaskBean();
        String sql = "SELECT * FROM  ECollaborationWeb.task WHERE id = ?;";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, taskId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                taskBean.setTitle(resultSet.getString("title"));
                taskBean.setContent(resultSet.getString("content"));
                taskBean.setCreatorId(resultSet.getInt("creatorId"));
                taskBean.setCreateDate(resultSet.getString("createDate"));
                taskBean.setModifyDate(resultSet.getString("modifyDate"));
                taskBean.setBeginDate(resultSet.getString("beginDate"));
                taskBean.setTargetDate(resultSet.getString("targetDate"));
                taskBean.setId(taskId);
                return taskBean;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 获取任务id列表，通过团队id
     *
     * @param teamId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTaskIdListByTeamId(int teamId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Integer> list = new ArrayList<>();
        String sql = "SELECT pt.taskId FROM ECollaborationWeb.project_task as pt," +
                " ECollaborationWeb.team_project AS tp " +
                " WHERE pt.projectId = tp.projectId AND tp.teamId = ?;";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, teamId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getInt(1));
            }
            if (list.size() > 0) {
                HashSet h = new HashSet(list);
                list.clear();
                list.addAll(h);
                return list;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 获取任务id列表，通过教师id
     *
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTaskIdListByTeacherId(int teacherId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Integer> list = new ArrayList<>();
        String sql = "SELECT pt.taskId FROM ECollaborationWeb.project_task as pt," +
                " ECollaborationWeb.teacher_project AS tp " +
                " WHERE pt.projectId = tp.projectId AND tp.teacherId = ?;";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, teacherId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getInt(1));
            }
            if (list.size() > 0) {
                HashSet h = new HashSet(list);
                list.clear();
                list.addAll(h);
                return list;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 获取任务id列表，通过项目id
     *
     * @param projectId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTaskIdListByProjectId(int projectId) throws SQLException {
        ComGetListValueDAO comGetListValueDAO = new ComGetListValueDAOImpl<Integer, Integer>();
        return comGetListValueDAO.getListAfromBbyC("id", "projectId", projectId, "project_task");
    }

//    /**
//     * 获取任务id列表，通过团队id，项目id
//     *
//     * @param teamId
//     * @param projectId
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public ArrayList<Integer> getTaskIdListByTeamIdProjectId(int teamId, int projectId) throws SQLException {
//        return null;
//    }

    /**
     * 获取任务id列表，通过团队id，教师id
     *
     * @param teamId
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getTaskIdListByTeamIdTeacherId(int teamId, int teacherId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Integer> list = new ArrayList<>();
        String sql = "SELECT taskId FROM ECollaborationWeb.project_task AS pjt, " +
                " ECollaborationWeb.teacher_project AS  tcp, " +
                " ECollaborationWeb.team_project AS  tp " +
                " WHERE tp.teamId = ? AND tcp.teacherId = ? AND tp.teamId = tcp = teamId " +
                " AND pjt.projectId = tcp.projectId AND pjt.projectId = tp.projectId;";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, teamId);
            preparedStatement.setInt(2, teacherId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getInt(1));
            }
            if (list.size() > 0){
                HashSet h = new HashSet(list);
                list.clear();
                list.addAll(h);
                return list;
            }else
                return null;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            DBUtils.close(resultSet, preparedStatement, connection);
        }
    }

//    /**
//     * 获取任务Id, 通过项目id，教师id
//     *
//     * @param projectId
//     * @param teacherId
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public ArrayList<Integer> getTaskIdListByProjectIdTeacherId(int projectId, int teacherId) throws SQLException {
//        return null;
//    }
//
//    /**
//     * 获取任务id，通过教师id，项目id，团队id
//     *
//     * @param teacherId
//     * @param projectId
//     * @param teamId
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public ArrayList<Integer> getTaskIdListByTeacherIdProjectIdTeamId(int teacherId, int projectId, int teamId) throws SQLException {
//        return null;
//    }

}