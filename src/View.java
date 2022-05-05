import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Toolkit;

public class View extends JFrame {

	private JPanel view;

	/**
	 * Create the frame.
	 */
	public View(JTextField username, JPasswordField password) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kongp\\Desktop\\IT\\Programming\\Basic Programming\\Java\\Project\\Hospital Appointment\\GUI\\Favicon.png"));
		setTitle("Hospital Appointment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1290, 720);
		view = new JPanel();
		view.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(view);
		view.setLayout(null);
		
		createButton();
		createSideBar(username, password);
		createTable(username);
		
		JLabel imgView = new JLabel("");
		imgView.setBounds(0, 5, 1290, 720);
		imgView.setIcon(new ImageIcon("C:\\Users\\kongp\\Desktop\\IT\\Programming\\Basic Programming\\Java\\Project\\Hospital Appointment\\GUI\\View.jpg"));
		view.add(imgView);
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
		view.add(logOut);
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
		view.add(cre_sb);
		
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
		view.add(can_sb);
		
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
		view.add(view_sb);
	}
	
	private void createTable(JTextField username) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(430, 237, 758, 339);
		view.add(scrollPane);
		
		//create table for showing the appointment of a user
		JTable table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"AppID", "Date", "Time", "Username", "Phone", "Department"
			}
		));
		scrollPane.setViewportView(table);
		
		DefaultTableModel tbModel = (DefaultTableModel) table.getModel();
		
		//delete all rows in JTable
		tbModel.setRowCount(0);
		
		//create variable stored select statement
		String sqlSelect = "SELECT * FROM Appointment WHERE UseName = " + "'" + username.getText() + "'";
		
		try {
			
			ConDB db = new ConDB();
			Connection con = db.onlyConDB();
												 //run select statement
			ResultSet rs = con.createStatement().executeQuery(sqlSelect);
			
			while (rs.next()) {
				
				//these variables store data of each attribute in a row
				String appID = String.valueOf(rs.getInt("AppID"));
				String date = rs.getString("Date");
				String time = rs.getString("Time");
				String name = rs.getString("UseName");
				String phone = rs.getString("Pnumber");
				String dept = rs.getString("Dname");
				
				//create array stored those variables
				String[] tbData = {appID, date, time, name, phone, dept};
				
				//add all data in array to JTable
				tbModel.addRow(tbData);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}