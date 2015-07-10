package com.topda.cooldevice;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

import com.topda.coolserver.ByteAndStr16;
import com.topda.event.DataEvent;
import com.topda.event.DataListener;

public class MyProtocolHandler extends IoHandlerAdapter {
	/*
	 * @Override public void messageReceived(IoSession session, Object message)
	 * throws Exception { SendMessage sm = (SendMessage) message;
	 * System.out.println("The message received is [ " + sm.getI() + " " +
	 * sm.getSymbol() + " " + sm.getJ() + " ]"); ResultMessage rm = new
	 * ResultMessage(); rm.setResult(sm.getI() + sm.getJ()); session.write(rm);
	 * if (message instanceof ByteBuffer) { ByteBuffer rb = (ByteBuffer)
	 * message; byte[] moMessage = new byte[rb.remaining()]; rb.get(moMessage);
	 * Endpoint endpoint = new MinaEndpoint(session);
	 * endpoint.receive(moMessage); } super.messageReceived(session, message); }
	 */
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
		System.out.println("err:" + cause.getMessage());
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// TODO Auto-generated method stub

		System.out.println("收到信息");
		IoBuffer bbuf = (IoBuffer) message;
		byte[] byten = new byte[bbuf.limit()];
		bbuf.get(byten, bbuf.position(), bbuf.limit());
		System.out.println("客户端收到消息" + ByteAndStr16.Bytes2HexString(byten));
		Incubator inc = new Incubator();
		inc.setBoxSn(ByteAndStr16.Bytes2HexString(byten));
		DataEvent de = new DataEvent(inc);

		if (this.dataListener != null) {
			dataListener.handleEvent(de);
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("小心发送");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println(session.getRemoteAddress().toString() + "---create");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println(session.getServiceAddress() + "IDS");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("连接打开：" + session.getLocalAddress());
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

}
