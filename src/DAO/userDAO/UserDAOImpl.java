package DAO.userDAO;

import DAO.com.util.db.DBUtils;
import bean.domain.UserBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

/**
 * Created by GR on 2016/11/9.
 */
public class UserDAOImpl implements UserDAO{


    /**
     * 获取UserBean，通过登录名，密码
     * @param logName,passWord
     * @param passWord
     * @return
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
     * 添加用户，内部生成id，返回用户id
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
     * 根据id寻找用户，返回User
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
     * 修改用户信息
     * @param user
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean updateInfo(UserBean user) throws SQLException {
        return false;
    }

    /**
     *
     *
     * @param userId
     * @return User
     * @throws SQLException
     */
    @Override
    public UserBean deleteById(int userId) throws SQLException {
        return null;
    }

    /**
     * 设置用户头像，根据userBean，file
     *
     * @param userBean
     * @param file
     * @return
     * @throws SQLException
     */
    @Override
    public boolean setUserPhoto(UserBean userBean, File file) throws SQLException , FileNotFoundException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
        String sql = "UPDATE ECollaborationweb.user SET photo = ? WHERE id = ?;";
        try {
            InputStream inputStream = new FileInputStream(file);
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBinaryStream(  1, inputStream, file.length());
            preparedStatement.setInt(           2, userBean.getId());
            int flag = preparedStatement.executeUpdate();
            if (flag == 1)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            DBUtils.close(null, preparedStatement, connection);
        }



    }

    /**
     * 获取File，头像，通过UserBean
     *
     * @param userBean
     * @return
     * @throws SQLException
     */
    @Override
    public File getUserPhoto(UserBean userBean) throws SQLException,FileNotFoundException {
        return null;
    }
}