import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class Data_Member extends JFrame {

	private static JTextField[] T = new JTextField[10];
	private JLabel[] L = new JLabel[10];
	private JButton[] B = new JButton[7];
	private Object[] columns = {"Date","First Name", "Last Name", "Password", "Confirm Password","Gender","Age","Telephone"};
    private DefaultTableModel listModel = new DefaultTableModel();
    private static JTable table = new JTable();
    private 	JScrollPane pane = new JScrollPane();
  
    private JTextField Time;
    private JPasswordField PS;
	private JPasswordField PS2;

    
	public Data_Member(String Member)
	{
		getContentPane().setLayout(null);
		

		//setting from time in window 
	       JLabel currentTime = new JLabel("Current Time : ");
	       currentTime.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
	   	 currentTime.setBounds(50, 50, 287, 50);
	   	 getContentPane().add(currentTime);
	   	 
	   	 Time = new JTextField();
	   	Time.setFont(new Font("Tohama",Font.BOLD,20));
	    Time.setEditable(false);
	   	Time.setBounds(320, 50, 200, 45);
	   	getContentPane().add(Time);
	 
	   	 Calendar c = Calendar.getInstance();
	   	 SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	   	String currentDate = df.format(c.getTime());
	   	Time.setText(currentDate);

		//FN
		L[0] = new JLabel("First Name");
		L[0].setBounds(50,200,200,50);
		L[0].setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(L[0]);
		
		T[0] = new JTextField();
		T[0].setBounds(160,200,120,50);
		T[0].setFont(new Font("Tahoma", Font.BOLD, 20));
		getContentPane().add(T[0]);
	   	
		//LN
		L[1] = new JLabel("Last Name");
		L[1].setBounds(300,200,200,50);
		L[1].setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(L[1]);
				
		T[1] = new JTextField();
		T[1].setBounds(410,200,120,50);
		T[1].setFont(new Font("Tahoma", Font.BOLD, 20));
		getContentPane().add(T[1]);
		
		//password
		L[2] = new JLabel("password");
		L[2].setBounds(550,200,200,50);
		L[2].setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(L[2]);
				
		PS = new JPasswordField();
		PS.setBounds(660,200,120,50);
		PS.setFont(new Font("Tahoma", Font.BOLD, 20));
		getContentPane().add(PS);
		
		//Confirm Password
		L[3] = new JLabel("confirm password");
		L[3].setBounds(800,200,200,50);
		L[3].setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(L[3]);
						
		PS2 = new JPasswordField();
		PS2.setBounds(1000,200,120,50);
		PS2.setFont(new Font("Tahoma", Font.BOLD, 20));
		getContentPane().add(PS2);
		
		//Gender
		L[4] = new JLabel("Gender");
		L[4].setBounds(1140,200,200,50);
		L[4].setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(L[4]);
						
		T[2] = new JTextField();
		T[2].setBounds(1250,200,120,50);
		T[2].setFont(new Font("Tahoma", Font.BOLD, 20));
		getContentPane().add(T[2]);
		
		//Age
		L[5] = new JLabel("Age");
		L[5].setBounds(1390,200,200,50);
		L[5].setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(L[5]);
		
		T[3] = new JTextField();
		T[3].setBounds(1470,200,120,50);
		T[3].setFont(new Font("Tahoma", Font.BOLD, 20));
		getContentPane().add(T[3]);
	
		//Age
		L[6] = new JLabel("Telephone");
		L[6].setBounds(1600,200,200,50);
		L[6].setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(L[6]);
		
		T[4] = new JTextField();
		T[4].setBounds(1720,200,120,50);
		T[4].setFont(new Font("Tahoma", Font.BOLD, 20));
		getContentPane().add(T[4]);

		// Go
				B[2] = new JButton("Go");
				B[2].setBounds(1200,800,100,50);
				B[2].setFont(new Font("Tahoma", Font.BOLD, 20));
				B[2].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						 Trainthree T = new Trainthree("Welcome to Trainstaion");
						   T.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						   T.setSize(1520,1000);
						   T.setVisible(true);
						   T.setLocation(300, 10);
						 

					}
				});

				getContentPane().add(B[2]);
				
		
		// ADD
		B[0] = new JButton("Add");
		B[0].setBounds(900,800,100,50);
		B[0].setFont(new Font("Tahoma", Font.BOLD, 20));
		B[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/trainstation2? useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
							"root", "");
					PreparedStatement ps; // = con.prepareStatement("insert into
											// user(user_id,user_Last,user_password,user_password2)
											// values(?,?,?,?);");
					String query = "INSERT INTO `register_1`(`Date`,`First Name`, `Last Name`, `Password`, `Confirm Password`, `Gender`, `Age`, `Telephone`) VALUES (?,?,?,?,?,?,?,?)";
					ps = con.prepareStatement(query);


					String currentDate = Time.getText();
					ps.setString(1, Time.getText());
				  	int TT = currentDate.length();
				  	

					String name = T[0].getText();
					ps.setString(2, T[0].getText());
					int m2 = name.length();
					
					String last = T[1].getText();
					ps.setString(3, T[1].getText());
					int m3 = last.length();

					String pass = String.valueOf(PS.getPassword());
					ps.setString(4, PS.getText());
					int m4 = pass.length();
					
					String pass2 = String.valueOf(PS2.getPassword());
					ps.setString(5, PS2.getText());
					int m5 = pass2.length();
	          
					////gen
					String gen1 = T[2].getText();
					ps.setString(6, T[2].getText());
					int m6 = gen1.length();

					String Age = T[3].getText();
					ps.setString(7, T[3].getText());
					int m7 = Age.length();

					String Tel = T[4].getText();
					ps.setString(8, T[4].getText());
					int m8 = Tel.length();

				
					ps.setString(1,currentDate);
					ps.setString(2, name);
					ps.setString(3, last);
					ps.setString(4, pass);
					ps.setString(5, pass2);
					ps.setString(6, gen1);
					ps.setString(7, Age);
					ps.setString(8, Tel);
					
					if(TT <= 4)
					{
						JOptionPane.showMessageDialog(null,
								"Please Input Time");
						Time.requestFocus();
						return;
						
					}
					// ResultSet rs = ps.executeQuery();
					if (m2 <= 3) // First Name
					{
						JOptionPane.showMessageDialog(null,
								"Please Input (First Name)\n First Name must more than four number.");
						T[0].requestFocus();
						return;
					}

					if (m3 < 1) // Last Name
					{
						JOptionPane.showMessageDialog(null, "Please Input (Last Name)");
						T[1].requestFocusInWindow();
						return;
					}

					if (m4 <= 3) // Password
					{
						JOptionPane.showMessageDialog(null,
								"Please Input Password \n Password must more than four number.");
						PS.requestFocusInWindow();
						return;
					} else if (m5 <= 3) // Confirm Password
					{
						JOptionPane.showMessageDialog(null,
								"Please Input (Confirm Password)\n Confirmpassword must equal to password.");
						PS2.requestFocusInWindow();
						return;
					}

					if (!pass.equals(pass2)) // Password math
					{
						JOptionPane.showMessageDialog(null, "Please Input (Password Not Match!)");
						PS.requestFocusInWindow();
						return;
					}
					
					
					if (m6 <= 1) // Gender
					{
						JOptionPane.showMessageDialog(null,
								"Please Input (Gender)\n Male or Female");
						T[2].requestFocus();
						return;
					}
                   

					if (m7 <= 0 || m7 >= 3  ) // Age
					{
						JOptionPane.showMessageDialog(null,
								"Please Input Age!!!");
						T[3].requestFocus();
						return;
					}

					if (m8 <= 9 || m6 >= 11) // telephone
					{
						JOptionPane.showMessageDialog(null,
								"Please Input (number phone) You must have a only ten digits!!!  ");
						T[4].requestFocusInWindow();
						return;
					}

					int x = ps.executeUpdate();
					if (x > 0) {
						System.out.println("Registration Successfully...");
					} else {
						System.out.println("Registration Failed...");
					}
				} catch (Exception ex) {
					Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);

				}
				

			}
		});


		getContentPane().add(B[0]);
		
		B[3] = new JButton("Refresh");
		B[3].setBounds(1500,800,100,50);
		B[3].setFont(new Font("Tahoma", Font.BOLD, 15));
		B[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Data_Member M = new Data_Member("Member");
			        M.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        M.setVisible(true);
			        M.setSize(1900,1000);
			 	   M.setLocation(10, 10);
			}
		});

		getContentPane().add(B[3]);

		table = new JTable();
		  table.setBackground(Color.WHITE);
	        table.setForeground(Color.black);
	        Font font = new Font("Tohama",Font.BOLD,15);
	        table.setFont(font);
	        table.setRowHeight(40);
	        
	        table.addMouseListener(new MouseAdapter() {
				   @Override
				   public void mouseClicked(MouseEvent arg0) {
				    int row = table.getSelectedRow();
				    if (row != -1) {
				     //TT[0].setText(myJTable.getValueAt(row, 0).toString());
				     T[0].setText(table.getValueAt(row, 2).toString());
				     T[1].setText(table.getValueAt(row, 3).toString());
				     PS.setText(table.getValueAt(row, 4).toString());
				     PS2.setText(table.getValueAt(row, 5).toString());
				     T[2].setText(table.getValueAt(row, 6).toString());
				     T[3].setText(table.getValueAt(row, 7).toString());
				     T[4].setText(table.getValueAt(row, 8).toString());

				    
				    }
				   }
				  });
	        
	        
		
		JScrollPane pane = new JScrollPane(table);
        pane.setBounds(250, 300, 1400, 450);
        
        getContentPane().add(pane);
		
		pane.setViewportView(table);
		
		
		
		
		//Delete
		B[1] = new JButton("Delete");
		B[1].setBounds(600,800,100,50);
		B[1].setFont(new Font("Tahoma", Font.BOLD, 20));
		B[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object[] options = { "Yes", "No" };
				int n = JOptionPane
						.showOptionDialog(null, "Do you want to Delete data?",
								"Confirm to Delete?",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[1]);
				if (n == 0) // Confirm Delete = Yes
				{

					for (int i = 0; i < table.getRowCount(); i++) {
						Boolean chkDel = Boolean.valueOf(table.getValueAt(i, 0).toString()); // Checked
						if(chkDel) // Checked to Delete
						{
							String strDate= table.getValueAt(i, 1)
									.toString(); // get CustomerID
							
							DeleteData(strDate); // Delete Data
						}
					}
					
					JOptionPane.showMessageDialog(null, "Delete Data Successfully");

					PopulateData(); // Reload Table
				}

			}
		});

		getContentPane().add(B[1]);

		PopulateData();
	}
	
	


