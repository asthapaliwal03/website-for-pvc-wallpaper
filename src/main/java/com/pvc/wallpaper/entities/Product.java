package com.pvc.wallpaper.entities;

import javax.persistence.*;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int pID;
private String pName,pImg;
private int pPrice,pSize,pDiscount;
@ManyToOne
private Category category;
@Column(length=3000)
private String pDes;

public Product() {}
public Product(String pName, int pPrice, String pImg, int pSize, int pDiscount, String pDes,Category category) {
	super();
	this.pName = pName;
	this.pPrice = pPrice;
	this.pImg = pImg;
	this.pSize = pSize;
	this.pDiscount = pDiscount;
	this.pDes = pDes;
	this.category=category;
}

public Product(int pID, String pName, int pPrice, String pImg, int pSize, int pDiscount, String pDes) {
	super();
	this.pID = pID;
	this.pName = pName;
	this.pPrice = pPrice;
	this.pImg = pImg;
	this.pSize = pSize;
	this.pDiscount = pDiscount;
	this.pDes = pDes;
}

public int getpID() {
	return pID;
}

public void setpID(int pID) {
	this.pID = pID;
}

public String getpName() {
	return pName;
}

public void setpName(String pName) {
	this.pName = pName;
}

public int getpPrice() {
	return pPrice;
}

public void setpPrice(int pPrice) {
	this.pPrice = pPrice;
}

public String getpImg() {
	return pImg;
}

public void setpImg(String pImg) {
	this.pImg = pImg;
}

public int getpSize() {
	return pSize;
}

public void setpSize(int pSize) {
	this.pSize = pSize;
}

public int getpDiscount() {
	return pDiscount;
}

public void setpDiscount(int pDiscount) {
	this.pDiscount = pDiscount;
}

public String getpDes() {
	return pDes;
}

public void setpDes(String pDes) {
	this.pDes = pDes;
}


public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}
@Override
public String toString() {
	return "Product [pID=" + pID + ", pName=" + pName + ", pPrice=" + pPrice + ", pImg=" + pImg + ", pSize="
			+ pSize + ", pDiscount=" + pDiscount + ", pDes=" + pDes + "]";
}

//calculate discounted price
public int getDiscountedPrice() {
	int dis=0;
	dis=(int)(pPrice-(pPrice*pDiscount/100));
	return dis;
}

}
