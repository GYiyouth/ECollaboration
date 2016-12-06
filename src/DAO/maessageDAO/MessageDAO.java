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
     * @param messageBean
     * @return messageId
     * @throws SQLException
     */
    public Integer addMessage(MessageBean messageBean) throws SQLException;

    /**
     * 添加到 消息-收件人 的关系表中，
     * @param messageId,receiverId
     * @return boolean
     * @throws SQLException
     */
    public boolean addMessageReceiver(int messageId,List<Integer> receiverIds) throws SQLException;

    /**
     * 获取MessageBean，通过messageId
     * @param messageId
     * @return MessageBean
     * @throws SQLException
     */
    public MessageBean getMessageInfo(int messageId) throws SQLException;

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

    public Integer getSenderIdByMessageId(int messageId) throws SQLException;

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
