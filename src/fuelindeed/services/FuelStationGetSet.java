package fuelindeed.services;

import java.sql.Connection;
import java.sql.*;

import fuelindeed.db.ConnectDB;

public class FuelStationGetSet {
	static String femail;
static int fid; 
	public static int getId() {
	return fid;
}

public static void setId() {
	
	
	Connection con = ConnectDB.dbCon();
	try
	{
		PreparedStatement ps = con.prepareStatement("select fid from fuelstation where femail=?");
		ps.setString(1, femail);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			FuelStationGetSet.fid = rs.getInt(1);
		}
	}
	catch(Exception e)
	{
		
	}
}

	public static String getFemail() {
		return femail;
	}

	public static void setFemail(String femail) {
		FuelStationGetSet.femail = femail;
	}

}
