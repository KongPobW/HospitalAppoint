import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignIn extends JFrame {

	private JPanel signIn;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Create the frame.
	 */
	public SignIn() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kongp\\Desktop\\IT\\Programming\\Basic Programming\\Java\\Project\\Hospital Appointment\\GUI\\Favicon.png"));
		setTitle("Hospital Appointment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1290, 720);
		signIn = new JPanel();
		signIn.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(signIn);
		signIn.setLayout(null);
		
		createButton();
		createField();
		
		JLabel imgSignIn = new JLabel("");
		imgSignIn.setBounds(0, 5, 1290, 720);
		imgSignIn.setIcon(new ImageIcon("C:\\Users\\kongp\\Desktop\\IT\\Programming\\Basic Programming\\Java\\Project\\Hospital Appointment\\GUI\\Sign In.jpg"));
		signIn.add(imgSignIn);
	}
	
	private void createButton() {
		//create sign in button
		JButton sin = new JButton("");
		sin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkSignIn();
			}
		});
		sin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sin.setOpaque(false);
		sin.setContentAreaFilled(false);
		sin.setBorderPainted(false);
		sin.setBorder(null);
		sin.setBounds(534, 592, 214, 67);
		signIn.add(sin);
		
		//create back button
		JButton back = new JButton("");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				Account acc = new Account();
				acc.setVisible(true);
			}
		});
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setBorder(null);
		back.setBounds(-7, 5, 92, 91);
		signIn.add(back);
	}
	
	private void createField() {
		//create variable stored password
		password = new JPasswordField();
		password.setBorder(null);
		password.setFont(new Font("Arial", Font.PLAIN, 28));
		password.setBounds(698, 491, 245, 31);
		signIn.add(password);
		
		//create variable stored username
		username = new JTextField();
		username.setBorder(null);
		username.setFont(new Font("Arial", Font.PLAIN, 28));
		username.setBounds(344, 491, 245, 31);
		signIn.add(username);
		username.setColumns(10);
	}
	
	private void checkSignIn() {
		//create variable stored password inputted and converted to String
		String pass_input = new String(password.getPassword());
		
		//create variable stored select statement
		String sqlSelect = "SELECT UseName, PatPass FROM Patient WHERE UseName = " + "'" + username.getText() + "'" + " AND PatPass = " + "'" + pass_input + "'";
		
		try {
			
			ConDB db = new ConDB();
			Connection con = db.onlyConDB();
			                                     //run select statement
			ResultSet rs = con.createStatement().executeQuery(sqlSelect);
			
			if (rs.next()) {

				//**correct**

				//create reminded block
				JOptionPane.showMessageDialog(null, "Sign In Successfully");

				dispose();

				Home hom = new Home(username, password);
				hom.setVisible(true);

			} else if (username.getText().equals("") || pass_input.equals("")) {
				JOptionPane.showMessageDialog(null, "Please type all information");
			} else {
				//incorrect
				JOptionPane.showMessageDialog(null, "Incorrect, please try again");
				
				username.setText("");
				password.setText("");
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}