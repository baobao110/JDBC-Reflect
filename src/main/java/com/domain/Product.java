package com.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
	
	private Integer id;
	private String name;
	//注意这里的BigDecimal,如果这里定义为double后面的Jdbc反射会报错 
	private BigDecimal price;
	private String remark;
	private Date date;
	private String pic;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", remark=" + remark + ", date=" + date
				+ ", pic=" + pic + "]";
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String name, BigDecimal price, String remark) {
		super();
		this.name = name;
		this.price = price;
		this.remark = remark;
	}
	
	
}
