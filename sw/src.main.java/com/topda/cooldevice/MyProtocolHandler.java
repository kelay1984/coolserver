package com.topda.cooldevice;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.StringUtils;
import com.topda.common.ByteAndStr16;
import com.topda.common.Utils;
import com.topda.event.DataEvent;
import com.topda.event.DataListener;
import com.topda.vo.Incubator;

public class MyProtocolHandler extends IoHandlerAdapter {
	private static final Logger logger = LoggerFactory
			.getLogger(MyProtocolHandler.class);
	private DataListener dataListener;// 监听自己的监听器队列

	public DataListener getDataListener() {
		return dataListener;
	}

	public void setDataListener(DataListener dataListener) {
		this.dataListener = dataListener;
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.error("err:" + cause.getMessage());
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {

		IoBuffer bbuf = (IoBuffer) message;
		byte[] byten = new byte[bbuf.limit()];
		bbuf.get(byten, bbuf.position(), bbuf.limit());
		logger.debug("rece msg:" + ByteAndStr16.Bytes2HexString(byten));
		AnalyzeUtil alu = new AnalyzeUtil();
		Incubator inc =alu.analyze(byten);

		if(inc.getSendMsg()!=null){
			logger.debug("server send："+ByteAndStr16.Bytes2HexString(inc.getSendMsg().getSendMsg()));
			session.write(IoBuffer.wrap(inc.getSendMsg().getSendMsg()));
		}
		DataEvent de = new DataEvent(inc);

		if (this.dataListener != null) {
			dataListener.handleEvent(de);
		}
		
		session.close(false);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		logger.debug("send");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.debug(session.getRemoteAddress().toString() + "---create");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		logger.debug(session.getServiceAddress() + "IDS");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.debug("连接打开：" + session.getLocalAddress());
	}

	public class SendMessage {
		private int i = 0;
		private int j = 0;
		private char symbol = '+';

		public char getSymbol() {
			return symbol;
		}

		public void setSymbol(char symbol) {
			this.symbol = symbol;
		}

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}

		public int getJ() {
			return j;
		}

		public void setJ(int j) {
			this.j = j;
		}
	}

	public class ResultMessage {
		private int result = 0;

		public int getResult() {
			return result;
		}

		public void setResult(int result) {
			this.result = result;
		}
	}
	
	public static void main(String args[]){
		byte[] byearpre = Utils.hex2byte("20");
		System.out.println(ByteAndStr16.Bytes2HexString(byearpre));
		System.out.println(StringUtils.isNullOrEmpty("222"));
	}

}
