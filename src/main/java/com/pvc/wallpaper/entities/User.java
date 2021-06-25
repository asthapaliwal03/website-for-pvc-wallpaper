package com.pvc.wallpaper.entities;

import javax.persistence.*;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	private String userName,userPic,userCity,userPass,userType;
	//@Column(unique=true)
	private String userEmail;
	//@Column(unique=true)
	private String userPhone;
	public User() {}
	
	public User(String userName, String userEmail, String userPhone, String userPic, String userCity,String userPass,String userType) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userPic = userPic;
		this.userCity = userCity;
		this.userPass=userPass;
		this.userType=userType;
	}

	public User(int userId, String userName, String userEmail, String userPhone, String userPic, String userCity,String userPass,String userType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userPic = userPic;
		this.userCity = userCity;
		this.userPass=userPass;
		this.userType=userType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserPic() {
		return userPic;
	}
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPhone="
				+ userPhone + ", userPic=" + userPic + ", userCity=" + userCity + ", userPass=" + userPass
				+ ", userType=" + userType + "]";
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}


}
