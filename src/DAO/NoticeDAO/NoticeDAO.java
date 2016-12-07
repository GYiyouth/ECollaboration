package DAO.NoticeDAO;

import bean.domain.NoticeBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GR on 2016/12/6.
 */
public interface NoticeDAO {
    /**
     * 添加公告，内部生成id，返回公告id
     *
     * @param noticeBean
     * @return noticeId
     * @throws SQLException
     */
    public Integer addNotice(NoticeBean noticeBean) throws SQLException;

    /**
     * 添加到 公告-收件人 的关系表中，
     *
     * @param noticeId,receiverId
     * @return boolean
     * @throws SQLException
     */
    public boolean addNoticeReceiver(int noticeId, List<Integer> receiverIds) throws SQLException;

    /**
     * 获取 NoticeBean，通过noticeId
     *
     * @param noticeId
     * @return NoticeBean
     * @throws SQLException
     */
    public NoticeBean getNoticeInfoByNoticeId(int noticeId) throws SQLException;


    /**
     * 获取creatorId，通过noticeId
     *
     * @param noticeId
     * @return int
     * @throws SQLException
     */

    public Integer getCreatorIdByNoticeId(int noticeId) throws SQLException;

    /**
     * 获取publishId，通过noticeId
     *
     * @param noticeId
     * @return int
     * @throws SQLException
     */

    public Integer getPublishIdByNoticeId(int noticeId) throws SQLException;

    /**
     * 通过creatorId,获取所有公告id
     *
     * @param creatorId
     * @return noticeId
     * @throws SQLException
     */

    public ArrayList<Integer> getNoticeIdByCreatorId(int creatorId) throws SQLException;

    /**
     * 通过publishId,获取所有公告id
     *
     * @param publishId
     * @return noticeId
     * @throws SQLException
     */

    public ArrayList<Integer> getNoticeIdByPublishId(int publishId) throws SQLException;

    /**
     * 通过receiverId,获取所有公告id
     *
     * @param receiverId
     * @return noticeId
     * @throws SQLException
     */

    public ArrayList<Integer> getNoticeIdByReceiverId(int receiverId) throws SQLException;

    /**
     * 通过receiverId,获取所有未读公告id
     *
     * @param receiverId
     * @return noticeId
     * @throws SQLException
     */

    public ArrayList<Integer> getNotReadNoticeIdByReceiverId(int receiverId) throws SQLException;


    /**
     * 获取接受人Id列表，通过noticeId
     *
     * @param noticeId
     * @return ArrayList<Integer>
     * @throws SQLException
     */

    public ArrayList<Integer> getReceiverIdByNoticeId(int noticeId) throws SQLException;

    /**
     * 根据receiverId,noticeId设置已读公告
     *
     * @param receiverId,noticeId
     * @return boolean
     * @throws SQLException
     */

    public boolean updateReadFlagByReceiverId(int receiverId, int noticeId) throws SQLException;


}
