package weico.test;

//导入一些必须的包
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Log extends JFrame { // 设置了两个JLabel，两个JButton，一个JTextField和一个JPasswordField
	String			mima		= new String("123");
	String			zhanghu		= new String("hello");
	JButton			jbt1		= new JButton("Login");
	JButton			jbt2		= new JButton("Cancel");
	JTextField		account		= new JTextField();
	JLabel			jl1			= new JLabel("account");
	JLabel			jl2			= new JLabel("password");
	JPasswordField	password	= new JPasswordField();

	public Log() // 构造方法
	{
		super("Login Interface"); // 标题
		setBounds(400, 300, 400, 200); // 设置程序的位置左上角顶点由前两个参数控制，后两个参数是控制窗口大小
		setLayout(null); // 如果要自己精确指定组件的位置，那么setLayout设置为null即可。

		jl1.setBounds(20, 20, 100, 100); // 指定位置和大小
		add(jl1); // 将组件加到窗口
		account.setBounds(70, 55, 100, 30);
		add(account);

		jl2.setBounds(200, 20, 100, 100);
		add(jl2);

		password.setBounds(260, 55, 110, 30);
		add(password);

		jbt1.setBounds(100, 100, 100, 60);
		add(jbt1);

		jbt2.setBounds(210, 100, 100, 60);
		add(jbt2);
		setVisible(true); // 设置为可见
	}

	public static void main(String args[]) {
		System.setProperty("Quaqua.tabLayoutPolicy", "wrap");
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			UIManager
				.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		} catch (Exception e) {
		}
		new Log(); // main方法
	}
}
