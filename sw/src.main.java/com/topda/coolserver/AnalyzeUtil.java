package com.topda.coolserver;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.topda.common.Utils;
import com.topda.cooldevice.Incubator;
import com.topda.cooldevice.SendMsg;

public class AnalyzeUtil{

	public Incubator analyze(byte[] receData)
	{
		Incubator inc = null;
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
				System.out.println("head is === "+Utils.byte2hex(start));
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
					  
					  System.out.println("the send message =="+Utils.byte2hex(start)+Utils.byte2hex(length)+Utils.byte2hex(data)+Utils.byte2hex(check)+Utils.byte2hex(end));
					  if(Utils.byte2hex(end).equals("EF")||end[0]==(byte)239)
					  {
						  inc =dealWithData(data);
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
		
		return inc;
	}
	public Incubator dealWithData(byte [] data) throws IOException, SQLException, ClassNotFoundException
	{
		Incubator inc = new Incubator();
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
				int type =(int)command;
				inc.setType(type);
				switch (type) 
				{
			     case 0:

					break;
				case 1:
				    {
					byte[] boxid = new byte[6];
					copy(data,boxid,2);
					String strboxid = Utils.byte2hex(boxid);
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
					String strboxid = Utils.byte2hex(boxid);
					if(strboxid!=null&&strboxid.length()>6)
					{
						strboxid = strboxid.substring(0, 6);
					}
					byte[] local = new byte[4];
					copy(data, local, 8);
					String strlocal = Utils.byte2hex(local);
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
					//byte[] ctime = getCurrTime();
					byte[] ctime = parseDate();
					
					String strboxid = Utils.byte2hex(boxid);
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
					//byte[] xq = parseDate();
					//senddata[senddata.length-1] = xq[0];
					byte[] sendmessage = getSendMessage(senddata);
/*					BufferedOutputStream os = new BufferedOutputStream(socket
							.getOutputStream());
					os.write(sendmessage);
					os.flush();
					os.close();*/

					// //send message end
					SendMsg msg = new SendMsg();
					msg.setSendMsg(sendmessage);
					inc.setSendMsg(msg);
					inc.setBoxSn(strboxid);
					break;
				   }
				case 8:
				   {
					byte[] boxid = new byte[6];
					copy(data, boxid, 2); 
					byte[] sim = new byte[20];
					copy(data, sim, 8); 
					
					String strboxid = Utils.byte2hex(boxid);
					if(strboxid!=null&&strboxid.length()>6)
					{
						strboxid = strboxid.substring(0, 6);
					}
					inc.setBoxSn(strboxid);
					System.out.println("strboxid:" + strboxid);
					
					String strsim = Utils.byte2hex(sim);
					if(strsim!=null&&strsim.length()>20)
					{
						strsim = strsim.substring(0, 20);
					}
					inc.setSim(strsim);
					System.out.println("strsim:" + strsim);
					// //send message start
					byte[] senddata = new byte[14];
					senddata[0] = reqnum;
					for (int i = 0; i < boxid.length; i++) {
						senddata[i + 1] = boxid[i];
					}

					//byte[] xq = parseDate();
					//senddata[senddata.length-1] = xq[0];
					byte[] sendmessage = getSendMessage(senddata);
/*					BufferedOutputStream os = new BufferedOutputStream(socket
							.getOutputStream());
					os.write(sendmessage);
					os.flush();
					os.close();*/

					// //send message end
					SendMsg msg = new SendMsg();
					msg.setSendMsg(sendmessage);
					inc.setSendMsg(msg);

					inc.setBoxSn(strboxid);
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
		
		return inc;

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
		 int strzs = Integer.parseInt(Utils.byte2hex(bzs),16);
		 byte[] bxs = new byte[1];
		 bxs[0] = wd[1];
		 int ixs = Integer.parseInt(Utils.byte2hex(bxs),16)*625/1000;		 
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
		 int ixs = Integer.parseInt(Utils.byte2hex(bxs),16)*625/1000;	
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
			 int strzs = Integer.parseInt(Utils.byte2hex(bzs),16);		 		 
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
	 String yyyy = String.valueOf(Integer.parseInt(Utils.byte2hex(by),16));
	 String mm = String.valueOf(Integer.parseInt(Utils.byte2hex(bm),16));
	 String dd = String.valueOf(Integer.parseInt(Utils.byte2hex(bd),16));
	 String hh = String.valueOf(Integer.parseInt(Utils.byte2hex(bh),16));
	 String mi = String.valueOf(Integer.parseInt(Utils.byte2hex(bmi),16));
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
 public static byte[] getCurrTime()
 {
	 byte[] ctime = new byte[6];
	 Calendar c = Calendar.getInstance();
	 String syy = Integer.toHexString(c.get(Calendar.YEAR));
	 String smm = Integer.toHexString(c.get(Calendar.MONTH)+1);
	 String sdd = Integer.toHexString(c.get(Calendar.DATE));
	 String shh = Integer.toHexString(c.get(Calendar.HOUR_OF_DAY));
	 String smi = Integer.toHexString(c.get(Calendar.MINUTE));
	 byte[] byy = Utils.hex2byte(syy);
	 byte[] bmm = Utils.hex2byte(smm);
	 byte[] bdd = Utils.hex2byte(sdd);
	 byte[] bhh = Utils.hex2byte(shh);
	 byte[] bmi = Utils.hex2byte(smi);
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
 
 public static byte[] parseDate() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		int year = calendar.get(Calendar.YEAR);
		String year1 = String.valueOf(year).substring(0,2);
		String year2 = String.valueOf(year).substring(2,4);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);

		byte[] byearpre = Utils.hex2byte(year1);
		byte[] byearend = Utils.hex2byte(year2);
		byte[] bmonth = Utils.hex2byte(String.valueOf(month));
		byte[] bday = Utils.hex2byte(String.valueOf(day));
		byte[] bhour = Utils.hex2byte(String.valueOf(hour));
		byte[] bminute = Utils.hex2byte(String.valueOf(minute));

		byte[] bdow = Utils.hex2byte(String.valueOf(dayOfWeek));

		byte [] currDate = new byte[7];
		
		System.arraycopy(byearpre, 0, currDate, 0, 1);
		System.arraycopy(byearend, 0, currDate, 1, 1);
		System.arraycopy(bmonth, 0, currDate, 2, 1);
		System.arraycopy(bday, 0, currDate, 3, 1);
		System.arraycopy(bhour, 0, currDate, 4, 1);
		System.arraycopy(bminute, 0, currDate, 5, 1);
		System.arraycopy(bdow, 0, currDate, 6, 1);
		return currDate;
	}

}
