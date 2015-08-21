package com.topda.common;

public class Utils {
	public static  byte[] hex2byte(String hex)
	{
	   String shex = hex.toUpperCase();
	   if((shex.length()%2)!=0)
	   {
		   shex = "0"+shex;
	   }
	   int len = (shex.length()/2);
	   byte[] result = new byte[len];
	   char[] achar = shex.toCharArray();
	   for(int i=0;i<len;i++)
	   {
	     int pos = i*2;
	     result[i] = (byte)(toByte(achar[pos])<<4|toByte(achar[pos+1]));
	   }
	   return result;
	   
	}
	public static  byte toByte(char c)
	{
	  byte b = (byte)"0123456789ABCDEF".indexOf(c);
	  return b;
	}
	
	 public static  String byte2hex(byte[] b) {
			String hs = "";
			String tmp = "";
			for (int n = 0; n < b.length; n++) {

				tmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

				if (tmp.length() == 1) {
					hs = hs + "0" + tmp;

				} else {
					hs = hs + tmp;
				}
			}
			tmp = null;
			return hs.toUpperCase();
		} 
}
