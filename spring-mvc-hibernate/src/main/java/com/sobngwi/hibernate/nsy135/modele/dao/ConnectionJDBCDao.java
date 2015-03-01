/**
 * 
 */
package com.sobngwi.hibernate.nsy135.modele.dao;

import java.sql.Connection;


/**
 * 
 * Connection ad disconnection service to the database.
 * @author suse
 */

public interface ConnectionJDBCDao   {

	void connect () ;
	void disConnect () ;
	String getConnectionString()  ;
	Connection getConnection()  ;
}
