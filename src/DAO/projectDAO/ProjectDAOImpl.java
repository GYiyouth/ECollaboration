package DAO.projectDAO;

import bean.domain.ProjectBean;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by GR on 2016/11/30.
 */
public class ProjectDAOImpl implements ProjectDAO{
    @Override
    public int addProject(ProjectBean projectBean) throws SQLException {
        return 0;
    }

    @Override
    public ProjectBean getProjectInfo(int projectId) throws SQLException {
        return null;
    }

    @Override
    public boolean updateInfo(ProjectBean projectBean) throws SQLException {
        return false;
    }

    @Override
    public ProjectBean deleteById(int projectId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getProjectIdListByTeacherId(int teacherId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getProjectIdListByFileId(int fileId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getProjectIdListByFileIdTeacherId(int fileId, int teacherId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getProjectIdListByFileIdStudentId(int fileId, int studentId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getProjectIdListByPlanId(int planId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getProjectIdListByPlanIdTeacherId(int planId, int teacherId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getProjectIdListByPlanIdStudentId(int planId, int studentId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getProjectIdListByStudentId(int studentId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getProjectIdListByCodeId(int codeId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getProjectIdListByCodeIdStudentId(int codeId, int StudentId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getProjectIdListByTeamId(int teamId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getProjectIdListByTaskId(int taskId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getProjectIdListByTaskIdTeamId(int taskId, int teamId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getProjectIdListByTaskIdTeacherId(int taskId, int teacherId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getProjectIdListByTaskIdTeacherIdTeamId(int taskId, int teacherId, int teamId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getProjectIdListByTeacherIdTeamId(int teacherId, int teamId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getTeamIdList(int projectId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getAllTaskIdList(int projectId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getTaskIdListOfOneTeam(int projectId, int teamId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getTeacherId(int projectId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getAllCodeIdList(int projectId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getCodeIdListOfOneTeam(int projectId, int teamId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getCodeIdListOfOneStudent(int projectId, int teamId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getStudentIdList(int projectId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getPlanIdListByStudentId(int projectId, int studentId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getAllPlanIdList(int projectId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getFileIdListByStudentId(int projectId, int studentId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Integer> getAllFileIdList(int projectId) throws SQLException {
        return null;
    }
}
