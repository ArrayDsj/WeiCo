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
		String messageTmp = null;
		String onceMessage = "";
		try {
			/*InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			br = new BufferedReader(isr);*/
			
			br = new BufferedReader(
				new InputStreamReader(socket.getInputStream(), "GBK"));
			// 循环读取多行信息
			while ((messageTmp = br.readLine()) != null) {
				onceMessage += messageTmp + "\n";
			}

			// 控制台输出客户端传过来的信息
			System.out.print("客户端口号:" + socket.getLocalPort() + "\n" +
					"IP地址:" + socket.getInetAddress().getHostAddress() + "\n"
					+ "他说: \n"
					+ onceMessage
					+ "\n");
			// 文本框输出客户端传过来的信息
			// 谁说的
			// 什么时间
			// IP地址
			// 端口号
			// 按协议拆分 协议 --> 姓名&说的内容

			try {
				String[] messageAll = onceMessage.split("&");
				if (messageAll.length != 2) {
					throw new MessageException("异常");
				} else {
					String message = messageAll[0] + " ("
							+ socket.getInetAddress().getHostAddress() + ") "
							+ new SimpleDateFormat("M月d日 HH:mm")
								.format(new Date())
							+ "\n"
							+ messageAll[1] + "\n";

					// 把服务端的信息输出到文本域中
					client.getClientJpanel()
						.getMessagePane()
						.append(message);

					// 如果勾选了自动回复,则调用此方法
					if (client.getClientJpanel()
						.getBackMessageCheckBox()
						.isSelected()) {
						backMessage();
					} /* else {// 服务端向客户端发送消息
							// 使用客户端传过来的socket
						OutputStreamWriter osw = new OutputStreamWriter(
							socket.getOutputStream());
						BufferedWriter bw = new BufferedWriter(osw);
						System.out.println("输入要回复给客户端的信息:\n");
						String backMessage = new Scanner(System.in).next();
						bw.write(backMessage);
						bw.flush();
						
						osw.close();
						bw.close();
						}*/
				}
			} catch (MessageException e) {
				System.out.println("解析失败");
				if (socket != null) {
					socket.close();
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 中断线程
			this.interrupt();
		}
	}

	// 自动回复方法
	public void backMessage() {
		backMessage = client.getClientJpanel()
			.getBackMessage()
			.getText();
		if(client.getClientJpanel()
				.getBackMessage()
				.getText().trim()
				.equals("")){
			backMessage = "朕后宫佳丽三千,有事憋着,没事退朝";
		}
		backMessage = "Code.Ai&" + backMessage;
		
		String IP = socket.getInetAddress().getHostAddress();
		// System.out.println(IP);

		// 防止回复给本机从而导致死循环回复
		if (!IP.equals("127.0.0.1")) {
			Socket backSocket = null;
			BufferedWriter bw = null;
			try {
				backSocket = new Socket(IP,
					9527);
				bw = new BufferedWriter(
					new OutputStreamWriter(
						backSocket.getOutputStream(), "GBK"));
				// 回复客户端
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
}
