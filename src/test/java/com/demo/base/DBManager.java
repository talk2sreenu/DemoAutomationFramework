package com.demo.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.demo.utilities.TestConfig;

public class DBManager {

	public static Connection con;
	public static Statement stmt;

	
	public static Connection setDBConnection(){
		try {
			Class.forName(TestConfig.mySqlDBClass).newInstance();
			con = DriverManager.getConnection(TestConfig.DB_URL, TestConfig.DB_USER, TestConfig.DB_PASSWORD);
			if(!con.isClosed())
				System.out.println("Database connection established successfully!!!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		return con;
	}

	public static ResultSet performQuery() throws SQLException {
		setDBConnection();
		stmt = con.createStatement();
		String query = "select * from myfirstdb";
		ResultSet res = stmt.executeQuery(query);
		return res;

	}

	public static ResultSet performQuery(String tblName) throws SQLException {
		setDBConnection();
		stmt = con.createStatement();
		String query= "select * from "+tblName;
		ResultSet res = stmt.executeQuery(query);
		return res;
	}

	public static HashMap<Integer, HashMap<String, String>> runSqlQuery() throws SQLException {
		HashMap<Integer, HashMap<String, String>> resultRows = new HashMap<Integer, HashMap<String, String>>();
		ResultSet tempSet = performQuery();
		ResultSetMetaData metaData = tempSet.getMetaData();
		int count = metaData.getColumnCount();

		int cnt =0;
		while (tempSet.next())
		{
			HashMap<String, String> rowVal = new HashMap<String, String>();
			cnt++;
			for (int i = 1; i <= count; i++)
				rowVal.put(metaData.getColumnLabel(i), tempSet.getString(i));
			resultRows.put(cnt, rowVal);
		}

		for(Map.Entry<Integer, HashMap<String, String>> entry : resultRows.entrySet()) {
			System.out.println("* * * * * * * Displaying Row "+entry.getKey()+" Data * * * * * * *");
			for(Map.Entry<String, String> subEntry : entry.getValue().entrySet()) {
				System.out.println("Column '"+subEntry.getKey()+"' has data of '"+subEntry.getValue()+"' in DB Table");
			}
		}

		return resultRows;
	}

	public static boolean verifyTableCoumnName(String tblName, String colName) throws SQLException {
		boolean found = false;
		ResultSet tempSet = performQuery(tblName);
		ResultSetMetaData metaData = tempSet.getMetaData();

		int count = metaData.getColumnCount();
		for(int i=1;i<=count;i++) {
			if(metaData.getColumnName(i).toLowerCase().equals(colName.toLowerCase())) {
				System.out.println("Column Name of '"+colName+"' found in the Database table '"+tblName);
				found = true;
				break;
			}
		}
		return found;
	}

	public static boolean verifyValueFromTable(String tblName, String val) throws SQLException {
		boolean found = false;
		ResultSet tempSet = performQuery(tblName);
		ResultSetMetaData metaData = tempSet.getMetaData();

		int count = metaData.getColumnCount();
		int cnt =0;
		while (tempSet.next())
		{
			cnt++;
			for (int i = 1; i <= count; i++) {
				if(tempSet.getString(i).equals(val)) {
					System.out.println("Value of '"+val+"' found under '"+metaData.getColumnName(i)+"' column in '"+i+"' Row");
					found = true;
					break;
				}
			}	
		}
		return found;
	}

	public static void main(String[] args) throws SQLException {
		//setDBConnection();
		verifyTableCoumnName("myfirstdb", "userName");
		verifyValueFromTable("myfirstdb","Srinivasu");
	}
}
