package weico.test;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class quaqua extends JFrame {
	public quaqua() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 取消frame本身的框架
		setUndecorated(true);
		setFocusableWindowState(true);

		JRootPane rp = getRootPane();
		rp.setWindowDecorationStyle(JRootPane.FRAME);

		initComponent();
		pack();
		setVisible(true);
	}

	public void initComponent() {
		setLayout(new GridLayout(6, 1));
		setPreferredSize(new Dimension(200, 200));

		add(new JLabel("label style"));
		add(new JButton("Button"));
		add(new JTextArea(10, 3));
	}

	public static void main(String[] args) {
		try {
			UIManager
				.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		} catch (Exception e) {
			System.err.println("Oops!  Something went wrong!");
		}
		JFrame q = new quaqua();
	}
}