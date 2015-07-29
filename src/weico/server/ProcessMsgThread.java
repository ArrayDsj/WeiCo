package weico.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import weico.client.ClientFrame;
import weico.exception.MessageException;

public class ProcessMsgThread extends Thread {
	private Socket socket;
	private ClientFrame	client;

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
				new InputStreamReader(socket.getInputStream()));
			String msg = br.readLine();
			// 控制台输出客户端传过来的信息
			System.out.print("客户端口号:" + socket.getLocalPort() + "\n" +
							 "IP地址:"+ socket.getInetAddress()+"\n"+ "他说: " + msg + "\n");
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
							"IP: " + socket.getInetAddress() + "\n" +
							"时间:" + new SimpleDateFormat("MM月dd日 HH时mm分")
								.format(new Date())
							+ "\n" +
							messageAll[0] + "\n" +
							"\t" + messageAll[1];
					client.getClientJpanel().getMessagePane().setText(message);
				}
			}catch(MessageException e){
				System.out.println("解析失败");
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

}
