package weico.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import weico.client.ClientFrame;
import weico.exception.MessageException;

public class ProcessMsgThread extends Thread {
	private Socket socket;
	private ClientFrame	client;
	private String		backMessage;

	public ProcessMsgThread(Socket socket, ClientFrame client) {
		this.client = client;
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		try {
			br = new BufferedReader(
				new InputStreamReader(socket.getInputStream(), "GBK"));
			String msg = br.readLine();
			// 控制台输出客户端传过来的信息
			System.out.print("客户端口号:" + socket.getLocalPort() + "\n" +
					"IP地址:" + socket.getInetAddress() + "\n" + "他说: " + msg
					+ "\n");
			// 文本框输出客户端传过来的信息
			// 谁说的
			// 什么时间
			// IP地址
			// 端口号
			// 按协议拆分 协议 --> 姓名&说的内容

			try {
				String[] messageAll = msg.split("&");
				if (messageAll.length != 2) {
					throw new MessageException("异常");
				} else {
					String message = "端口:" + socket.getLocalPort() + "\n" +
							"IP: " + socket.getInetAddress().getHostAddress()
							+ "\n" +
							"时间:" + new SimpleDateFormat("MM月dd日 HH时mm分")
								.format(new Date())
							+ "\n\n" +
							messageAll[0] + " --> " +
							"\t" + messageAll[1] + "\n";

					client.getClientJpanel()
						.getMessagePane()
						.append(message);
				}
			} catch (MessageException e) {
				System.out.println("解析失败");
				if (socket != null) {
					socket.close();
				}
				// 中断线程
				this.interrupt();
			}

			// 接收到消息后,判断是否勾选自动回复
			if (client.getClientJpanel()
				.getBackMessageCheckBox()
				.isSelected()) {
				// 如果被选中了,但是没有编写自动回复的文本
				if (client.getClientJpanel()
					.getBackMessage()
					.getText()
					.equals("")) {
					backMessage = "朕后宫佳丽三千,有事憋着,没事退朝";
				} else {// 有预设消息时
					backMessage = client.getClientJpanel()
						.getBackMessage()
						.getText();
				}
				backMessage = "Code.Ai&" + backMessage;
				backMessage();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (socket != null) try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void backMessage() {
		String IP = socket.getInetAddress().getHostAddress();
		System.out.println(IP);
		Socket backSocket = null;
		BufferedWriter bw = null;
		try {
			backSocket = new Socket(IP,
				9527);
			bw = new BufferedWriter(
				new OutputStreamWriter(
					backSocket.getOutputStream(), "GBK"));
			// 这句是发送给服务器的 控制台输出
			bw.write(backMessage);
			bw.flush();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (backSocket != null) {
				try {
					backSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
