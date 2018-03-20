package com.capco.living.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true)
	private int uid;
	
	@Id
	@Column(name="user_id", nullable=false)
	private String userId;
	
	@Column(nullable=false)
	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="user_type")
	private String userTypeName;
	
	@Column(name="mobile_no")
	private String mobileNo;
	
	@Column(name="thai_id")
	private String thaiId;
	
	@Column(name="home_address")
	private String homeAddress;
	
	@Column(name="work_address")
	private String workAddress;

	public User() {}

	public User(int uid, String userId, String password, String firstName, String lastName, String userTypeName,
			String mobileNo, String thaiId, String homeAddress, String workAddress) {
		this.uid = uid;
		this.userId = userId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userTypeName = userTypeName;
		this.mobileNo = mobileNo;
		this.thaiId = thaiId;
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUserId() {
		return userId;
	}

	public void setEmail(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getThaiId() {
		return thaiId;
	}

	public void setThaiId(String thaiId) {
		this.thaiId = thaiId;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
