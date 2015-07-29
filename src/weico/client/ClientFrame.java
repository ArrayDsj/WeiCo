package weico.client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import weico.server.Server;

public class ClientFrame extends JFrame {
	private Container contentP;
	private JTextArea	messagePane;
	private ClientJpanel	clientJpanel;

	public ClientJpanel getClientJpanel() {
		return clientJpanel;
	}

	public void setClientJpanel(ClientJpanel clientJpanel) {
		this.clientJpanel = clientJpanel;
	}

	public JTextArea getMessagePane() {
		return messagePane;
	}

	public void setMessagePane(JTextArea messagePane) {
		this.messagePane = messagePane;
	}

	public ClientFrame() {
		// 设置窗体本身的属性
		Toolkit tk = Toolkit.getDefaultToolkit();
		this.setTitle("Client");// 设置窗体标题
		this.setSize(584, 540);// 设置窗体大小
		this.setLocation(((int) tk.getScreenSize().getWidth() - 584) / 2,
			((int) tk.getScreenSize().getHeight() - 508) / 2);// 设置窗体的位置
		this.setResizable(false);// 设置窗体尺寸不可更改
		// this.setIconImage(tk.createImage("img/hp.JPG"));// 设置窗体图标
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.addContent();
		// 显示窗口
		this.setVisible(true);

		// 服务器
		new Server(this);
	}

	private void addContent() {
		// 得到Container框架层
		this.contentP = this.getContentPane();
		this.contentP.setLayout(new BorderLayout());

		clientJpanel = new ClientJpanel(this);
		messagePane = clientJpanel.getMessagePane();
		this.contentP.add(clientJpanel);
	}


}
