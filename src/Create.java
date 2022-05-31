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
import javax.swing.JComboBox;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Create extends JFrame {

	private JPanel create;
	private JTextField date;
	private JTextField time;
	private JTextField phone;
	private JComboBox<String> dept;
	private Integer appID = 10000;

	/**
	 * Create the frame.
	 */
	public Create(JTextField username, JPasswordField password) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kongp\\Desktop\\IT\\Programming\\Basic Programming\\Java\\Project\\Hospital Appointment\\GUI\\Favicon.png"));
		setTitle("Hospital Appointment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1290, 720);
		create = new JPanel();
		create.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(create);
		create.setLayout(null);
		
		createField();
		createDrop();
		createButton(username, password);
		createSideBar(username, password);
		
		JLabel imgCreate = new JLabel("");
		imgCreate.setBounds(0, 5, 1290, 720);
		imgCreate.setIcon(new ImageIcon("C:\\Users\\kongp\\Desktop\\IT\\Programming\\Basic Programming\\Java\\Project\\Hospital Appointment\\GUI\\Create.jpg"));
		create.add(imgCreate);
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
		create.add(logOut);
		
		//create button
		JButton btn_cre = new JButton("");
		btn_cre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAppointment(username, password);
			}
		});
		btn_cre.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_cre.setOpaque(false);
		btn_cre.setContentAreaFilled(false);
		btn_cre.setBorderPainted(false);
		btn_cre.setBorder(null);
		btn_cre.setBounds(576, 507, 218, 67);
		create.add(btn_cre);
	}
	
	private void createField() {
		//create variable stored date
		date = new JTextField("YYYY-MM-DD");
		date.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				placeHolderDateGained();
			}
			
			public void focusLost(FocusEvent e) {
				placeHolderDateLost();
			}
		});
		date.setForeground(Color.LIGHT_GRAY);
		date.setBorder(null);
		date.setFont(new Font("Arial", Font.ITALIC, 28));
		date.setBounds(591, 257, 250, 31);
		create.add(date);
		date.setColumns(10);
		
		//create variable stored time
		time = new JTextField("HH:MM:SS");
		time.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				placeHolderTimeGained();
			}

			public void focusLost(FocusEvent e) {
				placeHolderTimeLost();
			}
		});
		time.setForeground(Color.LIGHT_GRAY);
		time.setBorder(null);
		time.setFont(new Font("Arial", Font.ITALIC, 28));
		time.setBounds(915, 257, 250, 31);
		create.add(time);
		time.setColumns(10);
		
		//create variable stored phone number
		phone = new JTextField("098xxxxxxx");
		phone.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				placeHolderPhoneGained();
			}

			public void focusLost(FocusEvent e) {
				placeHolderPhoneLost();
			}
		});
		phone.setForeground(Color.LIGHT_GRAY);
		phone.setBorder(null);
		phone.setFont(new Font("Arial", Font.PLAIN, 28));
		phone.setBounds(591, 393, 250, 31);
		create.add(phone);
		phone.setColumns(10);
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
		create.add(cre_sb);
		
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
		create.add(can_sb);
		
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
		create.add(view_sb);
	}
	
	private void createDrop() {
		//create variable stored department
		dept = new JComboBox<String>();
		dept.setBorder(null);
		dept.setFont(new Font("Arial", Font.PLAIN, 22));
		dept.setBounds(915, 393, 250, 31);
		create.add(dept);
		
		try {
			
			ConDB db = new ConDB();
			Connection con = db.onlyConDB();
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Department");

			dept.addItem("Choose...");

			while (rs.next()) {
				//create variable stored result in a record
				String deptName = rs.getString("Dname");
				
				//add the result in a record to JcomboBox
				dept.addItem(deptName);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private void placeHolderDateGained() {
		if (date.getText().equals("YYYY-MM-DD")) {
			date.setText("");
			date.setForeground(Color.BLACK);
			date.setFont(new Font("Arial", Font.PLAIN, 28));
		}
	}
	
	private void placeHolderDateLost() {
		if (date.getText().equals("")) {
			date.setForeground(Color.LIGHT_GRAY);
			date.setFont(new Font("Arial", Font.ITALIC, 28));
			date.setText("YYYY-MM-DD");
		}
	}
	
	private void placeHolderTimeGained() {
		if (time.getText().equals("HH:MM:SS")) {
			time.setText("");
			time.setForeground(Color.BLACK);
			time.setFont(new Font("Arial", Font.PLAIN, 28));
		}
	}
	
	private void placeHolderTimeLost() {
		if (time.getText().equals("")) {
			time.setForeground(Color.LIGHT_GRAY);
			time.setFont(new Font("Arial", Font.ITALIC, 28));
			time.setText("HH:MM:SS");
		}
	}
	
	private void placeHolderPhoneGained() {
		if (phone.getText().equals("098xxxxxxx")) {
			phone.setText("");
			phone.setForeground(Color.BLACK);
			phone.setFont(new Font("Arial", Font.PLAIN, 28));
		}
	}
	
	private void placeHolderPhoneLost() {
		if (phone.getText().equals("")) {
			phone.setForeground(Color.LIGHT_GRAY);
			phone.setFont(new Font("Arial", Font.ITALIC, 28));
			phone.setText("098xxxxxxx");
		}
	}
	
	private void readAndWriteFile() {
		//read data from file
		try {
			
			Scanner read = new Scanner(new File("APP_ID.txt"));
			
			if (read.hasNext()) {
				appID = read.nextInt();
				appID += 1;
			}
			
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		
		//write data to file
		try {
			
			FileWriter write = new FileWriter("APP_ID.txt");
			
			write.write(appID.toString());
			write.flush();
			
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	//This method is for when a user doesn't pick a department
	private void readAndWriteFileError() {
		//read data from file
		try {
			
			Scanner read = new Scanner(new File("APP_ID.txt"));
			
			if (read.hasNext()) {
				appID = read.nextInt();
				appID -= 1;
			}
			
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		
		//write data to file
		try {
			
			FileWriter write = new FileWriter("APP_ID.txt");
			
			write.write(appID.toString());
			write.flush();
			
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	private void createAppointment(JTextField username, JPasswordField password) {
		readAndWriteFile();
		
		try {
			
			ConDB db = new ConDB();
			Connection con = db.onlyConDB();
			
			Statement stmt = con.createStatement();
			
			//create variable stored insert statement
			String sqlInsert = "INSERT INTO Appointment VALUES (" + appID.toString() + ", '" + date.getText() + "', '" + time.getText() + "', '" + username.getText() + "', '" + phone.getText() + "', '" + dept.getSelectedItem().toString() + "')";
			
			//run the insert statement
			stmt.execute(sqlInsert);
			
			JOptionPane.showMessageDialog(null, "Create Appointment Successfully");
			
			dispose();
			
			Home hom = new Home(username, password);
			hom.setVisible(true);
			
		} catch (SQLException e1) {

			if (date.getText().equals("YYYY-MM-DD") || time.getText().equals("HH:MM:SS") || phone.getText().equals("098xxxxxxx")) {
				JOptionPane.showMessageDialog(null, "Please type all information");
			} else {
				JOptionPane.showMessageDialog(null, "Please choose the department");
			}
		}
	}
}