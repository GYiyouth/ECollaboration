package DAO.maessageDAO;
import bean.domain.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MessageDAO {
	/**
	 * 添加消息，内部生成id，返回消息id
	 * @param messageBean
	 * @return int
	 * @throws SQLException
	 */
	public int addMessage(MessageBean messageBean) throws SQLException;

	/**
	 * 获取MessageBean，通过messageId
	 * @param messageId
	 * @return MessageBean
	 * @throws SQLException
	 */
	public MessageBean getMessageInfo(int messageId) throws SQLException;

	/**
	 * 删除消息，获取MessageBean，通过messageId
	 * @param messageId
	 * @return MessageBean
	 * @throws SQLException
	 */

	
	public MessageBean deleteByMessageId(int messageId) throws SQLException;
	
	/**
	 * 判断该消息是否已经被所有的人阅读，，通过messageId
	 * @param messageId
	 * @return boolean
	 * @throws SQLException
	 */
	
	public boolean getReadFlagOfAllReceiverByMessageId(int messageId) throws SQLException;
	
	/**
	 * 获取SenderId，通过messageId
	 * @param messageId
	 * @return int
	 * @throws SQLException
	 */
	
	public int getSenderIdByMessageId(int messageId) throws SQLException;
	
	/**
	 * 获取发送者UserBean，通过messageId
	 * @param messageId
	 * @return UserBean
	 * @throws SQLException
	 */
	
	public UserBean getSenderInfoByMessageId(int messageId) throws SQLException;
	
	/**
	 * 获取接受人Id列表，通过messageId
	 * @param messageId
	 * @return ArrayList<Integer>
	 * @throws SQLException
	 */
	
	public ArrayList<Integer> getReceiverIdByMessageId(int messageId) throws SQLException;
	
	/**
	 * 获取接收人UserBean列表，通过消息Id
	 * @param messageId
	 * @return ArrayList<UserBean>
	 * @throws SQLException
	 */
	
	public ArrayList<UserBean> getReceiverInfoByMessageId(int messageId) throws SQLException;

}