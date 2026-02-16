package main;

import java.awt.*;
import javax.swing.*;

public class LoginRegisterGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtUsernameLogin, txtUsernameReg;
    private JPasswordField txtPasswordLogin, txtPasswordReg;
    private JTextField txtFirstName, txtLastName, txtAge;
    private JLabel lblLoginMessage, lblRegMessage;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginRegisterGUI frame = new LoginRegisterGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginRegisterGUI() {
    	setBackground(Color.GRAY);
        setTitle("Quiz App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        getContentPane().setBackground(Color.GRAY);
        getContentPane().setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.GRAY);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        /* ---------- Login Panel ---------- */
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.GRAY);
        loginPanel.setLayout(null);
        tabbedPane.addTab("Login", loginPanel);

        JLabel lblUsernameLogin = new JLabel("Username:");
        lblUsernameLogin.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUsernameLogin.setBounds(50, 30, 100, 25);
        loginPanel.add(lblUsernameLogin);

        txtUsernameLogin = new JTextField();
        txtUsernameLogin.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsernameLogin.setBounds(150, 30, 180, 25);
        loginPanel.add(txtUsernameLogin);

        JLabel lblPasswordLogin = new JLabel("Password:");
        lblPasswordLogin.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPasswordLogin.setBounds(50, 65, 100, 25);
        loginPanel.add(lblPasswordLogin);

        txtPasswordLogin = new JPasswordField();
        txtPasswordLogin.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPasswordLogin.setBounds(150, 65, 180, 25);
        loginPanel.add(txtPasswordLogin);

        lblLoginMessage = new JLabel("");
        lblLoginMessage.setFont(new Font("Arial", Font.PLAIN, 14));
        lblLoginMessage.setForeground(Color.RED);
        lblLoginMessage.setBounds(50, 100, 300, 25);
        loginPanel.add(lblLoginMessage);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 16));
        btnLogin.setBounds(150, 124, 100, 30);
        btnLogin.addActionListener(e -> performLogin());
        loginPanel.add(btnLogin);

        JLabel lblGoRegisterInfo = new JLabel("Not registered? Click to Register.");
        lblGoRegisterInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblGoRegisterInfo.setForeground(Color.ORANGE);
        lblGoRegisterInfo.setBounds(102, 180, 248, 25);
        
        lblGoRegisterInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedPane.setSelectedIndex(1); // Switch to Register tab
            }
        });
        
        loginPanel.add(lblGoRegisterInfo);

        /* ---------- Register Panel ---------- */
        JPanel registerPanel = new JPanel();
        registerPanel.setBackground(Color.GRAY);
        registerPanel.setLayout(null);
        tabbedPane.addTab("Register", registerPanel);

        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setFont(new Font("Arial", Font.PLAIN, 16));
        lblFirstName.setBounds(6, 5, 100, 25);
        registerPanel.add(lblFirstName);

        txtFirstName = new JTextField();
        txtFirstName.setFont(new Font("Arial", Font.PLAIN, 14));
        txtFirstName.setBounds(6, 42, 180, 25);
        registerPanel.add(txtFirstName);

        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setFont(new Font("Arial", Font.PLAIN, 16));
        lblLastName.setBounds(242, 5, 100, 25);
        registerPanel.add(lblLastName);

        txtLastName = new JTextField();
        txtLastName.setFont(new Font("Arial", Font.PLAIN, 14));
        txtLastName.setBounds(242, 42, 180, 25);
        registerPanel.add(txtLastName);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setFont(new Font("Arial", Font.PLAIN, 16));
        lblAge.setBounds(6, 79, 100, 25);
        registerPanel.add(lblAge);

        txtAge = new JTextField();
        txtAge.setFont(new Font("Arial", Font.PLAIN, 14));
        txtAge.setBounds(6, 116, 180, 25);
        registerPanel.add(txtAge);

        JLabel lblUsernameReg = new JLabel("Username:");
        lblUsernameReg.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUsernameReg.setBounds(242, 79, 100, 25);
        registerPanel.add(lblUsernameReg);

        txtUsernameReg = new JTextField();
        txtUsernameReg.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsernameReg.setBounds(242, 116, 180, 25);
        registerPanel.add(txtUsernameReg);

        JLabel lblPasswordReg = new JLabel("Password:");
        lblPasswordReg.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPasswordReg.setBounds(50, 160, 100, 25);
        registerPanel.add(lblPasswordReg);

        txtPasswordReg = new JPasswordField();
        txtPasswordReg.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPasswordReg.setBounds(150, 160, 180, 25);
        registerPanel.add(txtPasswordReg);

        lblRegMessage = new JLabel("");
        lblRegMessage.setFont(new Font("Arial", Font.PLAIN, 14));
        lblRegMessage.setForeground(Color.RED);
        lblRegMessage.setBounds(50, 190, 300, 25);
        registerPanel.add(lblRegMessage);

        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Arial", Font.PLAIN, 16));
        btnRegister.setBounds(160, 206, 100, 30);
        btnRegister.addActionListener(e -> performRegister());
        registerPanel.add(btnRegister);

        JLabel lblGoLoginInfo = new JLabel("Already registered? Click to Login.");
        lblGoLoginInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblGoLoginInfo.setForeground(Color.ORANGE);
        lblGoLoginInfo.setBounds(111, 248, 252, 25);
        
        lblGoLoginInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedPane.setSelectedIndex(0); // Switch to Login tab
            }
        });
        
        registerPanel.add(lblGoLoginInfo);
    }

    private void performLogin() {

        String username = txtUsernameLogin.getText();
        String password = new String(txtPasswordLogin.getPassword());

        int userID = UsersDBManager.login(username, password);

        if (userID == -1) {
            lblLoginMessage.setText("Invalid credentials!");
            return;
        }

        String role = UsersDBManager.getRole(userID);

        if (role.equals("admin")) {
            new AdminDashboardGUI().setVisible(true);
            dispose();
        } else {
            int competitorID = CompetitorDBManager.getCompetitorID(userID);

            System.out.println("UserID = " + userID);
            System.out.println("CompetitorID = " + competitorID);

            new PlayerDashboardGUI(userID, competitorID).setVisible(true);
            dispose();
        }
    }


    private void performRegister() {

    String username = txtUsernameReg.getText().trim();
    String password = new String(txtPasswordReg.getPassword()).trim();
    String firstName = txtFirstName.getText().trim();
    String lastName = txtLastName.getText().trim();
    String ageText = txtAge.getText().trim();

    if (username.isEmpty() || password.isEmpty() || firstName.isEmpty()
            || lastName.isEmpty() || ageText.isEmpty()) {

        lblRegMessage.setForeground(Color.RED);
        lblRegMessage.setText("Please fill in all fields!");
        return;
    }

    int age;

    try {
        age = Integer.parseInt(ageText);
        if (age <= 0) {
            lblRegMessage.setForeground(Color.RED);
            lblRegMessage.setText("Age must be positive!");
            return;
        }
    } catch (NumberFormatException e) {
        lblRegMessage.setForeground(Color.RED);
        lblRegMessage.setText("Age must be a number!");
        return;
    }

    int userID = UsersDBManager.registerPlayer(username, password);

    if (userID == -1) {
        lblRegMessage.setForeground(Color.RED);
        lblRegMessage.setText("Username already exists!");
        return;
    }

    // Create competitor immediately using returned userID
    CompetitorDBManager.addCompetitor(userID, firstName, lastName, age);

    txtUsernameReg.setText("");
    txtPasswordReg.setText("");
    txtFirstName.setText("");
    txtLastName.setText("");
    txtAge.setText("");

    lblRegMessage.setForeground(Color.GREEN);
    lblRegMessage.setText("Registration successful! You can login now.");
}

}
