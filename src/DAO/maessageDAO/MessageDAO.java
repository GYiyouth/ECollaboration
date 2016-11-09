package DAO.maessageDAO;
import bean.domain.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MessageDAO {
	/**
	 * �����Ϣ���ڲ�����id��������Ϣid
	 * @param messageBean
	 * @return int
	 * @throws SQLException
	 */
	public int addMessage(MessageBean messageBean) throws SQLException;

	/**
	 * ����idѰ����Ϣ������message
	 * @param messageId
	 * @return MessageBean
	 * @throws SQLException
	 */
	public MessageBean getMessageInfo(int messageId) throws SQLException;

	/**
	 * �õ���Ϣ��Ϣ
	 * @param messageId
	 * @return MessageBean
	 * @throws SQLException
	 */
	public MessageBean getInfoByMessageId(int messageId) throws SQLException;

	/**
	 * ɾ����Ϣ
	 * @param messageId
	 * @return MessageBean
	 * @throws SQLException
	 */
	
	public MessageBean deleteByMessageId(int messageId) throws SQLException;
	
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
	 * @return UserBean
	 * @throws SQLException
	 */
	
	public UserBean getSenderInfoByMessageId(int messageId) throws SQLException;
	
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
	 * @return ArrayList<UserBean>
	 * @throws SQLException
	 */
	
	public ArrayList<UserBean> getReceiverInfoByMessageId(int messageId) throws SQLException;
	
	
	/**
	 * ��ȡ�Ƿ��Ķ�����Ϣ
	 * @param messageId
	 * @return boolean
	 * @throws SQLException
	 */
	
	public boolean getReadFlagByMessageId(int messageId) throws SQLException;	
}