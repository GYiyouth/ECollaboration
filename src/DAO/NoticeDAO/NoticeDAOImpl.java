package DAO.NoticeDAO;

import DAO.com.util.db.DBUtils;
import bean.domain.NoticeBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GR on 2016/12/6.
 */
public class NoticeDAOImpl implements NoticeDAO {

    /**
     * 添加公告，内部生成id，返回公告id
     *
     * @param noticeBean
     * @return noticeId
     * @throws SQLException
     */
    @Override
    public Integer addNotice(NoticeBean noticeBean) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into ecollaborationweb.notice (title,content,creatorId," +
                "publishId,createTime) values(?,?,?,?,?)";
        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setString(1, noticeBean.getTitle());
            ps.setString(2, noticeBean.getContent());
            ps.setInt(3, noticeBean.getCreatorId());
            ps.setInt(4, noticeBean.getPublishId());
            ps.setString(5, noticeBean.getCreateTime());
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
     * 添加到 公告-收件人 的关系表中，
     *
     * @param noticeId
     * @param receiverIds
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean addNoticeReceiver(int noticeId, List<Integer> receiverIds) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into ecollaborationweb.notice_receiver (noticeId, receiverId, readFlag) values(?,?,?);";
        try {
            conn = DBUtils.getConnetction();
            for (int i = 0; i < receiverIds.size(); i++) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, noticeId);
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
     * 获取 NoticeBean，通过noticeId
     *
     * @param noticeId
     * @return NoticeBean
     * @throws SQLException
     */
    @Override
    public NoticeBean getNoticeInfoByNoticeId(int noticeId) throws SQLException {
        NoticeBean noticeBean = new NoticeBean();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from ecollaborationweb.notice where id = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, noticeId);
            rs = ps.executeQuery();
            if (rs.next()) {
                noticeBean.setId(rs.getInt("id"));
                noticeBean.setTitle(rs.getString("title"));
                noticeBean.setContent(rs.getString("content"));
                noticeBean.setCreatorId(rs.getInt("creatorId"));
                noticeBean.setPublishId(rs.getInt("publishId"));
                noticeBean.setCreateTime(rs.getString("createTime"));
                return noticeBean;
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
     * 获取creatorId，通过noticeId
     *
     * @param noticeId
     * @return int
     * @throws SQLException
     */
    @Override
    public Integer getCreatorIdByNoticeId(int noticeId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select creatorId from ecollaborationweb.notice where id = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, noticeId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("creatorId");
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
     * 获取publishId，通过noticeId
     *
     * @param noticeId
     * @return int
     * @throws SQLException
     */
    @Override
    public Integer getPublishIdByNoticeId(int noticeId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select publishId from ecollaborationweb.notice where id = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, noticeId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("publishId");
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
     * 通过creatorId,获取所有公告id
     *
     * @param creatorId
     * @return noticeId
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getNoticeIdByCreatorId(int creatorId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id from ecollaborationweb.notice where creatorId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, creatorId);
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
     * 通过publishId,获取所有公告id
     *
     * @param publishId
     * @return noticeId
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getNoticeIdByPublishId(int publishId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id from ecollaborationweb.notice where publishId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, publishId);
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
     * 通过receiverId,获取所有公告id
     *
     * @param receiverId
     * @return noticeId
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getNoticeIdByReceiverId(int receiverId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select noticeId from ecollaborationweb.notice_receiver where receiverId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, receiverId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("noticeId"));
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
     * 通过receiverId,获取所有未读公告id
     *
     * @param receiverId
     * @return noticeId
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getNotReadNoticeIdByReceiverId(int receiverId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select noticeId from ecollaborationweb.notice_receiver where receiverId = ? and readFlag = 0";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, receiverId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("noticeId"));
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
     * 获取接受人Id列表，通过noticeId
     *
     * @param noticeId
     * @return ArrayList<Integer>
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getReceiverIdByNoticeId(int noticeId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select receiverId from ecollaborationweb.notice_receiver where noticeId = ?";

        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, noticeId);
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
     * 根据receiverId,noticeId设置已读公告
     *
     * @param receiverId
     * @param noticeId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean updateReadFlagByReceiverIdNoticeId(int receiverId, int noticeId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update ecollaborationweb.notice_receiver set readFlag=1 where receiverId = ? and noticeId=?;";
        try {
            conn = DBUtils.getConnetction();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, receiverId);
            ps.setInt(2, noticeId);
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
