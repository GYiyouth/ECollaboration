package DAO.maessageDAO;

import DAO.com.util.db.DBUtils;
import bean.domain.MessageBean;
import bean.domain.UserBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GR on 2016/12/5.
 */
public class MessageDAOImpl implements MessageDAO {

    /**
     * 添加消息，内部生成id，返回消息id
     *
     * @param messageBean
     * @return messageId
     * @throws SQLException
     */
    @Override
    public Integer addMessage(MessageBean messageBean) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into ecollaborationweb.message (title,content,createTime," +
                "senderId,readFlag,deadDate) values(?,?,?,?,?,?);";
        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setString(1, messageBean.getTitle());
            ps.setString(2, messageBean.getContent());
            ps.setString(3, messageBean.getCreateTime());
            ps.setInt(4, messageBean.getSenderId());
            ps.setInt(5, messageBean.getReadFlag());
            ps.setString(6, messageBean.getDeadDate());
            int i = ps.executeUpdate();
            if (i != 0) {
                sql = "SELECT LAST_INSERT_ID()";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(null, ps, conn);
        }
    }



    /**
     * 添加到 消息-收件人 的关系表中，
     *
     * @param messageId
     * @param receiverIds
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean addMessageReceiver(int messageId, List<Integer> receiverIds) throws SQLException {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into ecollaborationweb.message_receiver (messageId, receiverId, readFlag) values(?,?,?);";
        try {
            conn = DBUtils.getConnetction();
            for (int i = 0; i < receiverIds.size(); i++) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, messageId);
                ps.setInt(2, receiverIds.get(i));
                ps.setInt(3, 0);
                if (ps.executeUpdate() == 0) {
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, ps, conn);
        }
        return flag;
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
    public Integer getSenderIdByMessageId(int messageId) throws SQLException {
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
