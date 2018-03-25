package com.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

//这里联系queryById方法注意这里的泛型
public interface RowMapper<T> {
	public abstract T mapRow(ResultSet rs) throws SQLException;
}
