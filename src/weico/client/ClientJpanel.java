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
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
		ipAddressList.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		ipAddressList.setBounds(10, 481, 144, 23);
		// 下拉框不可编辑
		// ipAddressList.setEditable(true);
		add(ipAddressList);
		// 返回当前所选项
		this.setIp(pro.getProperty((String) ipAddressList.getSelectedItem()));
		System.out.println("当前IP:" + getIp());
		// 添加监听器
		ipAddressList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println((String) ipAddressList.getSelectedItem());
				String ip = "所选IP:" + pro
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
			public void actionPerformed(ActionEvent e) {
				// 判断输入框是否为空
				// System.out.println(getEditorPane().getText());
				String s = "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9]).(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9]).(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9]).(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])";
				// 如果输入不为空
				if (!editorPane.getText().trim().isEmpty()) {
					// 如果IP地址为空,则默认向自己发送消息
					// if (getIp().trim().isEmpty()) {
					// ip = "127.0.0.1";
					// // ipAddressTmp = "127.0.0.1";
					// // 启动客户端
					// new Client(clientFrame.getClientJpanel());
					// }
					// // 如果不为空,且IP格式正确
					// else if (getIp().matches(s)) {
					//
					// // ipAddress = ipAddress.getText().trim();
					// ip = (String) ipAddressList.getSelectedItem();
					// // 启动客户端
					// new Client(clientFrame.getClientJpanel());
					// } else
					// JOptionPane.showMessageDialog(null, "IP地址格式错误");

					// if (getIp().matches(s) && !getIp().trim().isEmpty()) {
						new Client(clientFrame.getClientJpanel());
						// 输出后设置为空
					editorPane.setText("");
					// } else
					// JOptionPane.showMessageDialog(null, "输入的IP地址格式错误!");

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

		/*//IP地址
		ipAddress = new JTextField();
		ipAddress.setBounds(10, 481, 144, 21);
		add(ipAddress);
		ipAddress.setColumns(10);*/



		JButton btnNewButton = new JButton("清屏(L)");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

		JButton button = new JButton("消息记录");
		button.setMnemonic('C');
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button.setBounds(485, 373, 89, 23);
		add(button);



	}
}
