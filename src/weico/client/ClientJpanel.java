package weico.client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientJpanel extends JPanel {
	private ClientFrame clientFrame;
	private JTextArea	messagePane;
	private JTextArea	editorPane;
	// private String ipAddressTmp;
	private String		ip;
	private JComboBox	ipAddressList;

	// 自动消息回复
	private JTextField	backMessage;
	// 复选框
	private JCheckBox	backMessageCheckBox;

	public JTextField getBackMessage() {
		return backMessage;
	}

	public void setBackMessage(JTextField backMessage) {
		this.backMessage = backMessage;
	}

	public JCheckBox getBackMessageCheckBox() {
		return backMessageCheckBox;
	}

	public void setBackMessageCheckBox(JCheckBox backMessageCheckBox) {
		this.backMessageCheckBox = backMessageCheckBox;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public JTextArea getEditorPane() {
		return editorPane;
	}

	public void setEditorPane(JTextArea editorPane) {
		this.editorPane = editorPane;
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
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Object[] studentName = pro.keySet().toArray();
		ipAddressList = new JComboBox(studentName);
		ipAddressList.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		ipAddressList.setBounds(10, 481, 144, 23);
		// 下拉框可编辑
		ipAddressList.setEditable(true);
		add(ipAddressList);

		// 返回当前所选项
		this.setIp(pro.getProperty((String) ipAddressList.getSelectedItem()));
		System.out.println("当前IP:" + getIp());

		// 添加监听器
		ipAddressList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setIp(
					pro.getProperty(ipAddressList.getSelectedItem().toString()));
			}
		});

		// 添加文本域和滚动条
		messagePane = new JTextArea();
		messagePane.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		messagePane.setEditable(false);
		JScrollPane jsp1 = new JScrollPane(messagePane);
		jsp1.setBounds(0, 83, 578, 288);
		this.add(jsp1);

		// 输入窗口
		editorPane = new JTextArea();
		editorPane.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JScrollPane jsp2 = new JScrollPane(editorPane);
		jsp2.setBounds(0, 399, 578, 72);
		this.add(jsp2);

		JButton sendButton = new JButton("发送(S)");
		sendButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		sendButton.addActionListener(new ActionListener() {
			// 1、取得发送框的输入的内容，如果为空不发送，给出弹出框提示
			// 2、取得IP地址框中的IP地址，如果为空，默认为127.0.0.1；进行正则表达式验证
			// 3、构造自定义协议格式的发送信息(名字&信息)
			// 4、发送
			// 5、把自己的发送信息回显在自己的文本域(我说：信息)
			public void actionPerformed(ActionEvent e) {
				String s = "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])."
						+ "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])."
						+ "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])."
						+ "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])";
				// 取得输入框字符串
				String input = editorPane.getText();
				// 取得下拉列表当前所选中的字符串
				String inputIP = ipAddressList.getSelectedItem().toString();
				if (input == null || input.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "不能发送空消息");
				} else {
					// 当输入的IP为空时,默认IP为本地
					if (inputIP == null || inputIP.equals("")) {
						setIp("127.0.0.1");
						new Client(clientFrame.getClientJpanel(), getIp());
					}
					// 当输入满足IP地址格式时
					else if (inputIP.matches(s)) {
						setIp(inputIP);
						new Client(clientFrame.getClientJpanel(), getIp());
					} else if (pro.getProperty(inputIP) != null) {// 说明在列表中有这个人
						setIp(pro.getProperty(inputIP));
						new Client(clientFrame.getClientJpanel(), getIp());
					} else {
						JOptionPane.showMessageDialog(null,
							"友情提示&请输入有效的IP地址或在好友列表中选择好友");
						ipAddressList.setSelectedIndex(0);
					}
				}
				editorPane.setText("");
			}
		});
		// 快捷键设置
		sendButton.setMnemonic('S');
		sendButton.setBounds(494, 481, 80, 23);
		add(sendButton);

		JButton offButton = new JButton("关闭(C)");
		offButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		offButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// 快捷键设置
		offButton.setMnemonic('C');
		offButton.setBounds(409, 481, 80, 23);
		add(offButton);

		// 清屏的时候向文件中写入消息记录
		JButton btnNewButton = new JButton("清屏(L)");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageHistoryOperation.getInstance()
					.write(clientFrame.getClientJpanel());
				messagePane.setText("");
			}
		});
		btnNewButton.setMnemonic('L');
		btnNewButton.setBounds(164, 481, 80, 23);
		add(btnNewButton);

		// 添加自动消息回复
		backMessage = new JTextField();
		backMessage.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		backMessage.setBounds(121, 4, 295, 34);
		backMessage.setText("自动回复");
		// backMessage.setEditable(false);
		backMessage.setEnabled(false);
		add(backMessage);
		backMessage.setColumns(10);

		backMessageCheckBox = new JCheckBox("自动回复");
		backMessageCheckBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		// 默认不选中
		backMessageCheckBox.setSelected(false);
		backMessageCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// 如果复选框被选中
				if (backMessageCheckBox.isSelected()) {
					backMessage.setEnabled(true);
					// backMessage.setEditable(true);
					sendButton.setEnabled(false);
				} else {
					// backMessage.setEditable(false);
					backMessage.setEnabled(false);
					sendButton.setEnabled(true);
				}
			}
		});
		backMessageCheckBox.setBounds(0, 6, 104, 29);
		add(backMessageCheckBox);

		JButton HistoryButton = new JButton("消息记录");
		HistoryButton.setMnemonic('C');
		HistoryButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		HistoryButton.setBounds(485, 373, 89, 23);
		HistoryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 写入信息到文件中

				// 自定义对话框
				JDialog jdlg = new JDialog(clientFrame, "son", true);
				jdlg.setLocation(400, 400);
				jdlg.setSize(200, 200);
				jdlg.setVisible(true);

				MessageHistoryOperation.getInstance()
					.write(clientFrame.getClientJpanel());
				new MessageHistoryJFrame();
				// 设置主窗体不可用

			}
		});
		add(HistoryButton);
	}
}
