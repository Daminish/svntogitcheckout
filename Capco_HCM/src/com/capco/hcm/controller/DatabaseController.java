package com.capco.hcm.controller;

import java.sql.Connection;

/**
 * @author Mohit Gangil
 *
 */


public interface DatabaseController {

	public void createConnectionPool();
	public Connection getConnection();
	public void closeConnection(Connection conn);
	
}
