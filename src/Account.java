import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Account extends JFrame {

	private JPanel account;

	/**
	 * Create the frame.
	 */
	public Account() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kongp\\Desktop\\IT\\Programming\\Basic Programming\\Java\\Project\\Hospital Appointment\\GUI\\Favicon.png"));
		setTitle("Hospital Appointment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1290, 720);
		account = new JPanel();
		account.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(account);
		account.setLayout(null);
		
		createButton();
		
		JLabel imgAccount = new JLabel("");
		imgAccount.setBounds(0, 5, 1290, 720);
		imgAccount.setIcon(new ImageIcon("C:\\Users\\kongp\\Desktop\\IT\\Programming\\Basic Programming\\Java\\Project\\Hospital Appointment\\GUI\\Account.jpg"));
		account.add(imgAccount);
	}
	
	private void createButton() {
		//create sign up button
		JButton sup = new JButton("");
		sup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				SignUp sup = new SignUp();
				sup.setVisible(true);
			}
		});
		sup.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sup.setOpaque(false);
		sup.setContentAreaFilled(false);
		sup.setBorderPainted(false);
		sup.setBorder(null);
		sup.setBounds(518, 564, 245, 88);
		account.add(sup);
		
		//create sign in button
		JButton sin = new JButton("");
		sin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				SignIn sin = new SignIn();
				sin.setVisible(true);
			}
		});
		sin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sin.setOpaque(false);
		sin.setContentAreaFilled(false);
		sin.setBorderPainted(false);
		sin.setBorder(null);
		sin.setBounds(518, 414, 245, 88);
		account.add(sin);
	}
}