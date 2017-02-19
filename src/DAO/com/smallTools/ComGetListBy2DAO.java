package DAO.com.smallTools;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by geyao on 2016/12/4.
 */
public interface ComGetListBy2DAO<V, F1, F2> {
	/**
	 * 通过2个元素，获取一个元素的队列
	 * @param columnA
	 * @param column1
	 * @param value1
	 * @param column2
	 * @param value2
	 * @param tableC
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<V> getListAfromBbyC(String columnA, String column1,
	                                     F1 value1, String column2, F2 value2, String tableC) throws SQLException;
}
