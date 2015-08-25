package com.topda.cooldevice;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topda.common.Constant;
import com.topda.common.Utils;
import com.topda.vo.DeviceTemprature;
import com.topda.vo.Incubator;
import com.topda.vo.SendMsg;

public class AnalyzeUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(AnalyzeUtil.class);

	/**
	 * 报文结构解析
	 * @param receData
	 * @return
	 */
	public Incubator analyze(byte[] receData) {
		Incubator inc = null;
		try {
			byte[] start = new byte[2];
			int startPos = 0;
			byte[] length = new byte[1];
			int lengthPos = start.length;
			byte[] check = new byte[1];

			byte[] end = new byte[1];

			if (receData != null) {
				System.arraycopy(receData, startPos, start, 0, start.length);

				// message head check byte -128~127
				if (start[0] == (byte) 170 && start[1] == (byte) 187) {
					System.arraycopy(receData, lengthPos, length, 0,
							length.length);

					if (length[0] <= 127 && length[0] > 0) {
						byte[] data = new byte[length[0]];
						int dataPos = lengthPos + length.length;

						System.arraycopy(receData, dataPos, data, 0,
								data.length);
						int checkPos = dataPos + data.length;
						System.arraycopy(receData, checkPos, check, 0,
								check.length);
						int endPos = checkPos + check.length;
						System.arraycopy(receData, endPos, end, 0, end.length);

						if (Utils.byte2hex(end).equals("EF")
								|| end[0] == (byte) 239) {
							inc = dealWithData(data);
						} else {
							logger.error("message end is wrong!");
						}
					} else {
						logger.error("the length of data is wrong!");
					}
				} else {
					logger.error("message head is wrong!");

				}

			} else {
				logger.error("socket is null");
			}

		} catch (Exception e) {
			logger.error("err:", e);
		}

		return inc;
	}

	/**
	 * 正确报文内容解析
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Incubator dealWithData(byte[] data) throws IOException,
			SQLException, ClassNotFoundException {
		Incubator inc = new Incubator();
		if (data != null && data.length > 0) {
			byte reqnum = data[0];
			if (reqnum >= 3) {
				logger.error("request has been stop!");

			} else {
				byte[] boxid = new byte[6]; // ID
				byte[] byWD = new byte[2]; // 温度
				byte[] byAllTM = new byte[6]; // 时间
				byte[] byLocal = new byte[4]; // 基站坐标
				byte[] bOutWd = new byte[2]; // 外部温度
				byte[] infoCount = new byte[1]; // 信息包条数
				byte[] infoTime = new byte[2]; // 信息包条数
				byte[] infoDoor = new byte[1]; // 信息包内容

				byte command = data[1];
				int type = (int) command;
				inc.setType(type);
				switch (type) {
				case 0:

					break;
				case 1: {
					copy(data, boxid, 2);
					String strboxid = Utils.byte2hex(boxid);
					if (strboxid != null && strboxid.length() > 6) {
						strboxid = strboxid.substring(0, 6);
					}
					byte[] wd = new byte[2];
					copy(data, wd, 8);
					String strwd = changeWd(wd);
					byte[] htime = new byte[6];
					copy(data, htime, 10);
					String strhtime = changeTime(htime);
					byte ds = data[data.length - 2];
					byte dl = data[data.length - 1];
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Date nowtime = new Date();
					String ctime = formatter.format(nowtime);
					// saveData(strsql);

					byte[] senddata = new byte[8];
					senddata[0] = reqnum;
					for (int i = 0; i < boxid.length; i++) {
						senddata[i + 1] = boxid[i];
					}
					senddata[7] = 1;

					// //send message end
					break;
				}
				case 2: {
					String strWD = "";
					String strOutWD = "";
					String strState = "";
					String strSupWarn = "";
					String strSupRes = "";
					String strLowRes = "";
					String strLowWarn = "";
					String strDoorState = "";
					String strPower = "";

					char[] strStates = null;
					byte[] reqtype = new byte[1];
					byte[] byTM = new byte[4];

					copy(data, reqtype, 1);
					copy(data, boxid, 2);
					copy(data, byTM, 8);
					copy(data, infoCount, 12);

					System.arraycopy(byTM, 0, byAllTM, 0, 4);

					String strboxid = Utils.byte2hex(boxid);
					if (strboxid != null && strboxid.length() > 6) {
						strboxid = strboxid.substring(0, 6);
					}
					inc.setBoxSn(strboxid);

					int count = Integer.parseInt(Utils.byte2hex(infoCount), 16);

					List<DeviceTemprature> devtemLst = new ArrayList<DeviceTemprature>();
					for (int i = 0; i < count; i++) {
						copy(data, byWD, 13 + 7 * i);
						copy(data, bOutWd, 15 + 7 * i);
						copy(data, infoTime, 17 + 7 * i);
						copy(data, infoDoor, 19 + 7 * i);
						// 添加时间数组的时分数值
						System.arraycopy(infoTime, 0, byAllTM, 4, 2);
						strWD = Utils.ChangeWD(byWD);
						if ((int) reqtype[0] == 2) // 请求号为1时室外温度不采集
							strOutWD = Utils.ChangeWD(bOutWd);
						else
							strOutWD = "0"; // 转换2进制字符串 用0补齐6位

						strState = Integer.toBinaryString(infoDoor[0]);
						strState = Utils.padLeft(strState, 6);
						strStates = strState.toCharArray();
						strDoorState = String.valueOf(strStates[5]); // 门磁——第0bit
						strPower = String.valueOf(strStates[4]); // 电量——第1bit
						strSupWarn = String.valueOf(strStates[3]); // 超高警——第2bit
						strSupRes = String.valueOf(strStates[2]); // 超高限——第3bit
						strLowRes = String.valueOf(strStates[1]); // 超低限——第4bit
						strLowWarn = String.valueOf(strStates[0]); // 超低警——第5bit

						Date dtsa = Utils.formatParaTime(byAllTM);
						DeviceTemprature temprature = new DeviceTemprature();
						temprature.setCollectTime(dtsa);
						temprature.setTemperature(strWD);
						temprature.setOutTemperature(strOutWD);

						devtemLst.add(temprature);

					}
					inc.setDevInfo(devtemLst);
					inc.setDoorState(strDoorState);
					inc.setHighAlarmThreshold(strSupWarn);
					inc.setHighThresholdLimit(strSupRes);
					inc.setLowAlarmThreshold(strLowWarn);
					inc.setLowThresholdLimit(strLowRes);
					inc.setPower(strPower);

					// //send message start
					byte[] senddata = new byte[8];
					senddata[0] = reqnum;
					for (int i = 0; i < boxid.length; i++) {
						senddata[i + 1] = boxid[i];
					}
					senddata[7] = 1;

					byte[] sendmessage = getSendMessage(senddata);
					// //send message end
					SendMsg msg = new SendMsg();
					msg.setSendMsg(sendmessage);
					inc.setSendMsg(msg);
					inc.setBoxSn(strboxid);
					break;
				}
				case 3: {
					copy(data, boxid, 2);
					String strboxid = Utils.byte2hex(boxid);
					if (strboxid != null && strboxid.length() > 6) {
						strboxid = strboxid.substring(0, 6);
					}
					byte[] local = new byte[4];
					copy(data, local, 8);
					String strlocal = Utils.byte2hex(local);
					byte[] htime = new byte[6];
					copy(data, htime, 12);
					String strhtime = changeTime(htime);
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Date nowtime = new Date();
					String ctime = formatter.format(nowtime);
					// //send message start
					byte[] senddata = new byte[8];
					senddata[0] = reqnum;
					for (int i = 0; i < boxid.length; i++) {
						senddata[i + 1] = boxid[i];
					}
					senddata[7] = 1;
					// //send message end
					break;
				}
				case 4: {
					copy(data, boxid, 2);
					// byte[] ctime = getCurrTime();
					byte[] ctime = parseDate();

					String strboxid = Utils.byte2hex(boxid);
					if (strboxid != null && strboxid.length() > 6) {
						strboxid = strboxid.substring(0, 6);
					}
					// //send message start
					byte[] senddata = new byte[25];
					senddata[0] = reqnum;
					for (int i = 0; i < boxid.length; i++) {
						senddata[i + 1] = boxid[i];
					}
					for (int i = 0; i < ctime.length; i++) {
						senddata[7 + i] = ctime[i];
					}
					senddata[14] = Constant.bState;
					senddata[15] = Constant.bCollCycle;
					senddata[16] = Constant.bStoreCycle;
					senddata[17] = Constant.bSupStoreCycle;
					senddata[18] = Constant.bUpCycle;
					senddata[19] = Constant.bSupUpCycle;
					senddata[20] = Constant.bSHWaring;
					senddata[21] = Constant.bSHRestriction;
					senddata[22] = Constant.bSLWaring;
					senddata[23] = Constant.bSLRestriction;
					senddata[24] = Constant.bReserve;

					byte[] sendmessage = getSendMessage(senddata);

					// //send message end
					SendMsg msg = new SendMsg();
					msg.setSendMsg(sendmessage);
					inc.setSendMsg(msg);
					inc.setBoxSn(strboxid);
					break;
				}
				case 8: {
					copy(data, boxid, 2);
					byte[] sim = new byte[20];
					copy(data, sim, 8);

					String strboxid = Utils.byte2hex(boxid);
					if (strboxid != null && strboxid.length() > 6) {
						strboxid = strboxid.substring(0, 6);
					}
					inc.setBoxSn(strboxid);
					String strsim = Utils.byte2hex(sim);

					if (strsim != null && strsim.length() > 40) {
						strsim = strsim.substring(0, 40);
					}
					inc.setSim(strsim);

					// //send message start
					byte[] senddata = new byte[8];
					senddata[0] = reqnum;
					for (int i = 0; i < boxid.length; i++) {
						senddata[i + 1] = boxid[i];
					}
					senddata[7] = 1;

					byte[] sendmessage = getSendMessage(senddata);
					// //send message end
					SendMsg msg = new SendMsg();
					msg.setSendMsg(sendmessage);
					inc.setSendMsg(msg);

					inc.setBoxSn(strboxid);
					break;
				}
				case (byte) 128: {
					copy(data, boxid, 2);
					// //send message start
					byte[] senddata = new byte[8];
					senddata[0] = reqnum;
					for (int i = 0; i < boxid.length; i++) {
						senddata[i + 1] = boxid[i];
					}
					senddata[7] = 1;
					byte[] sendmessage = getSendMessage(senddata);
					// //send message end
					break;
				}
				default:

					break;
				}
			}

		} else {
			System.out.println("data is null!");

		}

		return inc;

	}

	public byte[] copy(byte[] a, byte[] b, int s) {
		for (int i = 0; i < b.length; i++) {
			b[i] = a[i + s];
		}
		return b;
	}

	public String changeWd(byte[] wd) {
		String strwd = "";
		if (wd != null && wd.length == 2) {
			byte[] bzs = new byte[1];
			bzs[0] = wd[0];
			byte[] bxs = new byte[1];
			bxs[0] = wd[1];
			int ixs = Integer.parseInt(Utils.byte2hex(bxs), 16) * 625 / 1000;
			int flag = (int) bzs[0] & 0x80;
			if (flag == 128) {
				// System.out.println("负数处理flag ==="+flag);
				flag = bzs[0] & 0x7F;
				//System.out.println("flag ===" + flag);
				strwd = "-";
				float wendu = flag + (float) ixs / 10;
				strwd += String.valueOf(wendu);

			} else {
				//System.out.println("正数处理flag ===" + flag);
				strwd = "+";
				int strzs = Integer.parseInt(Utils.byte2hex(bzs), 16);
				float wendu = strzs + (float) ixs / 10;
				strwd += String.valueOf(wendu);
			}

		}
		return strwd;
	}

	public String changeTime(byte[] time) {
		byte[] by = new byte[2];
		byte[] bm = new byte[1];
		byte[] bd = new byte[1];
		byte[] bh = new byte[1];
		byte[] bmi = new byte[1];
		copy(time, by, 0);
		bm[0] = time[2];
		bd[0] = time[3];
		bh[0] = time[4];
		bmi[0] = time[5];
		String yyyy = String.valueOf(Integer.parseInt(Utils.byte2hex(by), 16));
		String mm = String.valueOf(Integer.parseInt(Utils.byte2hex(bm), 16));
		String dd = String.valueOf(Integer.parseInt(Utils.byte2hex(bd), 16));
		String hh = String.valueOf(Integer.parseInt(Utils.byte2hex(bh), 16));
		String mi = String.valueOf(Integer.parseInt(Utils.byte2hex(bmi), 16));
		return yyyy + "-" + mm + "-" + dd + " " + hh + ":" + mi + ":" + "00";
	}

	public byte[] getSendMessage(byte[] data) {
		byte[] sendmessage = new byte[data.length + 5];
		sendmessage[0] = (byte) 204;
		sendmessage[1] = (byte) 221;
		sendmessage[2] = (byte) data.length;
		for (int i = 0; i < data.length; i++) {
			sendmessage[3 + i] = data[i];
		}
		sendmessage[data.length + 3] = (byte) data.length;// check
		sendmessage[data.length + 4] = (byte) 239;
		return sendmessage;
	}

	public static byte[] getCurrTime() {
		byte[] ctime = new byte[6];
		Calendar c = Calendar.getInstance();
		String syy = Integer.toHexString(c.get(Calendar.YEAR));
		String smm = Integer.toHexString(c.get(Calendar.MONTH) + 1);
		String sdd = Integer.toHexString(c.get(Calendar.DATE));
		String shh = Integer.toHexString(c.get(Calendar.HOUR_OF_DAY));
		String smi = Integer.toHexString(c.get(Calendar.MINUTE));
		byte[] byy = Utils.hex2byte(syy);
		byte[] bmm = Utils.hex2byte(smm);
		byte[] bdd = Utils.hex2byte(sdd);
		byte[] bhh = Utils.hex2byte(shh);
		byte[] bmi = Utils.hex2byte(smi);
		if (byy != null && byy.length == 2) {
			ctime[0] = byy[0];
			ctime[1] = byy[1];
		}
		if (bmm != null) {
			ctime[2] = bmm[0];
		}
		if (bdd != null) {
			ctime[3] = bdd[0];
		}
		if (bhh != null) {
			ctime[4] = bhh[0];
		}
		if (bmi != null) {
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
		String yearString = Integer.toHexString(year);
		yearString = Utils.padLeft(yearString, 4);
		String year1 = String.valueOf(yearString).substring(0, 2);
		String year2 = String.valueOf(yearString).substring(2, 4);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);

		byte[] byearpre = Utils.hex2byte(year1);
		byte[] byearend = Utils.hex2byte(year2);
		byte[] bmonth = Utils.hex2byte(Integer.toHexString(month + 1));
		byte[] bday = Utils.hex2byte(Integer.toHexString(day));
		byte[] bhour = Utils.hex2byte(Integer.toHexString(hour));
		byte[] bminute = Utils.hex2byte(Integer.toHexString(minute));

		byte[] bdow = Utils.hex2byte(Integer.toHexString(dayOfWeek));

		byte[] currDate = new byte[7];

		System.arraycopy(byearpre, 0, currDate, 0, 1);
		System.arraycopy(byearend, 0, currDate, 1, 1);
		System.arraycopy(bmonth, 0, currDate, 2, 1);
		System.arraycopy(bday, 0, currDate, 3, 1);
		System.arraycopy(bhour, 0, currDate, 4, 1);
		System.arraycopy(bminute, 0, currDate, 5, 1);
		System.arraycopy(bdow, 0, currDate, 6, 1);
		return currDate;
	}

	public static void main(String[] args) {
		/*
		 * String yearString=Integer.toHexString(2015); yearString =
		 * Utils.padLeft(yearString, 4); System.out.println(yearString); String
		 * year1 = yearString.substring(0,2); String year2
		 * =yearString.substring(2,4); System.out.println(year1);
		 * System.out.println(year2);
		 */

		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		String yearString = Integer.toHexString(year);
		yearString = Utils.padLeft(yearString, 4);

		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		System.out.println(hour);
		System.out.println(Integer.toHexString(hour));
	}

}
