package weico.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private ClientJpanel clientJpanel;

	public Client(ClientJpanel clientJpanel) {
		this.clientJpanel = clientJpanel;

		// 追加到文本域
		String msg = "Code.Ai&";
		msg += clientJpanel.getEditorPane().getText();

		// clientJpanel.getMessagePane()
		// .append(clientJpanel.getEditorPane().getText() + "\n");

		Socket socket = null;
		BufferedWriter bw = null;

		try {
			socket = new Socket(clientJpanel.getIpAddressTmp().trim(),
				9527);
			bw = new BufferedWriter(
				new OutputStreamWriter(
					socket.getOutputStream()));
			// 这句是发送给服务器的 控制台输出
			bw.write(msg);
			bw.flush();

		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}
}
