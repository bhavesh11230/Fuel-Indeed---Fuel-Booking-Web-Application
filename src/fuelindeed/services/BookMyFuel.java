package fuelindeed.services;

import java.io.IOException;

import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ParseConversionEvent;

import fuelindeed.db.ConnectDB;

/**
 * Servlet implementation class BookMyFuel
 */
public class BookMyFuel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookMyFuel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		Connection con = ConnectDB.dbCon();
		 try {
		int fid=Integer.parseInt(request.getParameter("fid"));
			int bid=0;
			int did=0;
			
			float quantity = Float.parseFloat(request.getParameter("quantity"));
			String ftype = request.getParameter("ftype");
			float totalbill=0;
			 int uid = UserGetSet.getId();
			String status = "pending";
			if(ftype.equals("petrol"))
			{
				PreparedStatement ps = con.prepareStatement("select fpetrolrate from fuelstation where fid=?");
				ps.setInt(1, fid);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					totalbill = totalbill+(rs.getFloat(1)*quantity);
				}
			}
			else if(ftype.equals("diesel"))
			{
				PreparedStatement ps = con.prepareStatement("select fdiselrate from fuelstation where fid=?");
				ps.setInt(1, fid);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					totalbill = totalbill+(rs.getFloat(1)*quantity);
				}
			}
			int code = (int)(Math.random() * 9000) + 1000;
			 PreparedStatement ps = con.prepareStatement("insert into bookings values(?,?,?,?,?,?,?,?,?,CURRENT_DATE)");
			 ps.setInt(1, bid);
			 ps.setInt(2, uid);
			 ps.setInt(3,fid);
			 ps.setInt(4, did);
			 ps.setString(5, ftype);
			 ps.setFloat(6, quantity);
			 ps.setFloat(7,totalbill);
			 ps.setString(8,status);
			 ps.setInt(9,code);
			 int i = ps.executeUpdate();
		if(i>0) 
		{    
			response.sendRedirect("UserTask.jsp");
		}
		else
		{
			response.sendRedirect("bookfuel.html");
		}

		 } 
		 catch(Exception e) 
		 {
			 e.printStackTrace();
		 }
	}

}
