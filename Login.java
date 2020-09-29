import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.jdbc.StatementImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField FN;
	private JPasswordField PS;
	protected Statement MyConnection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setLocation(650, 200);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 566, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 50));
		lblLogin.setBounds(196, 13, 151, 79);
		contentPane.add(lblLogin);

		JLabel lblUsername = new JLabel("username :");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblUsername.setBounds(26, 113, 237, 79);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("password :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblPassword.setBounds(26, 263, 237, 56);
		contentPane.add(lblPassword);

		JButton btnNewButton = new JButton("sign in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Register f5 = new Register();
				f5.setVisible(true);
				f5.setLocation(650, 200);
				f5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnNewButton.setBounds(57, 409, 167, 67);
		contentPane.add(btnNewButton);

		FN = new JTextField();
		FN.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		FN.setBounds(294, 131, 218, 67);
		contentPane.add(FN);
		FN.setColumns(10);

		JButton btnLogin = new JButton("login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Register f5 = new Register();
				String query = "SELECT * FROM `user` WHERE `user_id` =? and `user_password` =? ";
				try {

					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/test? useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
							"root", "");
					PreparedStatement ps;

					String name = FN.getText();
					String pass = String.valueOf(PS.getPassword());

					ps = con.prepareStatement(query);
					ps.setString(1, name);
					ps.setString(2, pass);
					ResultSet rs = ps.executeQuery();
					int i = 0;

					while (rs.next()) {

						i++;
					}
					if (i == 1) {
						System.out.println("Registration done successfully....");
						Train_reserve f3 = new Train_reserve();
						f3.setVisible(true);
						f3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						f3.setLocation(10, 10);
						f3.setSize(1900, 1000);

					}

					else {
						System.out.println("Registration Failed because you don't have a register....");
					}

				} catch (Exception ex) {
					Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
				}

			}

		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnLogin.setBounds(321, 409, 167, 67);
		contentPane.add(btnLogin);

		PS = new JPasswordField();
		PS.setBounds(294, 269, 218, 67);
		contentPane.add(PS);
	}

}
