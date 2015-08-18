package weico.test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
//import com.sun.awt.AWTUtilities;

/**
 * Quaqua Test
 * 
 * @author hoodoo
 * @data 2012年12月22日
 *
 */
public class QuaQuaTest extends JFrame {
	private JButton	button;
	private JPanel	panel;

	public QuaQuaTest() {
		panel = new JPanel();
		panel.setBounds(0, 0, this.getWidth(), this.getHeight());
		panel.setBackground(Color.RED);
		button = new JButton("Click Me！");
		button.setBounds(50, 50, 100, 25);
		this.setSize(400, 400);
		this.setLocation(0, 0);
		this.setLayout(null);
		this.add(panel);
		this.add(button);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
					"My name is 'XXX', Nice to meet you!");

			}
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					// JFrame.setDefaultLookAndFeelDecorated(true);
					UIManager
						.setLookAndFeel(
							"ch.randelshofer.quaqua.QuaquaLookAndFeel");
				} catch (Exception e) {
					e.printStackTrace();
				}
				JFrame w = new QuaQuaTest();
				w.setBackground(Color.BLACK);
				// AWTUtilities.setWindowOpacity(w, 0.9f);
			}
		});

	}
}