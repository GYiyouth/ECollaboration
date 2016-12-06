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
            DBUtils.close(rs, ps, conn);
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
        Connection conn = null;
        PreparedStatement ps = null;
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
            throw e;
        } finally {
            DBUtils.close(null, ps, conn);
        }
    }


    /**
     * 获取MessageBean，通过messageId
     *
     * @param messageId
     * @return MessageBean
     * @throws SQLException
     */
    @Override
    public MessageBean getMessageInfoByMessageId(int messageId) throws SQLException {
        MessageBean messageBean = new MessageBean();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from ecollaborationweb.message where id = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, messageId);
            rs = ps.executeQuery();
            if (rs.next()) {
                messageBean.setId(rs.getInt("id"));
                messageBean.setTitle(rs.getString("title"));
                messageBean.setContent(rs.getString("content"));
                messageBean.setCreateTime(rs.getString("createTime"));
                messageBean.setSenderId(rs.getInt("senderId"));
                messageBean.setReadFlag(rs.getInt("readFlag"));
                messageBean.setDeadDate(rs.getString("deadDate"));
                return messageBean;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
    }

    /**
     * 判断该消息是否已经被所有的人阅读，，通过messageId
     *
     * @param messageId
     * @return Integer
     * @throws SQLException
     */
    @Override
    public Integer getReadFlagOfAllReceiverByMessageId(int messageId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select readFlag from ecollaborationweb.message where id = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, messageId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("readFlag");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
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
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select senderId from ecollaborationweb.message where id = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, messageId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("senderId");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
    }

    /**
     * 获取messageId，通过SenderId
     *
     * @param senderId
     * @return int
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getMessageIdBySenderId(int senderId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id from ecollaborationweb.message where senderId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, senderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
        return list;
    }


    /**
     * 通过receiverId,获取所有消息id
     *
     * @param receiverId
     * @return messageId
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getMessageIdByReceiverId(int receiverId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id from ecollaborationweb.message_receiver where receiverId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, receiverId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
        return list;
    }

    /**
     * 通过receiverId,获取所有未读消息id
     *
     * @param receiverId
     * @return messageId
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getNotReadMessageIdByReceiverId(int receiverId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id from ecollaborationweb.message_receiver where receiverId = ? and readFlag = 0";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, receiverId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
        return list;
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
        ArrayList<Integer> list = new ArrayList<Integer>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select receiverId from ecollaborationweb.message_receiver where messageId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, messageId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("receiverId"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
    }


    /**
     * 根据receiverId设置已读消息
     *
     * @param receiverId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean updateReadFlagByReceiverId(int receiverId, int messageId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update ecollaborationweb.message_receiver set readFlag=1 where receiverId = ? and messageId=?;";
        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, receiverId);
            ps.setInt(2, messageId);
            if (ps.executeUpdate() == 0)
                return false;
            else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(null, ps, conn);
        }
    }
}
