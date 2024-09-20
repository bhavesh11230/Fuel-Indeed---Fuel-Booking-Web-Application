package fuelindeed.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fuelindeed.db.ConnectDB;

public class DPersonEmailGetSet {
	static String demail;
	static int did;

	public static String getDemail() {
		return demail;
	}

	public static void setDemail(String demail) {
		DPersonEmailGetSet.demail = demail;
	}
		public static int getId() {
		return did;
	}

	public static void setId() {
		
		
		Connection con = ConnectDB.dbCon();
		try
		{
			PreparedStatement ps = con.prepareStatement("select did from dperson where demail=?");
			ps.setString(1, demail);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				DPersonEmailGetSet.did = rs.getInt(1);
			}
			System.out.println(did);
			System.out.println(demail);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
