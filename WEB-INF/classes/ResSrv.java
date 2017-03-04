package com.nt;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.text.*;

/**
This class displays the Employee details in the browser
*/

public class ResSrv extends HttpServlet {
	
	private Connection con;
	private PreparedStatement ps;
	FileOutputStream fos=null;
	
	public void init(){
		
		//getting connection from driver
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","qwaszx");
			ps=con.prepareStatement("select * from emp_profile where empno=?");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		//Display header information
		RequestDispatcher rd1=req.getRequestDispatcher("header.html");
			rd1.include(req,res);
		
		try{
			//get ServletContext object to fetch path of profile picture 
			ServletContext sc=getServletContext();

			//get Employee no. from query page
			int no=Integer.parseInt(req.getParameter("tno"));
			ps.setInt(1,no);
			ResultSet rs=ps.executeQuery();

			//check if record is present
			if(rs.next()){

				//if present display
				pw.println("<center><b><font color=#999966>Employee No</font></b></center><br><center><b>"+rs.getInt(1)+"</b></center><br>");
				pw.println("<center><b><font color=#999966>Employee Name</font></b></center><br><center><b>"+rs.getString(2)+"</b></center><br>");
				pw.println("<center><b><font color=#999966>Employee Age</font></b></center><br><center><b>"+rs.getInt(3)+"</b></center><br>");
				pw.println("<center><b><font color=#999966>Employee Date Of Birth</font></b></center><br><center><b>"+rs.getDate(4)+"<b></center><br>");
				pw.println("<center><b><font color=#999966>Employee Address</font></b></center><br><center><b>"+rs.getString(5)+"</b></center><br>");
				pw.println("<center><b><font color=#999966>Employee Mail ID</font></b></center><br><center><b>"+rs.getString(7)+"</b></center><br>");
				
				//fetch profile picture from database
				fos=new FileOutputStream(sc.getInitParameter("path")+"output.jpg");
		    	fos.write(rs.getBytes(6));
			 	
				 //display profile picture
				RequestDispatcher rd3=req.getRequestDispatcher("pic.html");
				rd3.include(req,res);
			}
			else{

				//else display error message
				pw.println("<center><h3>Record not found</h3></center>");
			}
			
			   
		}
			
			
		
		catch(Exception e){
			e.printStackTrace();
		}

		//display appropriate footer 
		RequestDispatcher rd2=req.getRequestDispatcher("footer.html");
		rd2.include(req,res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		doGet(req,res);
	}
	public void destroy(){
		
		//close all resources
		try{
			if(ps!=null)
				ps.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			if(con!=null)
				con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		

}
