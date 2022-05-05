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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.Cursor;

public class Cancel extends JFrame {

	private JPanel cancel;
	private JTextField appointID;
	private JPasswordField password;

	/**
	 * Create the frame.
	 */
	public Cancel(JTextField username, JPasswordField password) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kongp\\Desktop\\IT\\Programming\\Basic Programming\\Java\\Project\\Hospital Appointment\\GUI\\Favicon.png"));
		setTitle("Hospital Appointment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1290, 720);
		cancel = new JPanel();
		cancel.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(cancel);
		cancel.setLayout(null);
		
		createField();
		createButton(username, password);
		createSideBar(username, password);
		
		JLabel imgCan = new JLabel("");
		imgCan.setBounds(0, 5, 1290, 720);
		imgCan.setIcon(new ImageIcon("C:\\Users\\kongp\\Desktop\\IT\\Programming\\Basic Programming\\Java\\Project\\Hospital Appointment\\GUI\\Cancel.jpg"));
		cancel.add(imgCan);
	}
	
	private void createButton(JTextField username, JPasswordField password) {
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
		cancel.add(logOut);
		
		//create create button
		JButton btn_can = new JButton("");
		btn_can.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelAppointment(username, password);
			}
		});
		btn_can.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_can.setOpaque(false);
		btn_can.setContentAreaFilled(false);
		btn_can.setBorderPainted(false);
		btn_can.setBorder(null);
		btn_can.setBounds(576, 507, 218, 67);
		cancel.add(btn_can);
	}
	
	private void createField() {
		//create variable stored appointment id
		appointID = new JTextField();
		appointID.setBorder(null);
		appointID.setFont(new Font("Arial", Font.PLAIN, 28));
		appointID.setBounds(592, 254, 250, 31);
		cancel.add(appointID);
		appointID.setColumns(10);
		
		//create variable stored password
		password = new JPasswordField();
		password.setBorder(null);
		password.setFont(new Font("Arial", Font.PLAIN, 28));
		password.setBounds(592, 390, 250, 31);
		cancel.add(password);
	}
	
	private void createSideBar(JTextField username, JPasswordField password) {
		//create create side bar button
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
		cancel.add(cre_sb);
		
		//create side bar cancel button
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
		cancel.add(can_sb);
		
		//create side bar view button
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
		cancel.add(view_sb);
	}
	
	private void cancelAppointment(JTextField username, JPasswordField password) {
		//create variable stored password inputted and converted to String
		String pass_input = new String(password.getPassword());
		
		//create variable stored select statement
		String sqlSelect = "SELECT AppID, Appointment.UseName, PatPass FROM Patient INNER JOIN Appointment ON Patient.UseName = Appointment.UseName WHERE Appointment.AppID = " + appointID.getText() + " AND Patient.UseName = " + "'" + username.getText() + "' AND Patient.PatPass = " + "'" + pass_input + "'";
		
		try {
			
			ConDB db = new ConDB();
			Connection con = db.onlyConDB();
												 //run select statement
			ResultSet rs = con.createStatement().executeQuery(sqlSelect);
			
			if (rs.next()) {
				
				//**Correct**
					
				Statement stmt = con.createStatement();
					
				//create variable stored delete statement
				String sqlDelete = "DELETE FROM Appointment WHERE AppID = " + appointID.getText() + " AND UseName = " + "'" + username.getText() + "'";
					
				//run delete statement
				stmt.execute(sqlDelete);
					
				JOptionPane.showMessageDialog(null, "Cancel Appointment Successfully");
				
				dispose();
				
				Home hom = new Home(username, password);
				hom.setVisible(true);
				
			} else {
				//incorrect
				JOptionPane.showMessageDialog(null, "Incorrect, please try again");
				
				appointID.setText("");
				password.setText("");
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}