package DAO.ECFileDAO;

import bean.domain.ECFileBean;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 与student project teacher team相连
 * Created by geyao on 2016/11/8.
 */
public interface ECFileDAO {
	/**
	 * 添加文件，返回id
	 * @param ecFileBean
	 * @return
	 * @throws SQLException
	 */
	public Integer addFile(ECFileBean ecFileBean) throws SQLException;

	/**
	 * 添加头像，返回id,通过用户id
	 * @param ecFileBean, userId
	 * @return
	 * @throws SQLException
	 */
	public Integer addPhoto(ECFileBean ecFileBean, Integer userId) throws SQLException;

	/**
	 * 获取头像id，通过用户id
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public Integer getPhotoId(Integer userId) throws SQLException;
	/**
	 * 获取文件，通过文件id
	 * @param fileId
	 * @return
	 * @throws SQLException
	 */
	public ECFileBean getFileInfo(Integer fileId) throws SQLException;

	/**
	 * 修改文件
	 * @param ecFileBean
	 * @return
	 * @throws SQLException
	 */
	public boolean updateFile(ECFileBean ecFileBean) throws SQLException;

	/**
	 * 删除文件，返回被删除的文件
	 * @param fileId
	 * @return
	 * @throws SQLException
	 */
	public ECFileBean deleteFile(Integer fileId) throws SQLException;



	/**
	 * 获取文件id列表，通过学生id
	 * @param creatorId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getFileIdListByCreatorId(Integer creatorId) throws SQLException;

	/**
	 * 获取文件id列表，通过教师id
	 *
	 * @param teacherId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getFileIdListByTeacherId(Integer teacherId) throws SQLException;

	/**
	 * 获取文件id列表，通过项目id
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getFileIdListByProjectId(Integer projectId) throws SQLException;

	/**
	 * 获取文件id列表，通过团队id
	 * @param teamId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getFileIdListByTeamId(Integer teamId) throws SQLException;

	/**
	 * 获取文件id列表，通过创建者id，项目id
	 * @param creatorId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getFileIdListByCreatorIdProjectId(Integer creatorId, Integer projectId) throws SQLException;

	/**
	 * 获取文件列表，通过教师id，项目id
	 * @param teacherId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getFileIdListByTeacherIdProjectId(Integer teacherId, Integer projectId) throws SQLException;

	/**
	 * 获取文件列表，根据团队id，项目id
	 * @param teamId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getFileIdLIstByTeamIdProjectId(Integer teamId, Integer projectId) throws SQLException;
}
