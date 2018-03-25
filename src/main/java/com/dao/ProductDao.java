package com.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.domain.Product;
import com.jdbc.JdbcUtil;
import com.jdbc.RowMapper;

public class ProductDao extends BaseDao<Product>{
	
	//这里的查询方法就是将方法进一步的封装,但是还是要对ResultSet进行处理
	public List<Product> queryById(int id){
		String sql="select * from product where id = ?";
		return super.queryById(sql, new Object[]{id}, new RowMapper<Product>() {
			
			public Product mapRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				Product goods=new Product();
				goods.setId(rs.getInt("id"));
				goods.setName(rs.getString("name"));
				goods.setPrice(new BigDecimal(rs.getDouble("price")));
				goods.setRemark(rs.getString("remark"));
				return goods;
			}
		});
	}
	
	
	public int insert(Product goods) {
		
		String sql="insert product(name,price,remark) values (?,?,?)";
		int result=super.insert(sql, new Object[]{goods.getName(),goods.getPrice(),goods.getRemark()});
		return result;
		
		/*int result=0;
		try {
			pre=conn.prepareStatement(sql);
			pre.setString(1, goods.getName());
			pre.setDouble(2, goods.getPrice());
			pre.setString(3, goods.getRemark());
			result=pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JdbcUtil.close(pre, conn);
			}
			
		return result;*/
	}
	
	//这里的方法是继承于父类,这样做的好处是作为select因为返回对象不同所以不同对象的返回类型也不同
	@Override//注意这个方法不是特别的灵活
	public Product  getRows(ResultSet set) {
		// TODO Auto-generated method stub
		Product goods=new Product();
		try {
			goods.setId(set.getInt("id"));
			goods.setName(set.getString("name"));
			goods.setPrice(new BigDecimal(set.getDouble("price")));
			goods.setRemark(set.getString("remark"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goods;
	}
	
	public  void select(int id) {
		String sql="select * from product where id = ?";
		 try {
			System.out.println(super.select(sql, id,Product.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void select(int id) {
//		conn=JdbcUtil.getConnection();
//		String sql="select * from product where id = ?";
//		try {
//			pre=conn.prepareStatement(sql);
//			pre.setInt(1, 2);
//			pre.executeQuery();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally {
//			JdbcUtil.close(pre, conn);
//		}
//		
//	}
//	
//	public int update(Product goods) {
//		conn=JdbcUtil.getConnection();
//		String sql="update product set name = ?,price = ?,remark = ? where id= ?";
//		int result=0;
//		try {
//			pre=conn.prepareStatement(sql);
//			pre.setString(1, goods.getName());
//			pre.setDouble(2, goods.getPrice());
//			pre.setString(3, goods.getRemark());
//			pre.setInt(4, goods.getId());
//			result=pre.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally {
//			JdbcUtil.close(pre, conn);
//			}
//			
//		return result;
//	}
	
}
// sql查询当前数据库的连接数用 show PROCESSLIST;
