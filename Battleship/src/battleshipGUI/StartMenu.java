package battleshipGUI;

/**
 * Hauptmenü des Spiels
 *
 * @author oliver2
 */
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import battleship.ConsoleMode;
import battleship.GuiMode;

public class StartMenu extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public ConsoleMode 	cm;
	public GuiMode 		gm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Set Look and Feel
				try
				{
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (Exception e)
				{
					e.printStackTrace();
				}

				try {
					StartMenu frame = new StartMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartMenu() {
		setTitle("Hauptmen\u00FC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 200);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);





		JButton btnGui = new JButton("Grafisch");
		btnGui.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gm = new GuiMode();
				gm.run();
			}
		});

		JButton btnConsole = new JButton("Konsole");
		btnConsole.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				cm= new ConsoleMode();
				cm.run();
			}
		});

		JButton btnEnd = new JButton("Ende");
		btnEnd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addComponent(btnGui)
					.addGap(18)
					.addComponent(btnConsole)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEnd, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(82, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(75)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGui)
						.addComponent(btnConsole)
						.addComponent(btnEnd))
					.addContainerGap(149, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
