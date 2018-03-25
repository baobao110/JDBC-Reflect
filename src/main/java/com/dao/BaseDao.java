package com.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.domain.Product;
import com.jdbc.JdbcUtil;
import com.jdbc.RowMapper;

public abstract class BaseDao <T> {
	private Connection conn=null;
    private PreparedStatement pre=null;
    
  /**
   * 这里作为一个抽象方法给子类调用,这里之所以也用泛型,是因为这里作为接口获取对象
   * 这里我们因为是接口，为了可以获取所有类型的对象,所以使用泛型
   */
    public abstract T getRows(ResultSet set);
    
  /**
   * 注意这里将JDBC的操作再次抽取出来，这里将sql语句放入，
   * 对于sql语句中的数据修改顺序，因为参数类型的不同统一用Object数组的形式，定义好先后的顺序，
   * 同时注意这里的方法类型为protected，protected的作用是只有继承的类可以访问*/
    protected int insert(String sql, Object[]object){
		conn=JdbcUtil.getConnection();
		int result=0;
		try {
			pre=conn.prepareStatement(sql);
			for(int i=0;i<object.length;i++) {
				pre.setObject(i+1, object[i]);
			}
			result=pre.executeUpdate();
			System.out.println(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JdbcUtil.close(pre, conn);
			}
			
		return result;
	}
    
    /**
     * 注意这里的T 为泛型，就是不确定类型,因为这里的查找是抽出来的,这里作为一个接口给其它类使用，所以必须要有通用性
     * 这里注意参数表中Class反射的使用
     * @throws Exception 
     * @throws InstantiationException 
     */
    protected T select(String sql,Object id,Class<T> clazz) throws Exception {
    	T model=null;//注意这里为什么定义泛型
    	Product goods=null;
    	conn=JdbcUtil.getConnection();
		/*String sql="select * from product where id = ?";*/
    	ResultSet set=null;
		try {
			pre=conn.prepareStatement(sql);
			pre.setObject(1, id);
			set=pre.executeQuery();
			if(set.next()) {
				/*model=this.getRows(set);//注意这里的getRows()方法的使用,同时注意this的用法
				goods=new Product();
				goods.setId(set.getInt("id"));
				goods.setName(set.getString("name"));
				goods.setPrice(set.getDouble("price"));
				goods.setRemark(set.getString("remark"));*/
				model=clazz.newInstance();
				// 获取此 ResultSet 对象的列的编号、类型和属性。
				ResultSetMetaData metaData=set.getMetaData();
				System.out.println(metaData);
				//注意这里从1开始
				for(int i=1;i<=metaData.getColumnCount();i++) {
					// 获取列的名称
					String colname=metaData.getColumnLabel(i);
					System.out.println("获取列的名称"+colname);
					// 根据列的名称获取相应的属性名称
					Field filed=clazz.getDeclaredField(colname);
					// 值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查
					filed.setAccessible(true);
					// 通过反射进行赋值
					filed.set(model, set.getObject(colname));
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JdbcUtil.close(pre, conn);
		}
		return model;//作为泛型对象输出,可以不用考虑返回类型
    }
    
    //这边的这个查询方法说实话没有感觉好在哪里,就是做了封装
    public List<T>queryById(String sql,Object[]obj,RowMapper<T> mapper){
    	List<T>list=new ArrayList<T>();
    	ResultSet rs = null;
    	try {
    		conn=JdbcUtil.getConnection();
			pre=conn.prepareStatement(sql);
			for(int i=0;i<obj.length;i++) {
				pre.setObject(i+1, obj[i]);
			}
			rs=pre.executeQuery();
			while(rs.next()) {
				list.add(mapper.mapRow(rs) );
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }

}


