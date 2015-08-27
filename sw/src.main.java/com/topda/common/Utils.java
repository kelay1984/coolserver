package com.topda.common;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

public class Utils {
	public static byte[] hex2byte(String hex) {
		String shex = hex.toUpperCase();
		if ((shex.length() % 2) != 0) {
			shex = "0" + shex;
		}
		int len = (shex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = shex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;

	}

	public static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	public static String byte2hex(byte[] b) {
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

	public static int byte2int(byte[] res) {
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000

		int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示安位或
				| ((res[2] << 24) >>> 8) | (res[3] << 24);
		return targets;
	}

	public static Date formatParaTime(byte[] time) {
		byte[] by = new byte[2];

		byte[] bm = new byte[1];
		byte[] bd = new byte[1];
		byte[] bh = new byte[1];
		byte[] bmi = new byte[1];
		by[0] = time[0];
		by[1] = time[1];
		// Array.Copy(time, 0, by, 0, 2);
		bm[0] = time[2];
		bd[0] = time[3];
		if (time[4] == 0 && time[5] == 0) {
			bh[0] = 0;
			bmi[0] = 0;
		} else {
			bh[0] = time[4];
			bmi[0] = time[5];
		}
		int yy = Integer.parseInt(byte2hex(by), 16);
		int mm = Integer.parseInt(byte2hex(bm), 16);
		int dd = Integer.parseInt(byte2hex(bd), 16);
		int hh = Integer.parseInt(byte2hex(bh), 16);
		int mi = Integer.parseInt(byte2hex(bmi), 16);

		Calendar c = Calendar.getInstance();
		c.set(yy, mm, dd, hh, mi, 0);

		return c.getTime();
	}

	// / <summary>
	// / 温度转换函数
	// / </summary>
	// / <param name="wd"></param>
	// / <returns></returns>
	public static String ChangeWD(byte[] wd) {
		String strWD = "";
		if (wd != null && wd.length == 2) {
			byte[] bzs = new byte[1];
			bzs[0] = wd[0];
			byte[] bxs = new byte[1];
			bxs[0] = wd[1];
			int ixs = (Integer.parseInt(byte2hex(bxs), 16)) * 625 / 1000;
			int flag = (int) bzs[0] & 0x80;
			if (flag == 128) {
				// System.out.println("负数处理flag ==="+flag);
				flag = bzs[0] & 0x7F;
				strWD = "-";
				float wendu = flag + (float) ixs / 10;
				strWD += String.valueOf(wendu);
			} else {
				// strWD = "+";
				int strzs = Integer.parseInt(byte2hex(bzs), 16);
				float wendu = strzs + (float) ixs / 10;
				strWD = String.valueOf(wendu);
			}
		}
		return strWD;
	}

	public static String padLeft(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);// 左补0
				// sb.append(str).append("0");//右补0
				str = sb.toString();
				strLen = str.length();
			}
		}
		return str;
	}

	public static String padRight(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append(str).append("0");// 右补0
				str = sb.toString();
				strLen = str.length();
			}
		}
		return str;
	}

	public static String GetMD5Code(String strObj) {
		String resultString = null;
		try {
			resultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");
			// md.digest() 该函数返回值为存放哈希值结果的byte数组
			resultString = byte2hex(md.digest(strObj.getBytes()));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return resultString;
	}
	
    public static String convertMD5(String inStr){  

    	  
    	  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    } 

	public static String Encrypt(String strSrc) {
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		try {

			md = MessageDigest.getInstance("SHA-1");
			md.update(bt);
			strDes = byte2hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Invalid algorithm.");
			return null;
		}
		return strDes;
	}

	public String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
	
	public static String getLocalMac(InetAddress ia) {
		StringBuffer sb =new StringBuffer();
		try {
			byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
			
			
			for(int i=0; i<mac.length; i++) {
				if(i!=0) {
					if(i%2==0)
						sb.append("-");
					if(i%3==0)
						sb.append("+");
					if(i%5==0)
						sb.append("*");
				}
				//字节转换为整数
				int temp = mac[i]&0xff;
				String str = Integer.toHexString(temp);
				
				if(str.length()==1) {
					sb.append("0"+str);
				}else {
					sb.append(str);
				}
			}

		} catch (SocketException e) {
			e.printStackTrace();
		}

		return sb.toString().toUpperCase();
		
	}
    
}
