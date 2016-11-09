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
	 * 根据id寻找消息，返回message
	 * @param messageId
	 * @return MessageBean
	 * @throws SQLException
	 */
	public MessageBean getMessageInfo(int messageId) throws SQLException;

	/**
	 * 得到消息信息
	 * @param messageId
	 * @return MessageBean
	 * @throws SQLException
	 */
	public MessageBean getInfoByMessageId(int messageId) throws SQLException;

	/**
	 * 删除消息
	 * @param messageId
	 * @return MessageBean
	 * @throws SQLException
	 */
	
	public MessageBean deleteByMessageId(int messageId) throws SQLException;
	
	/**
	 * 获取是否所有收件人都阅读了消息
	 * @param messageId
	 * @return boolean
	 * @throws SQLException
	 */
	
	public boolean getReadFlagOfAllReceiverByMessageId(int messageId) throws SQLException;
	
	/**
	 * 获取消息发件人id
	 * @param messageId
	 * @return int
	 * @throws SQLException
	 */
	
	public int getSenderIdByMessageId(int messageId) throws SQLException;
	
	/**
	 * 获取消息发件人信息
	 * @param messageId
	 * @return UserBean
	 * @throws SQLException
	 */
	
	public UserBean getSenderInfoByMessageId(int messageId) throws SQLException;
	
	/**
	 * 获取收件人id
	 * @param messageId
	 * @return ArrayList<Integer>
	 * @throws SQLException
	 */
	
	public ArrayList<Integer> getReceiverIdByMessageId(int messageId) throws SQLException;
	
	/**
	 * 获取收件人信息
	 * @param messageId
	 * @return ArrayList<UserBean>
	 * @throws SQLException
	 */
	
	public ArrayList<UserBean> getReceiverInfoByMessageId(int messageId) throws SQLException;
	
	
	/**
	 * 获取是否阅读了消息
	 * @param messageId
	 * @return boolean
	 * @throws SQLException
	 */
	
	public boolean getReadFlagByMessageId(int messageId) throws SQLException;	
}