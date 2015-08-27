package com.topda.cooldevice;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.topda.common.Utils;

/**
 * Servlet implementation class AppProInit
 */
public class AppProInit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppProInit() {
        super();
        // TODO Auto-generated constructor stub
    }
    
public static void main(String args[]) throws ParseException{
	
	System.out.println(Utils.GetMD5Code("ccdd我dd是ff谁eeff"));
	System.out.println(Utils.convertMD5(Utils.GetMD5Code("ccdd我dd是ff谁eeff")));
	System.out.println(Utils.convertMD5(Utils.convertMD5(Utils.convertMD5(Utils.GetMD5Code("ccdd我dd是ff谁eeff")))));
	
	DateFormat df = new SimpleDateFormat("yyyyMMdd");
	Date aa = df.parse("20150905");
	
	System.out.println(aa.after(new Date()));
	
	  Integer aInteger = new Integer(222);
	  Integer bInteger = new Integer(222);
	  Integer cInteger = new Integer(333);
	  
	  System.out.println(aInteger.compareTo(bInteger));
	  System.out.println(aInteger.compareTo(cInteger));
}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
