package fuelindeed.services;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fuelindeed.db.ConnectDB;

/**
 * Servlet implementation class CodeCheck
 */
public class CodeCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeCheck() {
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
       	try 
       	{
       		String uemail=UserGetSet.getUemail();
       		int code = Integer.parseInt(request.getParameter("code"));
       		int uid = UserGetSet.getId();
       		String status = "delivered";
       		PreparedStatement ps = con.prepareStatement("update bookings set status=? where code=?");
       		 ps.setString(1, status);
       		ps.setInt(2, code);
            int i = ps.executeUpdate();
            if(i>0) 
            {
            	response.sendRedirect("DPersonTask.jsp");
            }
            else
            {
            	response.sendRedirect("codeenter.html");
            }
        } 
       	catch(Exception e) 
       	{
            e.printStackTrace();
        }
	}

}
