package DAO.com.smallTools;

import com.sun.istack.internal.Nullable;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 用来写一些通用的方法，比如在某个特定table，根据某个特定的列，返回某个值
 * 这里用了范型，V指要获取的数据类型
 * new ComGetSingleValueDAO<Integer, String>
 * Created by geyao on 2016/12/1.
 */
public interface ComGetSingleValueDAO<V, F> {
	/**
	 * 获取A的值，通过Integer B的名字，B的值，以及表名C
	 *
	 * @param columnA
	 * @param columnB
	 * @param valueB
	 * @param tableC
	 * @return
	 * @throws SQLException
	 */
	public V getAbyIntBfromC(String columnA, String columnB, F valueB, String tableC) throws SQLException;
}

