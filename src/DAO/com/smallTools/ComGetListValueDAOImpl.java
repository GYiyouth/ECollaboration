package DAO.com.smallTools;

import DAO.com.util.db.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by geyao on 2016/12/2.
 */
public class ComGetListValueDAOImpl<V, F> implements ComGetListValueDAO {

	private static String SchemaName = "ecollaborationweb.";

	/**
	 * 获取V类型的ArrayList
	 *
	 * @param columnA
	 * @param columnB
	 * @param valueB
	 * @param tableC
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ArrayList<V> getListAfromBbyC(String columnA, String columnB, Object valueB, String tableC) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<V> listValue = new ArrayList<V>();
		String sql = "SELECT " + columnA + " FROM "  + SchemaName + tableC + " WHERE " + columnB + " = ?;";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setObject(1, (F)valueB);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				listValue.add((V) resultSet.getObject(1));
			}
			if (listValue.size() == 0)
				return null;
			else{
				HashSet tem = new HashSet(listValue);
				listValue.clear();
				listValue.addAll(tem);
				return listValue;
			}
		}catch (SQLException e){
			e.printStackTrace();
			throw  e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}
}
