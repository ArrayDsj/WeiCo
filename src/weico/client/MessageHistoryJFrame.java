package weico.client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MessageHistoryJFrame extends JFrame {
	private JTextArea	messageHistoryPane;

	public JTextArea getMessageHistoryPane() {
		return messageHistoryPane;
	}

	public void setMessageHistoryPane(JTextArea messageHistoryPane) {
		this.messageHistoryPane = messageHistoryPane;
	}

	private Container contentP;

	public MessageHistoryJFrame() {
		// 设置窗体本身的属性
		Toolkit tk = Toolkit.getDefaultToolkit();
		this.setTitle("消息记录");// 设置窗体标题
		this.setSize(300, 500);// 设置窗体大小
		this.setLocation(((int) tk.getScreenSize().getWidth() - 584) / 2,
			((int) tk.getScreenSize().getHeight() - 508) / 2);// 设置窗体的位置
		this.setResizable(false);// 设置窗体尺寸不可更改
		// this.setIconImage(tk.createImage("img/hp.JPG"));// 设置窗体图标
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.addContent();
		// 显示窗口
		this.setVisible(true);

	}

	public void addContent() {
		// 得到Container框架层
		this.contentP = this.getContentPane();
		this.contentP.setLayout(new BorderLayout());

		// 添加消息面板
		JPanel messageHistoryJpanel = new JPanel();
		messageHistoryJpanel.setSize(300, 500);
		messageHistoryJpanel.setLayout(null);
		contentP.add(messageHistoryJpanel);

		// 添加文本域和滚动条
		messageHistoryPane = new JTextArea();
		messageHistoryPane.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		messageHistoryPane.setEditable(false);
		MessageHistoryOperation.getInstance().read(this);
		JScrollPane jsp = new JScrollPane(messageHistoryPane);
		jsp.setBounds(0, 0, 295, 440);
		messageHistoryJpanel.add(jsp);

		JButton deletButton = new JButton("删除记录");
		deletButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = JOptionPane.showConfirmDialog(null,
					"是否确认删除记录?",
					"删除记录",
					JOptionPane.YES_NO_OPTION);
				if (select == 0) {
					File file = new File("D:/History.data");
					if (file.delete()) {
						messageHistoryPane.setText("");
						JOptionPane.showMessageDialog(null, "删除成功");
					}
				}

			}
		});
		deletButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		deletButton.setBounds(10, 445, 91, 23);
		messageHistoryJpanel.add(deletButton);

		JButton checkButton = new JButton("查找");
		checkButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		checkButton.setBounds(191, 445, 93, 23);
		messageHistoryJpanel.add(checkButton);

	}
}
