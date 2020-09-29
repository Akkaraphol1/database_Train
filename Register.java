import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPasswordField;

public class Register extends JFrame {

	private static final String Boolean = null;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField FN;
	private JTextField LN;
	private JPasswordField PS;
	private JPasswordField PS2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register f5 = new Register();
					f5.setVisible(true);
					f5.setLocation(650, 200);
					f5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Register() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 567, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblRegister = new JLabel("Register");
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblRegister.setBounds(177, 26, 220, 81);
		contentPane.add(lblRegister);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblFirstName.setBounds(63, 120, 179, 50);
		contentPane.add(lblFirstName);

		FN = new JTextField();
		FN.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		FN.setBounds(377, 125, 139, 45);
		contentPane.add(FN);
		FN.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblLastName.setBounds(63, 215, 173, 45);
		contentPane.add(lblLastName);

		LN = new JTextField();
		LN.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		LN.setColumns(10);
		LN.setBounds(377, 215, 139, 45);
		contentPane.add(LN);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblPassword.setBounds(63, 297, 179, 50);
		contentPane.add(lblPassword);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/test? useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
							"root", "");
					PreparedStatement ps; // = con.prepareStatement("insert into
											// user(user_id,user_Last,user_password,user_password2)
											// values(?,?,?,?);");
					String query = "INSERT INTO `user`(`user_id`, `user_Last`, `user_password`, `user_password2`) VALUES (?,?,?,?)";
					ps = con.prepareStatement(query);

					String name = FN.getText();
					ps.setString(1, FN.getText());
					int m1 = name.length();

					String last = LN.getText();
					ps.setString(2, LN.getText());
					int m2 = last.length();

					String pass = String.valueOf(PS.getPassword());
					ps.setString(3, PS.getText());
					int m3 = pass.length();

					String pass2 = String.valueOf(PS2.getPassword());
					ps.setString(4, PS2.getText());
					int m4 = pass2.length();

					ps.setString(1, name);
					ps.setString(2, last);
					ps.setString(3, pass);
					ps.setString(4, pass2);

					// ResultSet rs = ps.executeQuery();
					if (m1 <= 3) // First Name
					{
						JOptionPane.showMessageDialog(null,
								"Please Input (First Name)\n First Name must more than four number.");
						FN.requestFocus();
						return;
					}

					if (m2 < 1) // Last Name
					{
						JOptionPane.showMessageDialog(null, "Please Input (Last Name)");
						LN.requestFocusInWindow();
						return;
					}

					if (m3 <= 3) // Password
					{
						JOptionPane.showMessageDialog(null,
								"Please Input Password \n Password must more than four number.");
						PS.requestFocusInWindow();
						return;
					} else if (m4 <= 3) // Confirm Password
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

					int x = ps.executeUpdate();
					if (x > 0) {
						System.out.println("Registration done successfully....");
						Login frame = new Login();
						frame.setVisible(true);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setLocation(650, 200);
					} else {
						System.out.println("Registration Failed...");
					}
				} catch (Exception ex) {
					Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);

				}

			}
		});
		btnNewButton.setBounds(199, 473, 166, 57);
		contentPane.add(btnNewButton);

		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblConfirmPassword.setBounds(63, 381, 287, 50);
		contentPane.add(lblConfirmPassword);

		PS = new JPasswordField();
		PS.setBounds(377, 302, 139, 45);
		PS.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		PS.setColumns(10);
		contentPane.add(PS);

		PS2 = new JPasswordField();
		PS2.setBounds(377, 384, 139, 45);
		PS2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		PS2.setColumns(10);
		contentPane.add(PS2);

	}
}
