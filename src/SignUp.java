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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.Cursor;

public class SignUp extends JFrame {

	private JPanel signUp;
	private JTextField username;
	private JTextField name;
	private JPasswordField password;

	/**
	 * Create the frame.
	 */
	public SignUp() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kongp\\Desktop\\IT\\Programming\\Basic Programming\\Java\\Project\\Hospital Appointment\\GUI\\Favicon.png"));
		setTitle("Hospital Appointment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1290, 720);
		signUp = new JPanel();
		signUp.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(signUp);
		signUp.setLayout(null);
		
		createButton();
		createField();
		
		JLabel imgSignUp = new JLabel("");
		imgSignUp.setBounds(0, 5, 1290, 720);
		imgSignUp.setIcon(new ImageIcon("C:\\Users\\kongp\\Desktop\\IT\\Programming\\Basic Programming\\Java\\Project\\Hospital Appointment\\GUI\\Sign Up.jpg"));
		signUp.add(imgSignUp);
	}
	
	private void createButton() {
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
		back.setBounds(1, 5, 92, 91);
		signUp.add(back);
		
		//create sign up button
		JButton sup = new JButton("");
		sup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAccount();
			}
		});
		sup.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sup.setOpaque(false);
		sup.setContentAreaFilled(false);
		sup.setBorderPainted(false);
		sup.setBorder(null);
		sup.setBounds(534, 592, 214, 67);
		signUp.add(sup);
	}
	
	private void createField() {
		//create variable stored username
		username = new JTextField();
		username.setBorder(null);
		username.setFont(new Font("Arial", Font.PLAIN, 28));
		username.setBounds(214, 494, 250, 31);
		signUp.add(username);
		username.setColumns(10);
		
		//create variable stored first name
		name = new JTextField();
		name.setBorder(null);
		name.setFont(new Font("Arial", Font.PLAIN, 28));
		name.setBounds(820, 494, 250, 31);
		signUp.add(name);
		name.setColumns(10);
		
		//create variable stored password
		password = new JPasswordField();
		password.setBorder(null);
		password.setFont(new Font("Arial", Font.PLAIN, 28));
		password.setBounds(516, 494, 250, 31);
		signUp.add(password);
	}
	
	private void createAccount() {
		//create variable stored password inputted and converted to String
		String pass_input = new String(password.getPassword());
		
		try {
			
			ConDB db = new ConDB();
			Connection con = db.onlyConDB();
			
			Statement stmt = con.createStatement();

			if (!username.getText().equals("") && !name.getText().equals("") && !pass_input.equals("")) {

				//create variable stored insert statement
				String sqlInsert = "INSERT INTO Patient (`UseName`, `PatPass`, `PatName`) VALUES ('" + username.getText() + "', '" + pass_input + "', '" + name.getText() + "')";

				//run insert statement
				stmt.execute(sqlInsert);

				JOptionPane.showMessageDialog(null, "Sign Up Successfully");

				dispose();

				Account acc = new Account();
				acc.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "Please type all information");
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}