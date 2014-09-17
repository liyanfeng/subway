package com.subway.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class FileUpLoad extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FileUpLoad() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Variables
		int count=0;        
		SmartUpload mySmartUpload=new SmartUpload();
		// Initialization
		mySmartUpload.initialize(this.getServletConfig()
				,request,response);

		mySmartUpload.setTotalMaxFileSize(100000);

		// Upload	
		try {
			mySmartUpload.upload();
		} catch (SmartUploadException e1) {
			e1.printStackTrace();
		}
		System.out.println("ooookkkkk");
		try {

			// Save the files with their original names in the virtual path "/upload"
			// if it doesn't exist try to save in the physical path "/upload"
			count = mySmartUpload.save("/upload");
			
			// Save the files with their original names in the virtual path "/upload"
			// count = mySmartUpload.save("/upload", mySmartUpload.SAVE_VIRTUAL);

			// Display the number of files uploaded 
			System.out.println(count + " file(s) uploaded.");

		} catch (Exception e) { 
			System.out.println(e.toString());
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
