package DAO.maessageDAO;
import bean.domain.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MessageDAO {
	/**
	 * �����Ϣ���ڲ�����id��������Ϣid
	 * @param message
	 * @return int
	 * @throws SQLException
	 */
	public int addMessage(Message message) throws SQLException;

	/**
	 * ����idѰ����Ϣ������message
	 * @param messageId
	 * @return Message
	 * @throws SQLException
	 */
	public Message getMessageInfo(int messageId) throws SQLException;

	/**
	 * �õ���Ϣ��Ϣ
	 * @param messageId
	 * @return Message
	 * @throws SQLException
	 */
	public Message getInfoByMessageId(int messageId) throws SQLException;

	/**
	 * ɾ����Ϣ
	 * @param messageId
	 * @return Message
	 * @throws SQLException
	 */
	
	public Message deleteByMessageId(int messageId) throws SQLException;
	
	/**
	 * ��ȡ�Ƿ������ռ��˶��Ķ�����Ϣ
	 * @param messageId
	 * @return boolean
	 * @throws SQLException
	 */
	
	public boolean getReadFlagOfAllReceiverByMessageId(int messageId) throws SQLException;
	
	/**
	 * ��ȡ��Ϣ������id
	 * @param messageId
	 * @return int
	 * @throws SQLException
	 */
	
	public int getSenderIdByMessageId(int messageId) throws SQLException;
	
	/**
	 * ��ȡ��Ϣ��������Ϣ
	 * @param messageId
	 * @return User
	 * @throws SQLException
	 */
	
	public User getSenderInfoByMessageId(int messageId) throws SQLException;
	
	/**
	 * ��ȡ�ռ���id
	 * @param messageId
	 * @return ArrayList<Integer>
	 * @throws SQLException
	 */
	
	public ArrayList<Integer> getReceiverIdByMessageId(int messageId) throws SQLException;
	
	/**
	 * ��ȡ�ռ�����Ϣ
	 * @param messageId
	 * @return ArrayList<User>
	 * @throws SQLException
	 */
	
	public ArrayList<User> getReceiverInfoByMessageId(int messageId) throws SQLException;
	
	
	/**
	 * ��ȡ�Ƿ��Ķ�����Ϣ
	 * @param messageId
	 * @return boolean
	 * @throws SQLException
	 */
	
	public boolean getReadFlagByMessageId(int messageId) throws SQLException;	
}