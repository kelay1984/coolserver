package com.topda.coolserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AnalyzeUtil{

	public void analyze(byte[] receData)
	{

		try{
	        byte[] start = new byte[2];
	        int startPos =0;
	        byte[] length = new byte[1];
	        int lengthPos =start.length;
	        byte[] check = new byte[1];
	        
	        byte[] end = new byte[1]; 
	        

			if(receData!=null)
			{
				System.arraycopy(receData, startPos, start, 0, start.length);
				//is.read(start);	
				System.out.println("head is === "+byte2hex(start));
				//message head check byte -128~127
				if(start[0]==(byte)170&&start[1]==(byte)187)
				{
					System.arraycopy(receData, lengthPos, length, 0, length.length);
					//is.read(length);
					if(length[0]<=127&&length[0]>0)
					{   					
					  byte[] data = new byte[length[0]];
					  int dataPos =lengthPos+length.length;
/*					  is.read(data);
					  is.read(check);
					  is.read(end);*/
					  System.arraycopy(receData, dataPos, data, 0, data.length);
					  int checkPos =dataPos+data.length;
					  System.arraycopy(receData, checkPos, check, 0, check.length);
					  int endPos =checkPos+check.length;
					  System.arraycopy(receData, endPos, end, 0, end.length);
					  
					  System.out.println("the send message =="+byte2hex(start)+byte2hex(length)+byte2hex(data)+byte2hex(check)+byte2hex(end));
					  if(byte2hex(end).equals("EF")||end[0]==(byte)239)
					  {
						  dealWithData(data);
					  }
					  else
					  {
						  System.out.println("message end is wrong!");
					  }
					}
					else
					{
						System.out.println("the length of data is wrong!");
					}
				}
				else
				{
					System.out.println("message head is wrong!");

				}
			
			}
			else
			{
				System.out.println("socket is null");
			}

		}catch(Exception e){
			System.out.println("Error:"+e);
		}
	}
	public void dealWithData(byte [] data) throws IOException, SQLException, ClassNotFoundException
	{
		if(data!=null&&data.length>0)
		{
			byte reqnum = data[0];
			if(reqnum>=3)
			{
				System.out.println("request has been stop!");

			}
			else
			{
				byte command = data[1];
				switch (command) 
				{
			     case 0:

					break;
				case 1:
				    {
					byte[] boxid = new byte[6];
					copy(data,boxid,2);
					String strboxid = byte2hex(boxid);
					if(strboxid!=null&&strboxid.length()>6)
					{
						strboxid = strboxid.substring(0, 6);
					}
					byte[] wd = new byte[2];
					copy(data,wd,8);
					String strwd = changeWd(wd);
					byte[] htime = new byte[6];
					copy(data,htime,10);
					String strhtime = changeTime(htime);
					byte ds = data[data.length-2];
					byte dl = data[data.length-1];
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date nowtime = new Date();					
				    String ctime = formatter.format(nowtime);
//					String strsql = "insert into Monitor_CyberheadTemperature(BoxSN,ServerDatetime,Temperature,DateHappen) "+
//					   " values('"
//						+ strboxid+ "','"
//						+ ctime+ "',"
//						+ strwd+ ",'"
//						+ strhtime+"')";
					String strsql = "insert into Monitor_Temperature(BoxSN,ServerDatetime,Temperature,DateHappen,Power,DoorState,SupplyID) "+
					   " values('"+ strboxid+ "','"+ ctime+ "',"+ strwd+ ",'"+ strhtime+ "',"+ dl+ ","+ ds+ ","+ 2+")";
				    System.out.println("strsql====" + strsql);
				   // saveData(strsql);

				    byte[] senddata = new byte[8];
				    senddata[0] = reqnum;
				    for(int i=0;i<boxid.length;i++)
				    {
				    	senddata[i+1] = boxid[i];
				    }
				    senddata[7] = 1;
/*				    byte[] sendmessage = getSendMessage(senddata);
				    BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream());
				    os.write(sendmessage);
				    os.flush();
				    os.close();*/
				    ////send message end
					break;
				   }
				case 2:
					break;
				case 3:
				   {
					 byte[] boxid = new byte[6];
					copy(data, boxid, 2);
					String strboxid = byte2hex(boxid);
					if(strboxid!=null&&strboxid.length()>6)
					{
						strboxid = strboxid.substring(0, 6);
					}
					byte[] local = new byte[4];
					copy(data, local, 8);
					String strlocal = byte2hex(local);
					byte[] htime = new byte[6];
					copy(data,htime,12);
					String strhtime = changeTime(htime);
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Date nowtime = new Date();
					String ctime = formatter.format(nowtime);
//					String strsql = "insert into Monitor_CyberheadTemperature(BoxSN,ServerDatetime,Local) "
//							+ " values("
//							+ strboxid
//							+ ","
//							+ ctime
//							+ ","
//							+ strlocal+ ")";
					String strsql = "insert into Monitor_GPS(BoxSN,ServerDatetime,BaseStation,SupplyID,DateHappen) "
						+ " values('"+ strboxid+ "','"+ ctime+ "','"+ strlocal+ "',"+ 2+",'"+strhtime+"')";					
					System.out.println("strsql====" + strsql);
//					System.out.println("BoxSN=" + strboxid + "ServerDatetime="
//							+ ctime + "Local=" + strlocal);
					saveData(strsql);
					// //send message start
					byte[] senddata = new byte[8];
					senddata[0] = reqnum;
					for (int i = 0; i < boxid.length; i++) {
						senddata[i + 1] = boxid[i];
					}
					senddata[7] = 1;
/*					byte[] sendmessage = getSendMessage(senddata);
					BufferedOutputStream os = new BufferedOutputStream(socket
							.getOutputStream());
					os.write(sendmessage);
					os.flush();
					os.close();*/

					// //send message end
					break;
				   }
				case 4:
				   {
					byte[] boxid = new byte[6];
					copy(data, boxid, 2); 
					byte[] ctime = getCurrTime();
					
					String strboxid = byte2hex(boxid);
					if(strboxid!=null&&strboxid.length()>6)
					{
						strboxid = strboxid.substring(0, 6);
					}
					System.out.println("strboxid:" + strboxid);
					// //send message start
					byte[] senddata = new byte[14];
					senddata[0] = reqnum;
					for (int i = 0; i < boxid.length; i++) {
						senddata[i + 1] = boxid[i];
					}
					for (int i = 0; i < ctime.length; i++) {
						senddata[7+i] = ctime[i];
					}
					byte[] xq = parseDate();
					senddata[senddata.length-1] = xq[0];
					byte[] sendmessage = getSendMessage(senddata);
/*					BufferedOutputStream os = new BufferedOutputStream(socket
							.getOutputStream());
					os.write(sendmessage);
					os.flush();
					os.close();*/

					// //send message end
					break;
				   }
				case (byte) 128:
				   {
					byte[] boxid = new byte[6];
					copy(data,boxid,2);
				    ////send message start
				    byte[] senddata = new byte[8];
				    senddata[0] = reqnum;
				    for(int i=0;i<boxid.length;i++)
				    {
				    	senddata[i+1] = boxid[i];
				    }
				    senddata[7] = 1;
				    byte[] sendmessage = getSendMessage(senddata);
/*				    BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream());
				    os.write(sendmessage);
				    os.flush();
				    os.close();*/

				    ////send message end
					break;
				   }
				default:

					break;		
				}
			}
			
		}
		else
		{
			System.out.println("data is null!");				
		
		}

	}
	public void saveData(String sql) throws SQLException, ClassNotFoundException
	{
/*        Connection conn = null;
        Statement stmt = null;
		DataBaseConn dbc = new DataBaseConn();
		conn =dbc.getSqlserverConn();	
		if(conn==null)
		{
			System.out.println("get connect false!");
		}
		else
		{
			stmt = conn.createStatement();
			System.out.println(sql);
			stmt.execute(sql);
			stmt.close();
			conn.commit();
			conn.close();
		}	*/													
	}
public byte[] hex2byte(String hex)
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
public byte toByte(char c)
	{
	  byte b = (byte)"0123456789ABCDEF".indexOf(c);
	  return b;
	}
 public String byte2hex(byte[] b) {
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
 
 public byte[] copy(byte[] a,byte[] b,int s)
 {
	 for(int i=0;i<b.length;i++)
	 {
		 b[i] = a[i+s];
	 }
	 return b;
 }
 /*
 public String changeWd(byte[] wd)
 {
	 String strwd = "";
	 if(wd!=null&&wd.length==2)
	 {
		 //+shu 
		 byte[] bzs = new byte[1];
		 bzs[0] = wd[0];
		 int strzs = Integer.parseInt(byte2hex(bzs),16);
		 byte[] bxs = new byte[1];
		 bxs[0] = wd[1];
		 int ixs = Integer.parseInt(byte2hex(bxs),16)*625/1000;		 
		 float wendu = strzs+(float)ixs/10;
		 strwd = String.valueOf(wendu);
	 }	 
	 return strwd;	 
 }
 */
 public String changeWd(byte[] wd)
 {
	 String strwd = "";
	 if(wd!=null&&wd.length==2)
	 {
		 byte[] bzs = new byte[1];
		 bzs[0] = wd[0];         		 
		 byte[] bxs = new byte[1];
		 bxs[0] = wd[1];
		 int ixs = Integer.parseInt(byte2hex(bxs),16)*625/1000;	
		 int flag = (int)bzs[0] & 0x80;
		 if(flag==128)
		 {
         //   System.out.println("负数处理flag ==="+flag);
            flag =  bzs[0] & 0x7F;
			System.out.println("flag ==="+flag);
            strwd = "-";		 	 
		    float wendu = flag+(float)ixs/10;
		    strwd += String.valueOf(wendu);

		 }
		 else
		 {
			 System.out.println("正数处理flag ==="+flag);
			 strwd = "+";
			 int strzs = Integer.parseInt(byte2hex(bzs),16);		 		 
		     float wendu = strzs+(float)ixs/10;
		     strwd += String.valueOf(wendu);
		 }

		 
	 }	 
	 return strwd;	 
 }
 public String changeTime(byte[] time)
 {
	 byte[] by = new byte[2];
	 byte[] bm = new byte[1];
	 byte[] bd = new byte[1];
	 byte[] bh = new byte[1];
	 byte[] bmi = new byte[1];
	 copy(time,by,0);
	 bm[0] = time[2];
	 bd[0] = time[3];
	 bh[0] = time[4];
	 bmi[0] = time[5];
	 String yyyy = String.valueOf(Integer.parseInt(byte2hex(by),16));
	 String mm = String.valueOf(Integer.parseInt(byte2hex(bm),16));
	 String dd = String.valueOf(Integer.parseInt(byte2hex(bd),16));
	 String hh = String.valueOf(Integer.parseInt(byte2hex(bh),16));
	 String mi = String.valueOf(Integer.parseInt(byte2hex(bmi),16));
	 return yyyy+"-"+mm+"-"+dd+" "+hh+":"+mi+":"+"00";	 
 }
 public byte[] getSendMessage(byte[] data)
 {
	 byte[] sendmessage = new byte[data.length+5];
	 sendmessage[0] = (byte)204;
	 sendmessage[1] = (byte)221;
	 sendmessage[2] = (byte)data.length;
	 for(int i=0;i<data.length;i++)
	 {
		 sendmessage[3+i] = data[i];
	 }
	 sendmessage[data.length+3] = (byte)data.length;//check
	 sendmessage[data.length+4] = (byte)239;
	 return sendmessage;
 }
 public byte[] getCurrTime()
 {
	 byte[] ctime = new byte[6];
	 Calendar c = Calendar.getInstance();
	 String syy = Integer.toHexString(c.get(Calendar.YEAR));
	 String smm = Integer.toHexString(c.get(Calendar.MONTH)+1);
	 String sdd = Integer.toHexString(c.get(Calendar.DATE));
	 String shh = Integer.toHexString(c.get(Calendar.HOUR_OF_DAY));
	 String smi = Integer.toHexString(c.get(Calendar.MINUTE));
	 byte[] byy = hex2byte(syy);
	 byte[] bmm = hex2byte(smm);
	 byte[] bdd = hex2byte(sdd);
	 byte[] bhh = hex2byte(shh);
	 byte[] bmi = hex2byte(smi);
	 if(byy!=null&&byy.length==2)
	 {
		 ctime[0] = byy[0];
		 ctime[1] = byy[1];
	 }
	 if(bmm!=null)
	 {
		 ctime[2] = bmm[0];
	 }
	 if(bdd!=null)
	 {
		 ctime[3] = bdd[0];
	 }
	 if(bhh!=null)
	 {
		 ctime[4] = bhh[0];
	 }
	 if(bmi!=null)
	 {
		 ctime[5] = bmi[0];
	 }
	 return ctime;
 }
 
 public byte[] parseDate() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		byte[] byy = hex2byte(String.valueOf(dayOfWeek));
		return byy;
	}
 
}
