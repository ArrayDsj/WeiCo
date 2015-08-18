package weico.test;

import weico.client.ClientFrame;

import javax.swing.*;

public class TestMain {
	public static void main(String[] args) {
		System.setProperty("Quaqua.tabLayoutPolicy", "wrap");

		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
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
		JFrame w = new ClientFrame();
//		//w.setBackground(Color.BLACK);
		
		
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					// JFrame.setDefaultLookAndFeelDecorated(true);
//					UIManager
//						.setLookAndFeel(
//							"ch.randelshofer.quaqua.QuaquaLookAndFeel");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				JFrame w = new QuaQuaTest();
//				w.setBackground(Color.BLACK);
//				// AWTUtilities.setWindowOpacity(w, 0.9f);
//			}
//		});
	}
}

