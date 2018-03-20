package com.capco.living.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="password_reset_token")
public class PasswordResetToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private int token;
    
    @Id
    @Column(name="user_id")
    private String email;
    
    @Column(name="expiry_date", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;
    
	public PasswordResetToken(int token, String email) {
		
		this.token = token;
		this.email = email;
		this.expiryDate = new Date();
		
	}

	public PasswordResetToken() {}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

}
