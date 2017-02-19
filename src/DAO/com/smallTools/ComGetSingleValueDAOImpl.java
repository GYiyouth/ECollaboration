package DAO.com.smallTools;

import DAO.com.util.db.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by geyao on 2016/12/1.
 */
public class ComGetSingleValueDAOImpl<V, F> implements ComGetSingleValueDAO {

	private static String SchemaName = "ecollaborationweb.";
	/**
	 * 获取 V 类型 A的值，通过F类型B的名字，B的值，以及表名C
	 *
	 * @param columnA
	 * @param columnB
	 * @param valueB
	 * @param tableC
	 * @return
	 * @throws SQLException
	 */
	@Override
	public V getAbyIntBfromC(String columnA, String columnB, Object valueB, String tableC) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = "SELECT " + columnA + " FROM "  + SchemaName + tableC + " WHERE " + columnB + " = ?;";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setObject(1, (F)valueB);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				return (V) resultSet.getObject(1);
			}else return null;
		}catch (SQLException e){
			e.printStackTrace();
			throw  e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}
}
