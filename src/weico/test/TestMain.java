package weico.test;

import javax.swing.UIManager;
import weico.client.ClientFrame;

public class TestMain {
	public static void main(String[] args) {
		System.setProperty("Quaqua.tabLayoutPolicy", "wrap");
		try {
			UIManager
				.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		
			// // Windows风格
			// String lookAndFeel =
			// "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			// UIManager.setLookAndFeel(lookAndFeel);
			//
			// // Windows Classic风格
			// String lookAndFeel =
			// "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
			// UIManager.setLookAndFeel(lookAndFeel);
		
		} catch (Exception e) {
		}
		new ClientFrame();
	}
}
