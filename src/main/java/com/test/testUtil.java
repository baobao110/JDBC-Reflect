package com.test;

import com.dao.ProductDao;
import com.domain.Product;

public class testUtil {
	public static void main(String[] args) {
		ProductDao dao=new ProductDao();
		/*ProductDao dao=new ProductDao();
		Product pro=new Product("def", 22.00, "武介绍");
		System.out.println(dao.insert(pro));*/
		/*dao.select(3);*/
		/*dao.queryById(3);*/
		dao.select(3);
		/*Product pro=new Product();
		pro.setId(2);
		pro.setName("rererer");
		pro.setPrice(3333);
		pro.setRemark("reeeeeee");
		int result=dao.update(pro);
		System.out.println(result);*/
	}
}
/*
 * 总结这里主要看select方法,这里的select运用的ResultSet和Class反射的结合使用,
 * 这里要特别注意泛型的使用,非常的巧妙,主要就是用Result和Class的各种方法
 * 这里用Result的getMetaData()获取数据库查询后获取的参数以及值,
 * 然后通过遍历的方法根据参数用Class的getDeclaredField()方法匹配java类中的属性,
 * 之后通过T model=clazz.newInstance()创建对象,
 * 之后需要注意setAccessible(true);值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查
 * 通过Field的set()方法对model的参数设置值
 * 最后作为泛型输出
 * */