private static void PopulateData() {
	// Clear table
			table.setModel(new DefaultTableModel());

			// Model for Table
			DefaultTableModel model = new DefaultTableModel() {

				public Class<?> getColumnClass(int column) {
					switch (column) {
					case 0:
						return Boolean.class;
					case 1:
						return String.class;
					case 2:
						return String.class;
					case 3:
						return String.class;
					case 4:
						return String.class;
					case 5:
						return String.class;
					case 6:
						return String.class;
					case 7:
						return String.class;
					case 8:
						return String.class;
					case 9:
						return String.class;
					case 10:
						return String.class;
				
					default:
						return String.class;
					}
				}
			};
			table.setModel(model);

// Add Column
model.addColumn("Select");
model.addColumn("Date");
model.addColumn("First Name");
model.addColumn("Last Name");
model.addColumn("Password");
model.addColumn("Confirm Password");
model.addColumn("Gender");
model.addColumn("Age");
model.addColumn("Telephone");

Connection connect = null;
Statement s = null;

try {


	Class.forName("com.mysql.cj.jdbc.Driver");
	connect = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/trainstation2? useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
			"root", "");
	

	s = connect.createStatement();

	String sql = "SELECT * FROM  register_1 ORDER BY Date ASC";

	ResultSet rec = s.executeQuery(sql);
	int row = 0;
	while ((rec != null) && (rec.next())) {
		model.addRow(new Object[0]);
		model.setValueAt(false, row, 0); // Checkbox
		model.setValueAt(rec.getString("Date"), row, 1);
		model.setValueAt(rec.getString("First Name"), row, 2);
		model.setValueAt(rec.getString("Last Name"), row, 3);
		model.setValueAt(rec.getString("Password"), row, 4);
		model.setValueAt(rec.getString("Confirm Password"), row, 5);
		model.setValueAt(rec.getString("Gender"), row, 6);
		model.setValueAt(rec.getString("Age"), row, 7);
		model.setValueAt(rec.getString("Telephone"), row, 8);
	
		row++;
	}

} catch (Exception e) {
	// TODO Auto-generated catch block
	JOptionPane.showMessageDialog(null, e.getMessage());
	e.printStackTrace();
}

try {
	if (s != null) {
		s.close();
		connect.close();
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


}



// Delete
private void DeleteData(String strDate) {

Connection connect = null;
Statement s = null;

try {

	Class.forName("com.mysql.cj.jdbc.Driver");
	connect = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/trainstation2? useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
			"root", "");
	
	s = connect.createStatement();

	String sql = "DELETE FROM register_1 WHERE " + "Date = '"
			+ strDate + "' ";
	s.execute(sql);

} catch (Exception e) {
	// TODO Auto-generated catch block
	JOptionPane.showMessageDialog(null, e.getMessage());
	e.printStackTrace();
}

try {
	if (s != null) {
		s.close();
		connect.close();
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	System.out.println(e.getMessage());
	e.printStackTrace();
}
		getContentPane().add(B[1]);
			
}

		



	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				 Data_Member M = new Data_Member("Member");
			        M.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        M.setVisible(true);
			        M.setSize(1900,1000);
			 	   M.setLocation(10, 10);
			}
		});
	}
}
