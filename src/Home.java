import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Color;

public class Home extends JFrame {

	private JPanel home;

	/**
	 * Create the frame.
	 */
	public Home(JTextField username, JPasswordField password) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kongp\\Desktop\\IT\\Programming\\Basic Programming\\Java\\Project\\Hospital Appointment\\GUI\\Favicon.png"));
		setTitle("Hospital Appointment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1290, 720);
		home = new JPanel();
		home.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(home);
		home.setLayout(null);
		
		createButton();
		createSideBar(username, password);
		
		//create variable stored username showed behind welcome
		JLabel welcome = new JLabel(username.getText());
		welcome.setForeground(Color.WHITE);
		welcome.setFont(new Font("Arial", Font.PLAIN, 43));
		welcome.setBounds(849, 73, 321, 43);
		home.add(welcome);
		
		JLabel imgHome = new JLabel("");
		imgHome.setBounds(0, 5, 1290, 720);
		imgHome.setIcon(new ImageIcon("C:\\Users\\kongp\\Desktop\\IT\\Programming\\Basic Programming\\Java\\Project\\Hospital Appointment\\GUI\\Home.jpg"));
		home.add(imgHome);
	}
	
	private void createButton() {
		//create log out button
		JButton logOut = new JButton("");
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//create confirmed block
				int confirmLogOut = JOptionPane.showConfirmDialog(null, "Do you confirm to log out?", "Select an Option...", JOptionPane.YES_NO_OPTION);
				
				//yes = 0, no = 1, cancel = 2
				if (confirmLogOut == 0) {
					dispose();
				
					Account acc = new Account();
					acc.setVisible(true);
				}
			}
		});
		logOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
		logOut.setOpaque(false);
		logOut.setContentAreaFilled(false);
		logOut.setBorderPainted(false);
		logOut.setBorder(null);
		logOut.setBounds(1196, 5, 85, 86);
		home.add(logOut);
	}
	
	private void createSideBar(JTextField username, JPasswordField password) {
		//create "create" sidebar button
		JButton cre_sb = new JButton("");
		cre_sb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				Create cre = new Create(username, password);
				cre.setVisible(true);
			}
		});
		cre_sb.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cre_sb.setOpaque(false);
		cre_sb.setContentAreaFilled(false);
		cre_sb.setBorderPainted(false);
		cre_sb.setBorder(null);
		cre_sb.setBounds(1, 240, 344, 58);
		home.add(cre_sb);
		
		//create sidebar cancel button
		JButton can_sb = new JButton("");
		can_sb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				Cancel can = new Cancel(username, password);
				can.setVisible(true);
			}
		});
		can_sb.setCursor(new Cursor(Cursor.HAND_CURSOR));
		can_sb.setOpaque(false);
		can_sb.setContentAreaFilled(false);
		can_sb.setBorderPainted(false);
		can_sb.setBorder(null);
		can_sb.setBounds(1, 376, 344, 58);
		home.add(can_sb);
		
		//create sidebar view button
		JButton view_sb = new JButton("");
		view_sb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				View viw = new View(username, password);
				viw.setVisible(true);
			}
		});
		view_sb.setCursor(new Cursor(Cursor.HAND_CURSOR));
		view_sb.setOpaque(false);
		view_sb.setContentAreaFilled(false);
		view_sb.setBorderPainted(false);
		view_sb.setBorder(null);
		view_sb.setBounds(3, 509, 344, 58);
		home.add(view_sb);
	}
}