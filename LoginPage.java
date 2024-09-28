import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, resetButton;
    private JLabel carBookingLabel;

    public LoginPage() {
        setTitle("Car Booking Application");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon("download.jpeg").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.setBackground(Color.WHITE); 

        carBookingLabel = new JLabel("Car Booking Application");
        carBookingLabel.setForeground(Color.BLACK); 
        carBookingLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH; 
        gbc.insets = new Insets(20, 20, 20, 20); 
        backgroundPanel.add(carBookingLabel, gbc);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2, 10, 10));
        loginPanel.setBackground(new Color(255, 255, 255, 200)); 

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.BLACK); 
        usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.BLACK); 
        passwordField = new JPasswordField(15);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButton.setBackground(Color.decode("#4CAF50")); 
        loginButton.setForeground(Color.WHITE); 

        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        resetButton.setBackground(Color.decode("#FF5722")); 
        resetButton.setForeground(Color.WHITE);

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(resetButton);

        add(backgroundPanel, BorderLayout.CENTER);
        add(loginPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (!username.isEmpty() && !password.isEmpty()) {
                new HomePage();
                dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Authentication Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == resetButton) {
            usernameField.setText("");
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginPage();
            }
        });
    }
}

