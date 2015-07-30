package weico.client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientJpanel extends JPanel {
	private ClientFrame clientFrame;
	private JTextArea	messagePane;
	private JTextArea	editorPane;
	private JTextField	ipAddress;
	private String		ipAddressTmp;
	private String		ip;
	private JComboBox	ipAddressList;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIpAddressTmp() {
		return ipAddressTmp;
	}

	public void setIpAddressTmp(String ipAddressTmp) {
		this.ipAddressTmp = ipAddressTmp;
	}

	public JTextArea getEditorPane() {
		return editorPane;
	}

	public void setEditorPane(JTextArea editorPane) {
		this.editorPane = editorPane;
	}

	public JTextField getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(JTextField ipAddress) {
		this.ipAddress = ipAddress;
	}

	public JTextArea getMessagePane() {
		return messagePane;
	}

	public void setMessagePane(JTextArea messagePane) {
		this.messagePane = messagePane;
	}

	public ClientJpanel(ClientFrame clientFrame) {
		this.clientFrame = clientFrame;

		this.setSize(584, 508);
		this.setLayout(null);

		// 加载Properties文件
		Properties pro = new Properties();
		try {
			pro.load(new FileInputStream("J113.properties"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 将键添加到下拉列表中
		Set nameAll = pro.keySet();
		int size = nameAll.size();
		int i = 0;
		String[] studentName = new String[size];
		for (Object name : nameAll) {
			studentName[i++] = (String) name;
		}
		ipAddressList = new JComboBox(studentName);
		ipAddressList.setBounds(10, 481, 144, 21);
		add(ipAddressList);
		ipAddressList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println((String) ipAddressList.getSelectedItem());
				String ip = pro
					.getProperty((String) ipAddressList.getSelectedItem());
				System.out.println(ip);
				setIp(
					pro.getProperty((String) ipAddressList.getSelectedItem()));
			}
		});

		// 取得选中的那个键 然后得到相应的值,设置IP地址为这个值

		// 添加文本域和滚动条
		// 聊天信息
		messagePane = new JTextArea();
		messagePane.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JScrollPane jsp1 = new JScrollPane(messagePane);
		jsp1.setBounds(0, 83, 584, 288);
		this.add(jsp1);

		// 输入窗口
		editorPane = new JTextArea();
		editorPane.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JScrollPane jsp2 = new JScrollPane(editorPane);
		jsp2.setBounds(0, 399, 584, 72);
		this.add(jsp2);

		JButton sendButton = new JButton("发送(S)");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 判断输入框是否为空
				// System.out.println(getEditorPane().getText());
				String s = "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9]).(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9]).(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9]).(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])";
				// 如果输入不为空
				if (!editorPane.getText().trim().isEmpty()) {
					// 如果IP地址为空,则默认向自己发送消息
					// if (ipAddress.getText().isEmpty()) {
					// ip = "127.0.0.1";
					// //ipAddressTmp = "127.0.0.1";
					// // 启动客户端
					// new Client(clientFrame.getClientJpanel());
					// }
					// // 如果不为空,且IP格式正确
					// else if (ipAddress.getText().trim().matches(s)) {
					//
					// //ipAddress = ipAddress.getText().trim();
					// ip = (String) ipAddressList.getSelectedItem();
					// // 启动客户端
					// new Client(clientFrame.getClientJpanel());
					// } else
					// JOptionPane.showMessageDialog(null, "IP地址格式错误");

					new Client(clientFrame.getClientJpanel());
					// 输出后设置为空
					editorPane.setText("");

				} else {
					JOptionPane.showMessageDialog(null, "消息输入不能为空");
				}
			}
		});
		// 快捷键设置
		sendButton.setMnemonic('S');
		sendButton.setBounds(494, 481, 80, 23);
		add(sendButton);

		JButton offButton = new JButton("关闭(C)");
		offButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// 快捷键设置
		offButton.setMnemonic('C');
		offButton.setBounds(409, 481, 80, 23);
		add(offButton);

		/*//IP地址
		ipAddress = new JTextField();
		ipAddress.setBounds(10, 481, 144, 21);
		add(ipAddress);
		ipAddress.setColumns(10);*/



		JButton btnNewButton = new JButton("清屏(L)");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				messagePane.setText("");
			}
		});
		btnNewButton.setMnemonic('L');
		btnNewButton.setBounds(164, 481, 80, 21);
		add(btnNewButton);

	}
}
