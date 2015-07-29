package weico.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import weico.client.ClientFrame;

public class Server {
	private ClientFrame clientFrame;

	public Server(ClientFrame clientFrame) {
		this.clientFrame = clientFrame;
		// 服务器
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(9527);
			System.out.println("服务器启动.....");
			while (true) {
				socket = server.accept();
				// 多线程
				new ProcessMsgThread(socket, clientFrame).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (server != null) try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
