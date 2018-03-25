package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		public static Connection getConnection(){
			try {
				return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/1?characterEncoding=UTF-8", "root", "123456");//注意这里设置编码格式不然的话中文插入数据库为?
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		public static void main(String[] args) {
			System.out.println(JdbcUtil.getConnection());
		}
		
		
		/**
		*注意这里的close方法，这里作为一个静态的方法将它模块块
		*这里特别要注意的就是两个close,这里如果将两个放在一起,如果有一个没有关闭成功都不能成功的关闭
		*这里用finally ,无论Statement是否关闭成功，数据库的接口必须关闭
		*这里如果Connection不执行关闭操作,java虚拟机就不会执行垃圾回收,会导致内存泄露,表现为程序执行越来越慢,
		*必须重启,内存泄露是指无用对象（不再使用的对象）持续占有内存或无用对象的内存得不到及时释放
		*，从而造成的内存空间的浪费称为内存泄露。
		*/
		public static void close(Statement pre,Connection conn) {
			try {
				pre.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}


	
//JDK包含JRE JRE包含JVM 
/**单例模型分两种：懒汉模式和饿汉模式，饿汉模式是线程安全的，懒汉模式是非线程安全的 ，线程安全问题出现在多线程中，可能一个线程在用，又一个线程进入发现没有，会再次创建对象，这时懒汉模式就不是单线程
*https://blog.csdn.net/jason0539/article/details/23297037/
*http://www.jb51.net/article/101380.htm
*懒汉模式的线程安全问题可以用同步锁
*/
