package DAO.com.smallTools;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by geyao on 2016/12/2.
 */
public interface ComGetListValueDAO<V, F> {
	/**
	 * 获取V类型的ArrayList
	 * @param columnA
	 * @param columnB
	 * @param valueB
	 * @param tableC
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<V> getListAfromBbyC(String columnA, String columnB, F valueB, String tableC) throws SQLException;
}
