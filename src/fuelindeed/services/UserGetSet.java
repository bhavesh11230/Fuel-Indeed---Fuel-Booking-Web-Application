package fuelindeed.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fuelindeed.db.ConnectDB;

public class UserGetSet {
	static String uemail;
	static int uid; 
	public static String getUemail() {
		return uemail;
	}

	public static void setFemail(String uemail) {
		UserGetSet.uemail = uemail;
	}
		public static int getId() {
		return uid;
	}

	public static void setId() {
		
		
		Connection con = ConnectDB.dbCon();
		try
		{
			PreparedStatement ps = con.prepareStatement("select uid from users where uemail=?");
			ps.setString(1, uemail);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				UserGetSet.uid = rs.getInt(1);
			}
			System.out.println(uid);
			System.out.println(uemail);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
