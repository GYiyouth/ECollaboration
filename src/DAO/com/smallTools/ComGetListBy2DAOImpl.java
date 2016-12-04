package DAO.com.smallTools;

import DAO.com.util.db.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * Created by geyao on 2016/12/4.
 */
public class ComGetListBy2DAOImpl<V, F1, F2> implements ComGetListBy2DAO {
	/**
	 * 通过2个元素，获取一个元素的队列
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
	public ArrayList<V> getListAfromBbyC(String columnA, String column1, Object value1, String column2, Object value2, String tableC) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT  "+ columnA + " FROM " + SchemaName + tableC +
				" WHERE " + column1 + " = ? AND " + column2 + " = ?;";
		ArrayList<V>list = new ArrayList<V>();
		try{
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setObject(1, value1);
			preparedStatement.setObject(2, value2);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				list.add((V) resultSet.getObject(1));
			}
			if (list.size() > 0)
				return list;
			else
				return null;
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}

//	public static void main(String[] args) {
//		ComGetSingleBy2DAO comGetSingleBy2DAO = new ComGetSingleBy2DAOImpl<String, Integer, Integer>();
//		ComGetListBy2DAO comGetListBy2DAO = new ComGetListBy2DAOImpl<String, Integer, Integer>();
//		try {
//			ArrayList<String> one = comGetListBy2DAO.getListAfromBbyC("teamName", "id", 1, "creatorId", 12, "team");
//			String two = (String) comGetSingleBy2DAO.getListAfromBbyC("teamName", "id", 1, "creatorId", 12, "team");
//
//			System.out.println(one);
//			System.out.println(two);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
