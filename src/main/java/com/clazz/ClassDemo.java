package com.clazz;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.domain.Product;

public class ClassDemo {
	
	public static void demo() throws Exception {
				//java中定义Class类，用于表示*.class的文件或者对象
				Class clazz=Class.forName("com.dao.ProductDao");
				System.out.println("该类的父类"+clazz.getSuperclass());
				ClassLoader load=clazz.getClassLoader();
				System.out.println("该类的加载器"+load);
	}
	
	public static void method() throws Exception {
		//获取class信息
		Class clazz=Class.forName("com.domain.Product");
		//动态的创建对象
		Object object=clazz.newInstance();
		//通过反射获取所有的方法(包含自身和父类的public方法)
		System.out.println("包含自身和父类的public方法");
		Method[] method=clazz.getMethods();
		for(Method i:method) {
			System.out.println(i);
		}
		//获取自身的方法包含private
		System.out.println("获取自身的方法包含private");
		for(Method i:clazz.getDeclaredMethods()) {
			System.out.println(i);
		}
		//获取指定类型的方法,需要方法名和参数，防止重载
		System.out.println(clazz.getMethod("setName",String.class));
		//对方法进行赋值
		Method meth=clazz.getMethod("setName",String.class);
		meth.invoke(object, "34343");
		Method thod=clazz.getMethod("getName");
		System.out.println(thod.invoke(object));
	}
	
	public static void field() throws Exception {
		//获取class信息
		Class clazz=Class.forName("com.domain.Product");
		//动态的创建对象
		Object object=clazz.newInstance();
		//通过反射获取所有的方法(包含自身和父类的public属性)
		System.out.println("包含自身和父类的public属性");
		Field[] field=clazz.getFields();
		for(Field i:field) {
			System.out.println(i);
		}
		//获取自身的属性包含private
		System.out.println("获取自身的属性包含private");
		for(Field i:clazz.getDeclaredFields()) {
			System.out.println(i);
		}
		
		//对方法进行赋值
		Field name=clazz.getDeclaredField("name");
		// 值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查
		name.setAccessible(true);
		name.set(object, "西瓜视频");
		System.out.println(name.get(object));
	}
	
	//获取Class的三种方式
	public void init() throws Exception {
		Class clazz=null;
		clazz=Class.forName("com.domain.Product");
		clazz=new Product().getClass();
		clazz=Product.class;
		
	}
	
}
