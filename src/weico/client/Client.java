package weico.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private ClientJpanel clientJpanel;
	private String			IP;

	public Client(ClientJpanel clientJpanel, String IP) {
		this.IP = IP;
		this.clientJpanel = clientJpanel;
		System.out.println("现在IP" + IP);
		// 追加到文本域
		String msg = "Code.Ai&";
		msg += clientJpanel.getEditorPane().getText();

		Socket socket = null;
		BufferedWriter bw = null;

		try {
			socket = new Socket(IP,
				9527);
			bw = new BufferedWriter(
				new OutputStreamWriter(socket.getOutputStream(), "GBK"));
			// 这句是发送给服务器的 控制台输出
			bw.write(msg);
			bw.flush();

		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
