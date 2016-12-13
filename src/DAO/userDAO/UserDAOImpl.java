package DAO.userDAO;

import DAO.com.util.db.DBUtils;
import bean.domain.UserBean;

import java.io.*;
import java.sql.*;
import java.util.TreeMap;

/**
 * Created by GR on 2016/11/9.
 */
public class UserDAOImpl implements UserDAO{


    /**
     * 获取UserBean，通过登录名，密码
     * 获取到的信息不含头像信息。
     * @param logName,passWord
     * @param passWord
     * @return
     * @throws SQLException
     */
    @Override
    public UserBean getLogInfo(String logName, String passWord) throws SQLException {

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
                user.setCharacter(rs.getInt("role"));
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
    public Integer addUser(UserBean user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        Integer id = null;
        String sql = "INSERT INTO ECollaborationWeb.user ( " +
                " schoolId, name, sex, role, email, phoneNumber, " +
                " logName, passWord, createDate, " +
                " lastLogTime, activeBefore, newsFlag) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            connection = DBUtils.getConnetction();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getSchoolId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setInt(   3, user.getSex());
            preparedStatement.setInt(   4, user.getCharacter());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhoneNumber());
            preparedStatement.setString(7, user.getLogName());
            preparedStatement.setString(8, user.getPassWord());
            preparedStatement.setString(9, user.getCreateDate());
//            preparedStatement.setString(10, user.getPhoto());
            preparedStatement.setString(10, user.getLastLogTime());
            preparedStatement.setString(11, user.getActiveBefore());
            preparedStatement.setInt(   12, user.getNewFlag());
            int flag = preparedStatement.executeUpdate();
            if (flag == 1){
                sql = "SELECT LAST_INSERT_ID();";
	            preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getInt(1);
                }
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            DBUtils.close(resultSet, preparedStatement, connection);
        }

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
                user.setCharacter(rs.getInt("role"));
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
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet= null;
	    Integer id = null;
	    String sql = "UPDATE ECollaborationWeb.user SET " +
			    " schoolId = ?, name = ?, sex = ?, role = ?, email = ?, phoneNumber = ?, " +
			    " logName = ?, passWord = ?, createDate = ?, " +
			    " lastLogTime = ?, activeBefore = ?, newsFlag = ? WHERE id = ? ;";
	    try {
		    connection = DBUtils.getConnetction();
		    preparedStatement = connection.prepareStatement(sql);
		    preparedStatement.setString(1, user.getSchoolId());
		    preparedStatement.setString(2, user.getName());
		    preparedStatement.setInt(   3, user.getSex());
		    preparedStatement.setInt(   4, user.getCharacter());
		    preparedStatement.setString(5, user.getEmail());
		    preparedStatement.setString(6, user.getPhoneNumber());
		    preparedStatement.setString(7, user.getLogName());
		    preparedStatement.setString(8, user.getPassWord());
		    preparedStatement.setString(9, user.getCreateDate());
//            preparedStatement.setString(10, user.getPhoto());
		    preparedStatement.setString(10, user.getLogName());
		    preparedStatement.setString(11, user.getActiveBefore());
		    preparedStatement.setInt(   12, user.getNewFlag());
		    preparedStatement.setInt(   13, user.getId());
		    int flag = preparedStatement.executeUpdate();
		    if (flag == 1){
			    return true;
		    }
		    return false;
	    }catch (SQLException e){
		    e.printStackTrace();
		    throw e;
	    }finally {
		    DBUtils.close(resultSet, preparedStatement, connection);
	    }

    }

    /**
     *
     *
     * @param userId
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean deleteById(int userId) throws SQLException {
        Connection connection = null;
	    PreparedStatement preparedStatement = null;
//	    ResultSet resultSet = null;
	    String sql = "DELETE FROM ECollaborationWeb.user WHERE id = ?;";
	    UserBean userBean = null;
	    try {
		    connection = DBUtils.getConnetction();
		    preparedStatement = connection.prepareStatement(sql);
		    preparedStatement.setInt(1, userId);
		    int flag = preparedStatement.executeUpdate();
		    if (flag == 1){
			    return true;
		    }
		    else return false;
	    }catch (SQLException e){
		    e.printStackTrace();
		    throw e;
	    }finally {
		    DBUtils.close(null, preparedStatement, connection);
	    }
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
    public File getUserPhoto(UserBean userBean) throws SQLException,FileNotFoundException, IOException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    String sql = "SELECT photo FROM ECollaborationWeb.user WHERE id = 1";
	    try {


		    connection = DBUtils.getConnetction();

		    preparedStatement = connection.prepareStatement(sql);
		    resultSet = preparedStatement.executeQuery();
		    resultSet.next();
		    int id = userBean.getId();
		    File file = new File("/Users/geyao/IdeaProjects/ECollaborationGit/web/askedFiles/headPhotos" + id +".jpg");
		    if (file.exists()){
			    file.delete();
		    }
		    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("/Users/geyao/Desktop/" + id + ".jpg"));
		    byte[] buf=new byte[1024];
		    BufferedInputStream bufferedInputStream = new BufferedInputStream(resultSet.getBinaryStream(1));
		    int count = -1;
		    while ((count = bufferedInputStream.read(buf, 0, 1024)) != -1 ){
			    bufferedOutputStream.write(buf, 0, count);
		    }
		    bufferedOutputStream.flush();
		    return file;
	    }catch (SQLException e){
		    e.printStackTrace();
		    throw e;
	    }catch (IOException e){
		    e.printStackTrace();
		    throw e;
	    }finally {
		    DBUtils.close(resultSet, preparedStatement, connection);
	    }

    }
}