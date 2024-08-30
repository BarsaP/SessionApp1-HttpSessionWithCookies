package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw=res.getWriter();
		//set response content type
		res.setContentType("text/html");
		//read form2/req2 data
		String f2val1=req.getParameter("f2t1");
		String f2val2=req.getParameter("f2t2");
		//get Access to the Session object
		HttpSession ses=req.getSession();
		//read form1/req1 data as Session attribute values (session tracking)
		String name=(String)ses.getAttribute("name");
		String fname=(String)ses.getAttribute("fname");
		String ms=(String)ses.getAttribute("ms");
		
		//display dynamic web page having form1/req1 and form2/req2 data
		pw.println("<h1 style='color:red;text-align:center'>Person Information----shadi.com</h1>");
		pw.println("<h1>form1/req1 data::"+name+"...."+fname+"...."+ms+"</h1>");
		pw.println("<h1>form2/req2 data::"+f2val1+"..."+f2val2+"</h1>");
		
		//add home hyperlink
		pw.println("<br><a href='details.html'>home</a>");
		pw.println("<br><b>Session id::"+ses.getId()+"</b>");
	}//doGet(-,-)
	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}//doPost(-,-)
}//class
