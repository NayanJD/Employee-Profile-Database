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

public class MainSrv extends HttpServlet {
	
	private Connection con;
	private PreparedStatement ps;
	private PreparedStatement psq;
	InputStream fc=null;
	
	public void init(){
		
		//try loading the driver and getting the appropriate connection
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","qwaszx");
			ps=con.prepareStatement("insert into emp_profile values(?,?,?,?,?,?,?)");
			psq=con.prepareStatement("select * from emp_profile where empno=?");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");

		

		try{
			
			//getting field values from the html form

			//getting Employee No.
			int no=Integer.parseInt(req.getParameter("tno"));
			
			//getting Employee Name
			String name=req.getParameter("tname");
			
			//getting Employee age
			int age=Integer.parseInt(req.getParameter("tage"));

			//getting Employee DOB
			SimpleDateFormat datef=new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date=datef.parse(req.getParameter("tdate"));
			long ms=date.getTime();
			java.sql.Date ddate=new java.sql.Date(ms);
			
			//getting Employee Adress
			String add=req.getParameter("taddress");
			
			//getting Employee Profile Picture
			Part fphoto=req.getPart("tphoto");
        	fc=fphoto.getInputStream();
			
			//getting Employee mail address
			String mail=req.getParameter("temail");
			

			psq.setInt(1,no);
			ResultSet rs=psq.executeQuery();

			//Checking if record already exists
			if(!rs.next()){

			//if not add it to database	
			ps.setInt(1,no);
			ps.setString(2,name);
			ps.setInt(3,age);
			ps.setDate(4,ddate);
			ps.setString(5,add);
			ps.setBlob(6,fc);
			ps.setString(7,mail);
        	ps.executeUpdate();
			RequestDispatcher rd2=req.getRequestDispatcher("resurl");
			rd2.include(req,res);
			}
			else{

				//If present display error message
				RequestDispatcher rd1=req.getRequestDispatcher("header.html");
				rd1.include(req,res);
				pw.println("<center><h3><b>Record Already in Database with this Employee No. Try with Diff. No.</b>");
				RequestDispatcher rd2=req.getRequestDispatcher("footer.html");
				rd2.include(req,res);
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		doGet(req,res);
	}
	public void destroy(){

		//closing all resources
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
