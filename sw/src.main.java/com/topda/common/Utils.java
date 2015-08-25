package com.topda.common;

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
	
    public static Date formatParaTime(byte[] time)
    {
        byte[] by = new byte[2];

        byte[] bm = new byte[1];
        byte[] bd = new byte[1];
        byte[] bh = new byte[1];
        byte[] bmi = new byte[1];
        by[0] = time[0];
        by[1] = time[1];
        //Array.Copy(time, 0, by, 0, 2);
        bm[0] = time[2];
        bd[0] = time[3];
        if (time[4] == 0 && time[5] == 0)
        {
            bh[0] = 0;
            bmi[0] = 0;
        }
        else
        {
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
    
    /// <summary>
    /// 温度转换函数
    /// </summary>
    /// <param name="wd"></param>
    /// <returns></returns>
    public static String ChangeWD(byte[] wd)
    {
        String strWD = "";
        if (wd != null && wd.length == 2)
        {
            byte[] bzs = new byte[1];
            bzs[0] = wd[0];
            byte[] bxs = new byte[1];
            bxs[0] = wd[1];
            int ixs = (Integer.parseInt(byte2hex(bxs), 16)) * 625 / 1000;
            int flag = (int)bzs[0] & 0x80;
            if (flag == 128)
            {
                //   System.out.println("负数处理flag ==="+flag);
                flag = bzs[0] & 0x7F;
                strWD = "-";
                float wendu = flag + (float)ixs / 10;
                strWD += String.valueOf(wendu);
            }
            else
            {
                //strWD = "+";
                int strzs = Integer.parseInt(byte2hex(bzs), 16);
                float wendu = strzs + (float)ixs / 10;
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
    	    sb.append("0").append(str);//左补0
//    	    sb.append(str).append("0");//右补0
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
  	    sb.append(str).append("0");//右补0
  	    str = sb.toString();
  	    strLen = str.length();
  	   }
  	  }
  	  return str;
  	 }
}
