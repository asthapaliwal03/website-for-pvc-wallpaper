package com.pvc.wallpaper.entities;

import java.util.*;

import javax.persistence.*;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(length=100)
private int categoryId;
private String categoryName,categoryDescription,categoryPic;
@OneToMany(mappedBy="category")
private List<Product> product=new ArrayList<Product>();
public Category() {}

public Category(String categoryName, String categorydescription, String categoryPic,List<Product> product) {
	super();
	this.categoryName = categoryName;
	this.categoryDescription = categorydescription;
	this.categoryPic = categoryPic;
	this.product=product;
}
public Category(String categoryName, String categoryDescription, String categoryPic) {
	super();
	this.categoryName = categoryName;
	this.categoryDescription = categoryDescription;
	this.categoryPic = categoryPic;
}

public Category(int categoryId, String categoryName, String categoryDescription, String categoryPic) {
	super();
	this.categoryId = categoryId;
	this.categoryName = categoryName;
	this.categoryDescription = categoryDescription;
	this.categoryPic = categoryPic;
}
public int getCategoryId() {
	return categoryId;
}
public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
}
public String getCategoryName() {
	return categoryName;
}
public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}
public String getCategoryDescription() {
	return categoryDescription;
}
public void setCategoryDescription(String categorydescription) {
	this.categoryDescription = categorydescription;
}
public String getCategoryPic() {
	return categoryPic;
}
public void setCategoryPic(String categoryPic) {
	this.categoryPic = categoryPic;
}


public List<Product> getProduct() {
	return product;
}

public void setProduct(List<Product> product) {
	this.product = product;
}

@Override
public String toString() {
	return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryDescription="
			+ categoryDescription + ", categoryPic=" + categoryPic + "]";
}

}
