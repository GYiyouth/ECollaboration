package DAO.team_project;

/**
 * Created by geyao on 2016/12/25.
 */
public class team_projectDAOImpl implements team_projectDAO{
	/**
	 * 添加评价，通过team_projectId, taskId, int access
	 *
	 * @param team_projectId
	 * @param taskId
	 * @param access
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean addAccess(int team_projectId, int taskId, int access) throws Exception {
		return false;
	}

	/**
	 * 获取team_projectId，通过teamId，projectId
	 *
	 * @param teamId
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer getIdByTeamIdProjectId(int teamId, int projectId) throws Exception {
		return null;
	}

	/**
	 * 更新评价，通过teamId，projectId，taskID，access
	 *
	 * @param teamId
	 * @param projectId
	 * @param taskId
	 * @param access
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean updateAccess(int teamId, int projectId, int taskId, int access) throws Exception {
		return false;
	}

	/**
	 * 获取评价，通过teamID，projectId，taskId
	 *
	 * @param teamId
	 * @param projectId
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer getAccess(int teamId, int projectId, int taskId) throws Exception {
		return null;
	}
}
