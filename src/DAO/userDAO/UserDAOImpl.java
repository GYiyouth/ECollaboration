package DAO.userDAO;

import DAO.com.util.db.DBUtils;
import bean.domain.UserBean;

import java.sql.*;

/**
 * Created by GR on 2016/11/9.
 */
public class UserDAOImpl implements UserDAO{

	/**
     * �û���¼���ɹ�����User��ʧ�ܷ��ؿ�
     *
     * @param logName
     * @param passWord
     * @return User
     * @throws SQLException
     */
    @Override
    public UserBean getLogerInfo(String logName, String passWord) throws SQLException {

        UserBean user = new UserBean();
        Connection connection = null;
        PreparedStatement state = null;
        ResultSet rs = null;
        String sql = "select * from user where logName = '"+ logName +"'and passWord = '"+ passWord +"'";
        try{
            connection = DBUtils.getConnetction();
            //preparedStatement = connection.prepareStatement(sql);
            state = connection.prepareStatement(sql);
            rs = state.executeQuery();
            if(rs.next()){
                user.setId(rs.getInt("id"));
                user.setSchoolId(rs.getString("schoolId"));
                user.setName(rs.getString("name"));
                user.setSex(rs.getInt("sex"));
                user.setCharacter(rs.getInt("character"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setLogName(rs.getString("logName"));
                user.setPassWord(rs.getString("passWord"));
                user.setCreateDate(rs.getString("createDate"));
                user.setLastLogTime(rs.getString("lastLogTime"));
                user.setActiveBefore(rs.getString("activeBefore"));
                user.setNewFlag(rs.getInt("newsFlag"));
                return user;
            }else{
                return null;
            }

        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            DBUtils.close(rs, state, connection);
        }
    }

    /**
     * ����û����ڲ�����id�������û�id
     *
     * @param user
     * @return int
     * @throws SQLException
     */
    @Override
    public int addUser(UserBean user) throws SQLException {
        return 0;
    }


    /**
     * ����idѰ���û�������User
     *
     * @param userId@return User
     * @throws SQLException
     */
    @Override
    public UserBean getUserInfoById(int userId) throws SQLException {
   
    	UserBean user = new UserBean();
        Connection connection = null;
        PreparedStatement state = null;
        ResultSet rs = null;
        String sql = "select * from user where id = " + userId;
        try{
            connection = DBUtils.getConnetction();
            //preparedStatement = connection.prepareStatement(sql);
            state = connection.prepareStatement(sql);
            rs = state.executeQuery();
            if(rs.next()){
                user.setId(rs.getInt("id"));
                user.setSchoolId(rs.getString("schoolId"));
                user.setName(rs.getString("name"));
                user.setSex(rs.getInt("sex"));
                user.setCharacter(rs.getInt("character"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setLogName(rs.getString("logName"));
                user.setPassWord(rs.getString("passWord"));
                user.setCreateDate(rs.getString("createDate"));
                user.setLastLogTime(rs.getString("lastLogTime"));
                user.setActiveBefore(rs.getString("activeBefore"));
                user.setNewFlag(rs.getInt("newsFlag"));
                return user;
            }else{
                return null;
            }

        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            DBUtils.close(rs, state, connection);
        }
    }

    /**
     * �޸��û���Ϣ
     *
     * @param user
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean updateInfo(UserBean user) throws SQLException {
        return false;
    }

    /**
     * ɾ���û�
     *
     * @param userId
     * @return User
     * @throws SQLException
     */
    @Override
    public UserBean deleteById(int userId) throws SQLException {
        return null;
    }

}