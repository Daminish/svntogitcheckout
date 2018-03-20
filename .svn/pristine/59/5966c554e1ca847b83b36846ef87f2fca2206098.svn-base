package com.capco.hcm.controller.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import com.capco.hcm.controller.DatabaseController;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.apache.tomcat.jdbc.pool.DataSource;
/**
 * @author Mohit Gangil
 *
 */


/**
 * @author Mohit Gangil
 *
 */


public class DatabaseControllerImpl implements DatabaseController, Serializable {
	private static final long serialVersionUID = -5524297200924344876L;
	
	private String dbUrl;//"jdbc:mysql://localhost:3306/mysql"
	private String dbUsername;
	private String dbPassword;
	private Integer maxActiveConnnCount;
	private Integer startConnnCount;
	private DataSource datasource;
	

	/**
	 * 
	 */
	public void createConnectionPool(){
		PoolProperties p = new PoolProperties();
		p.setUrl(dbUrl);
		p.setDriverClassName("com.mysql.jdbc.Driver");
		p.setUsername(dbUsername);
		p.setPassword(dbPassword);
		p.setJmxEnabled(true);
		p.setValidationInterval(30000);
		p.setTimeBetweenEvictionRunsMillis(30000);
		p.setMaxWait(10000);
		p.setRemoveAbandonedTimeout(60);
		p.setMinEvictableIdleTimeMillis(30000);
		p.setMinIdle(10);
		p.setMaxActive(maxActiveConnnCount);
		p.setInitialSize(startConnnCount);
		p.setLogAbandoned(true);
		p.setRemoveAbandoned(true);
		p.setJdbcInterceptors(
				"org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
				"org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		datasource = new DataSource();
		datasource.setPoolProperties(p);
	}

	/**
	 * 
	 * @return
	 */
	public Connection getConnection(){
		System.out.println("hello");
		Connection con = null;
		try {
			con = datasource.getConnection();
			System.out.println("the connections is "+con);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}
	
	/**
	 * 
	 * @param conn
	 */
	public void closeConnection(Connection conn){
		try{
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			conn = null;
		}
	}

	//START of Getters and Setters
	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public Integer getMaxActiveConnnCount() {
		return maxActiveConnnCount;
	}

	public void setMaxActiveConnnCount(Integer maxActiveConnnCount) {
		this.maxActiveConnnCount = maxActiveConnnCount;
	}

	public Integer getStartConnnCount() {
		return startConnnCount;
	}

	public void setStartConnnCount(Integer startConnnCount) {
		this.startConnnCount = startConnnCount;
	}

	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	
	
}