package fuelindeed.services;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fuelindeed.db.ConnectDB;

/**
 * Servlet implementation class ChangeUserPassword
 */
public class ChangeUserPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUserPassword() {
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
		String uemail = request.getParameter("uemail");
		String upassword = request.getParameter("opassword");
		String npassword = request.getParameter("npassword");
		Connection con = ConnectDB.dbCon();
		try
		{
			PreparedStatement ps = con.prepareStatement("update users set upassword=? where upassword=? and uemail=?");
			ps.setString(1, npassword);
			ps.setString(2, upassword);
			ps.setString(3, uemail);
			int i = ps.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("UserTask.jsp");
			}
			else
			{
				response.sendRedirect("changepassword.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
