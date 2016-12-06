package DAO.maessageDAO;

import bean.domain.MessageBean;
import bean.domain.UserBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GR on 2016/12/5.
 */
public interface MessageDAO {
    /**
     * 添加消息，内部生成id，返回消息id
     *
     * @param messageBean
     * @return messageId
     * @throws SQLException
     */
    public Integer addMessage(MessageBean messageBean) throws SQLException;

    /**
     * 添加到 消息-收件人 的关系表中，
     *
     * @param messageId,receiverId
     * @return boolean
     * @throws SQLException
     */
    public boolean addMessageReceiver(int messageId, List<Integer> receiverIds) throws SQLException;

    /**
     * 获取MessageBean，通过messageId
     *
     * @param messageId
     * @return MessageBean
     * @throws SQLException
     */
    public MessageBean getMessageInfoByMessageId(int messageId) throws SQLException;

    /**
     * 判断该消息是否已经被所有的人阅读，，通过messageId
     *
     * @param messageId
     * @return Integer
     * @throws SQLException
     */

    public Integer getReadFlagOfAllReceiverByMessageId(int messageId) throws SQLException;

    /**
     * 获取SenderId，通过messageId
     *
     * @param messageId
     * @return int
     * @throws SQLException
     */

    public Integer getSenderIdByMessageId(int messageId) throws SQLException;

    /**
     * 获取messageId，通过SenderId
     *
     * @param senderId
     * @return int
     * @throws SQLException
     */

    public ArrayList<Integer> getMessageIdBySenderId(int senderId) throws SQLException;

    /**
     * 通过receiverId,获取所有消息id
     *
     * @param receiverId
     * @return messageId
     * @throws SQLException
     */

    public ArrayList<Integer> getMessageIdByReceiverId(int receiverId) throws SQLException;

    /**
     * 通过receiverId,获取所有未读消息id
     *
     * @param receiverId
     * @return messageId
     * @throws SQLException
     */

    public ArrayList<Integer> getNotReadMessageIdByReceiverId(int receiverId) throws SQLException;

    /**
     * 获取接受人Id列表，通过messageId
     *
     * @param messageId
     * @return ArrayList<Integer>
     * @throws SQLException
     */

    public ArrayList<Integer> getReceiverIdByMessageId(int messageId) throws SQLException;

    /**
     * 根据receiverId设置已读消息
     *
     * @param receiverId,messageId
     * @return boolean
     * @throws SQLException
     */

    public boolean updateReadFlagByReceiverId(int receiverId, int messageId) throws SQLException;

}