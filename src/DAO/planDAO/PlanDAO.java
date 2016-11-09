package DAO.planDAO;

import ustc.bean.Plan;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlanDAO {
	/**
	 * ��Ӽƻ�����ȡ�¼ƻ�id
	 * @param plan
	 * @return
	 * @throws SQLException
	 */
	public Integer addPlan(Plan plan) throws SQLException;

	/**
	 * ɾ���ƻ�����ȡ�˼ƻ�Plan����
	 * @param planId
	 * @return
	 * @throws SQLException
	 */
	public Plan deletePlan(int planId) throws SQLException;

	/**
	 * �޸ļƻ�����ȡbool����
	 * @param plan
	 * @return
	 * @throws SQLException
	 */
	public boolean updatePlan(Plan plan) throws SQLException;

	/**
	 * ��ȡPlan����ͨ��PlanId
	 * @param planId
	 * @return
	 * @throws SQLException
	 */
	public Plan getPlanInfo(int planId) throws SQLException;


	/**
	 * ��ȡ�ƻ�id�б�ͨ���Ŷ�id
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getPlanIdListByTeamId(int teamId)throws SQLException;

	/**
	 * ��ȡ�ƻ�id�б�ͨ����ʦid
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getPlanIdListByTeacherId(int teacherId) throws SQLException;

	/**
	 * ��ȡ�ƻ�id�б�ͨ����Ŀid
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getPlanIdListByProjectId(int projectId) throws SQLException;

	/**
	 * ��ȡ�ƻ�id�б�ͨ���Ŷ�id����Ŀid
	 * @param teamId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getPlanIdListByTeamIdProjectId(int teamId, int projectId) throws SQLException;

	/**
	 * ��ȡ�ƻ�id�б�ͨ���Ŷ�id����ʦid
	 * @param teamId
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getPlanIdListByTeamIdTeacherId(int teamId, int teacherId) throws SQLException;

	/**
	 * ��ȡ�ƻ�Id, ͨ����Ŀid����ʦid
	 * @param projectId
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getPlanIdListByProjectIdTeacherId(int projectId, int teacherId) throws SQLException;

	/**
	 * ��ȡ�ƻ�id��ͨ����ʦid����Ŀid���Ŷ�id
	 * @param teacherId
	 * @param projectId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getPlanIdListByTeacherIdProjectIdTeamId(int teacherId, int projectId, int teamId) throws SQLException;


	/**
	 * ��ȡ�Ŷ�id�б�ͨ���ƻ�id
	 * @param planId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdList(int planId) throws SQLException;

	/**
	 * ��ȡ�Ŷ�id�б�ͨ���ƻ�id,��Ŀid
	 * @param planId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByProjectId(int planId, int projectId) throws SQLException;

	/**
	 * ��ȡ�Ŷ�id�б�ͨ���ƻ�id����ʦid
	 * @param planId
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByTeacherId(int planId, int teacherId) throws SQLException;

	/**
	 * ��ȡ�Ŷ�id�б�ͨ���ƻ�id����ʦid����Ŀid
	 * @param planId
	 * @param teacherId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeamIdListByTeacherIdProjectId(int planId, int teacherId, int projectId) throws SQLException;

	/**
	 * ��ȡ��ʦid�б�ͨ���ƻ�id
	 * @param planId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeacherIdList(int planId) throws SQLException;

	/**
	 * ��ȡ��ʦid�б�ͨ���ƻ�id���Ŷ�id
	 * @param planId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeacherIdListByTeamId(int planId, int teamId) throws SQLException;

	/**
	 * ��ȡ��ʦid�б�ͨ���ƻ�id����Ŀid
	 * @param planId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeacherIdListByProjectId(int planId, int projectId)throws SQLException;

	/**
	 * ��ȡ��ʦid�б�ͨ���ƻ�id���Ŷ�id����Ŀid
	 * @param planId
	 * @param teamId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getTeacherIdListByTeamIdProjectId(int planId, int teamId, int projectId) throws SQLException;

	/**
	 * ��ȡ��Ŀid�б�ͨ���ƻ�id
	 * @param planId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdList(int planId) throws SQLException;

	/**
	 * ��ȡ��Ŀid�б�ͨ���ƻ�id����Ŀid
	 * @param planId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByTeamId(int planId, int teamId) throws SQLException;
	/**
	 * ��ȡ��Ŀid�б�ͨ���ƻ�id����ʦid
	 * @param planId
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByTeacherId(int planId, int teacherId) throws SQLException;

	/**
	 * ��ȡ��Ŀid�б�ͨ���ƻ�id���Ŷ�id����ʦid
	 * @param planId
	 * @param teamId
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByTeamIdTeacherId(int planId, int teamId, int teacherId) throws SQLException;

	/**
	 * ��ȡ��Ŀid�б�ͨ���ƻ�id,�Ŷ�id
	 * @param planId
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getProjectIdListByTeamIdPlanId(int planId, int teamId)throws SQLException;
}
