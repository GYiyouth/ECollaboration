package DAO.com.smallTools;

import DAO.com.util.db.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by geyao on 2016/12/4.
 */
public class ComGetSingleBy2DAOImpl<V, F1, F2> implements ComGetSingleBy2DAO {
	/**
	 * 通过2个元素获取一个新的值
	 *
	 * @param columnA
	 * @param column1
	 * @param value1
	 * @param column2
	 * @param value2
	 * @param tableC
	 * @return
	 * @throws SQLException
	 */
	private static String SchemaName = "ecollaborationweb.";
	@Override
	public Object getListAfromBbyC(String columnA, String column1, Object value1, String column2, Object value2, String tableC) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT "+ columnA + " FROM " + SchemaName + tableC +
				" WHERE " + column1 + " = ? AND " + column2 + " = ?;";
		V v = null;
		try{
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setObject(1, value1);
			preparedStatement.setObject(2, value2);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				v = (V)resultSet.getObject(1);
				return v;
			}else
				return null;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}
}
