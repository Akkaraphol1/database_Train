package Great;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//import Connect.DBConnection;

public class Manager_Login {

 public JFrame frame;
 private JTextField txtID;
 private JTextField txtUsername;
 private JTable table;
 private JPasswordField txtPassword;

 private Connection conn;
 private PreparedStatement stmt;
 private ResultSet rs;
 private JTextField txtSearch;

 /**
  * Launch the application.
  */
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     Manager_Login window = new Manager_Login();
     window.frame.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }

 /**
  * Create the application.
  */

 public Manager_Login() {
  initialize();
  try {
   //conn = DBConnection.getConnection();
  } catch (Exception e) {
   // TODO: handle exception
  }
  showTable();
  this.frame.setResizable(false);
 }

 public void showTable() {
  Vector cols = new Vector();
  cols.addElement("ID");
  cols.addElement("Username");
  cols.addElement("Password");
  cols.addElement("Roles");

  // Data
  Vector data = new Vector();
  String sql = "SELECT * FROM manager.login";
  try {
   stmt = conn.prepareStatement(sql);
   rs = stmt.executeQuery();
   while (rs.next()) {
    Vector login = new Vector();
    login.addElement(rs.getString("id"));
    login.addElement(rs.getString("user"));
    login.addElement(rs.getString("pass"));
    login.addElement(rs.getString("roles"));

    data.add(login);
   }
  } catch (Exception e) {
   // TODO: handle exception
  }
  table.setModel(new DefaultTableModel(data, cols));

 }

 /**
  * Initialize the contents of the frame.
  */
 private void initialize() {

  frame = new JFrame();
  frame.setBounds(100, 100, 404, 428);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.getContentPane().setLayout(null);

  txtID = new JTextField();
  txtID.setBounds(123, 54, 86, 20);
  frame.getContentPane().add(txtID);
  txtID.setColumns(10);

  txtUsername = new JTextField();
  txtUsername.setBounds(123, 85, 86, 20);
  frame.getContentPane().add(txtUsername);
  txtUsername.setColumns(10);

  txtPassword = new JPasswordField();
  txtPassword.setBounds(123, 112, 86, 20);
  frame.getContentPane().add(txtPassword);

  JComboBox comboBox = new JComboBox();
  comboBox.setModel(new DefaultComboBoxModel(new String[] { "Admin", "Acountant", "Manager", "Saler" }));
  comboBox.setBounds(123, 143, 86, 20);
  frame.getContentPane().add(comboBox);

  JLabel lblId = new JLabel("ID:");
  lblId.setBounds(36, 57, 61, 14);
  frame.getContentPane().add(lblId);

  JLabel lblUsername = new JLabel("Username:");
  lblUsername.setBounds(36, 88, 77, 14);
  frame.getContentPane().add(lblUsername);

  JLabel lblPassword = new JLabel("Password:");
  lblPassword.setBounds(36, 115, 77, 14);
  frame.getContentPane().add(lblPassword);

  JLabel lblRoles = new JLabel("Roles:");
  lblRoles.setBounds(36, 146, 61, 14);
  frame.getContentPane().add(lblRoles);

  JButton btnAdd = new JButton("Add");
  btnAdd.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    String sql = "INSERT INTO manager.login (id,user, pass, roles) VALUES (?,?,?,?)";
    try {
     stmt = conn.prepareStatement(sql);
     stmt.setInt(1, Integer.parseInt(txtID.getText()));
     stmt.setString(2, txtUsername.getText());
     stmt.setString(3, txtPassword.getText());
     stmt.setString(4, comboBox.getSelectedItem().toString());

     stmt.executeUpdate();

    } catch (Exception e) {
     // TODO: handle exception
    }
    showTable();
   }
  });
  btnAdd.setIcon(new ImageIcon("C:\\Users\\Lonely\\workspace\\Swing003\\icon\\add-icon.png"));
  btnAdd.setBounds(234, 53, 106, 23);
  frame.getContentPane().add(btnAdd);

  JButton btnUpdate = new JButton("Update");
  btnUpdate.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    String sql = "UPDATE manager.login SET user=?, pass=?, roles=? WHERE id =?";
    int id = Integer.parseInt(txtID.getText());
    try {
     stmt = conn.prepareStatement(sql);

     stmt.setString(1, txtUsername.getText());
     stmt.setString(2, txtPassword.getText());
     stmt.setString(3, comboBox.getSelectedItem().toString());
     stmt.setInt(4, id);

     stmt.executeUpdate();
    } catch (Exception e2) {
     // TODO: handle exception
    }
    showTable();
   }
  });
  btnUpdate.setIcon(new ImageIcon("C:\\Users\\Lonely\\workspace\\Swing003\\icon\\Actions-edit-redo-icon.png"));
  btnUpdate.setBounds(234, 84, 106, 23);
  frame.getContentPane().add(btnUpdate);

  JButton btnDelete = new JButton("Delete");
  btnDelete.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    String sql = "DELETE FROM manager.login WHERE id =?";
    int id = Integer.parseInt(txtID.getText());
    try {
     stmt = conn.prepareStatement(sql);
     stmt.setInt(1, id);
     stmt.executeUpdate();

    } catch (Exception e2) {
     // TODO: handle exception
    }
    showTable();

   }
  });
  btnDelete.setIcon(new ImageIcon("C:\\Users\\Lonely\\workspace\\Swing003\\icon\\Actions-edit-delete-icon.png"));
  btnDelete.setBounds(234, 111, 106, 23);
  frame.getContentPane().add(btnDelete);

  JScrollPane scrollPane = new JScrollPane();
  scrollPane.setBounds(20, 185, 346, 146);
  frame.getContentPane().add(scrollPane);

  table = new JTable();
  table.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked(MouseEvent arg0) {
    int row = table.getSelectedRow();
    if (row != -1) {
     txtID.setText(table.getValueAt(row, 0).toString());
     txtUsername.setText(table.getValueAt(row, 1).toString());
     txtPassword.setText(table.getValueAt(row, 2).toString());
     comboBox.setSelectedItem(table.getValueAt(row, 3).toString());
    }
   }
  });
  scrollPane.setViewportView(table);
  table.setModel(
    new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Username", "Password", "Roles" }));

  JLabel lblXinChao = new JLabel("Xin chao Admin");
  lblXinChao.setBounds(36, 11, 95, 14);
  frame.getContentPane().add(lblXinChao);

  JLabel lblManagerLogin = new JLabel("Manager Login");
  lblManagerLogin.setFont(new Font("MV Boli", Font.PLAIN, 16));
  lblManagerLogin.setBounds(216, 0, 124, 32);
  frame.getContentPane().add(lblManagerLogin);

  txtSearch = new JTextField();
  txtSearch.setBounds(116, 354, 144, 20);
  frame.getContentPane().add(txtSearch);
  txtSearch.setColumns(10);

  JComboBox comboBoxOption = new JComboBox();
  comboBoxOption.setModel(new DefaultComboBoxModel(new String[] { "Serch All", "Username", "UserType" }));
  comboBoxOption.setBounds(20, 353, 86, 20);
  frame.getContentPane().add(comboBoxOption);

  JButton btnSearch = new JButton("Search");
  btnSearch.setIcon(new ImageIcon("C:\\Users\\Lonely\\workspace\\Swing004\\icon\\Search-icon.png"));

  btnSearch.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    String Search = txtSearch.getText().trim();

    Vector cols = new Vector();
    cols.addElement("ID");
    cols.addElement("User");
    cols.addElement("Pass");
    cols.addElement("Roles");

    // Data
    Vector dataSearch = new Vector();
    String option = comboBoxOption.getSelectedItem().toString();
    switch (option) {
    case "Search All":
     try {
      String sql = "SELECT * FROM manager.login";
      stmt = conn.prepareStatement(sql);
      rs = stmt.executeQuery();
      while (rs.next()) {
       Vector user = new Vector();
       user.add(rs.getInt("id"));
       user.add(rs.getString("user"));
       user.add(rs.getString("pass"));
       user.add(rs.getString("roles"));

       if (user.toString().toLowerCase().contains(Search)) {
        dataSearch.addElement(user);
       }
      }
     } catch (Exception e2) {
      // TODO: handle exception
     }
     break;
    case "Username":
     try {
      String sqlUser = "SELECT * FROM manager.login WHERE user LIKE ?";
      stmt = conn.prepareStatement(sqlUser);
      stmt.setString(1, txtSearch.getText()+"%");
      rs = stmt.executeQuery();
      while (rs.next()) {
       Vector user = new Vector();
       user.add(rs.getInt("id"));
       user.add(rs.getString("user"));
       user.add(rs.getString("pass"));
       user.add(rs.getString("roles"));

       dataSearch.addElement(user);
      }
     } catch (Exception e2) {
      // TODO: handle exception
     }
     break;
    case "UserType":
     try {
      String sqlType = "SELECT * FROM manager.login WHERE roles LIKE ?";
      stmt = conn.prepareStatement(sqlType);
      stmt.setString(1, txtSearch.getText()+"%");
      rs = stmt.executeQuery();
      while (rs.next()) {
       Vector user = new Vector();
       user.add(rs.getInt("id"));
       user.add(rs.getString("user"));
       user.add(rs.getString("pass"));
       user.add(rs.getString("roles"));

       dataSearch.addElement(user);
      }
     } catch (Exception e2) {
      // TODO: handle exception
     }
     break;
    default:
     try {
      String sqlDef = "SELECT * FROM manager.login";
      stmt = conn.prepareStatement(sqlDef);
      rs = stmt.executeQuery();
      while (rs.next()) {
       Vector user = new Vector();
       user.add(rs.getInt("id"));
       user.add(rs.getString("user"));
       user.add(rs.getString("pass"));
       user.add(rs.getString("roles"));

       if (user.toString().toLowerCase().contains(Search)) {
        dataSearch.addElement(user);
       }
      }
     } catch (Exception e2) {
      // TODO: handle exception
     }
     break;
    }

    table.setModel(new DefaultTableModel(dataSearch, cols));
   }
  });
  btnSearch.setBounds(270, 354, 95, 20);
  frame.getContentPane().add(btnSearch);

  JButton btnNewButton = new JButton("About");
  btnNewButton
    .setIcon(new ImageIcon("C:\\Users\\Lonely\\workspace\\Swing004\\icon\\Actions-help-about-icon.png"));
  btnNewButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
   /* Note note = new Note();
    note.frame.setVisible(true);
    note.frame.setResizable(false);*/
   }
  });
  btnNewButton.setBounds(234, 142, 106, 23);
  frame.getContentPane().add(btnNewButton);

 }
}