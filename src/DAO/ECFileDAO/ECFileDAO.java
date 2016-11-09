package DAO.ECFileDAO;

import bean.domain.ECFile;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 与student project teacher team相连
 * Created by geyao on 2016/11/8.
 */
public interface ECFileDAO {
	/**
	 * 添加文件，返回id
	 * @param ecFile
	 * @return
	 * @throws SQLException
	 */
	public Integer addFile(ECFile ecFile) throws SQLException;

	/**
	 * 添加头像，返回id,通过用户id
	 * @param ecFile, userId
	 * @return
	 * @throws SQLException
	 */
	public Integer addPhoto(ECFile ecFile, Integer userId) throws SQLException;

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
	public ECFile getFileInfo(Integer fileId) throws SQLException;

	/**
	 * 修改文件
	 * @param ecFile
	 * @return
	 * @throws SQLException
	 */
	public boolean updateFile(ECFile ecFile) throws SQLException;

	/**
	 * 删除文件，返回被删除的文件
	 * @param fileId
	 * @return
	 * @throws SQLException
	 */
	public ECFile deleteFile(Integer fileId) throws SQLException;

	/**
	 * 获取头像id，通过用户id
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public Integer getFileIdByUserId(Integer userId) throws SQLException;


	/**
	 * 获取文件id列表，通过学生id
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getFileIdListByStudentId(Integer studentId) throws SQLException;

	/**
	 * 获取文件id列表，通过教师id
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
	 * 获取文件id列表，通过学生id，项目id
	 * @param studentId
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getFileIdListByStudentIdProjectId(Integer studentId, Integer projectId) throws SQLException;

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
