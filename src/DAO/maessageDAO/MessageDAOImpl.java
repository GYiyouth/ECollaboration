package DAO.maessageDAO;

import bean.domain.MessageBean;
import bean.domain.UserBean;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by GR on 2016/12/5.
 */
public class MessageDAOImpl implements MessageDAO{
    /**
     * 添加消息，内部生成id，返回消息id
     *
     * @param messageBean
     * @return int
     * @throws SQLException
     */
    @Override
    public int addMessage(MessageBean messageBean) throws SQLException {
        return 0;
    }

    /**
     * 获取MessageBean，通过messageId
     *
     * @param messageId
     * @return MessageBean
     * @throws SQLException
     */
    @Override
    public MessageBean getMessageInfo(int messageId) throws SQLException {
        return null;
    }

    /**
     * 删除消息，获取MessageBean，通过messageId
     *
     * @param messageId
     * @return MessageBean
     * @throws SQLException
     */
    @Override
    public MessageBean deleteByMessageId(int messageId) throws SQLException {
        return null;
    }

    /**
     * 判断该消息是否已经被所有的人阅读，，通过messageId
     *
     * @param messageId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean getReadFlagOfAllReceiverByMessageId(int messageId) throws SQLException {
        return false;
    }

    /**
     * 获取SenderId，通过messageId
     *
     * @param messageId
     * @return int
     * @throws SQLException
     */
    @Override
    public int getSenderIdByMessageId(int messageId) throws SQLException {
        return 0;
    }

    /**
     * 获取发送者UserBean，通过messageId
     *
     * @param messageId
     * @return UserBean
     * @throws SQLException
     */
    @Override
    public UserBean getSenderInfoByMessageId(int messageId) throws SQLException {
        return null;
    }

    /**
     * 获取接受人Id列表，通过messageId
     *
     * @param messageId
     * @return ArrayList<Integer>
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getReceiverIdByMessageId(int messageId) throws SQLException {
        return null;
    }

    /**
     * 获取接收人UserBean列表，通过消息Id
     *
     * @param messageId
     * @return ArrayList<UserBean>
     * @throws SQLException
     */
    @Override
    public ArrayList<UserBean> getReceiverInfoByMessageId(int messageId) throws SQLException {
        return null;
    }
}
