import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HomePage extends JFrame implements ActionListener {
    private JTextField nameField, mobileField, locationFromField, locationToField, distanceField;
    private JComboBox<String> carSelectionComboBox;
    private JButton bookButton, resetButton, logoutButton;
    private JSpinner dateTimeSpinner;

    private ImageIcon[] carImages; // Array to hold car images

    public HomePage() {
        setTitle("Car Booking Form");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load car images
        carImages = new ImageIcon[]{
            new ImageIcon("taxiservide.jpg"),
            new ImageIcon("images.jpeg"),
            new ImageIcon("download (4).jpeg"),
            new ImageIcon("z4-exterior-right-front-three-quarter-2.jpg"),
            new ImageIcon("download (1).jpeg"),
            new ImageIcon("download (2).jpeg"),
            new ImageIcon("download (3).jpeg")
        };

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 10, 10));
        panel.setBackground(new Color(255, 218, 185)); // Set background color

        // Components initialization

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel carLabel = new JLabel("Car Selection:");
        String[] carOptions = {"Select","Fortuner", "Bugatti", "BMW", "Audi", "Mercedes", "Tesla"};
        carSelectionComboBox = new JComboBox<>(carOptions);
        carSelectionComboBox.addActionListener(this);

        JLabel dateTimeLabel = new JLabel("Date and Time:");
        SpinnerDateModel dateTimeModel = new SpinnerDateModel();
        dateTimeSpinner = new JSpinner(dateTimeModel);
        JSpinner.DateEditor dateTimeEditor = new JSpinner.DateEditor(dateTimeSpinner, "dd/MM/yyyy HH:mm");
        dateTimeSpinner.setEditor(dateTimeEditor);

        JLabel mobileLabel = new JLabel("Mobile Number:");
        mobileField = new JTextField();

        JLabel locationFromLabel = new JLabel("From:");
        locationFromField = new JTextField();

        JLabel locationToLabel = new JLabel("To:");
        locationToField = new JTextField();

        JLabel distanceLabel = new JLabel("Distance:");
        distanceField = new JTextField();
        distanceField.setEditable(true);

        bookButton = new JButton("Book");
        bookButton.addActionListener(this);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(carLabel);
        panel.add(carSelectionComboBox);
        panel.add(dateTimeLabel);
        panel.add(dateTimeSpinner);
        panel.add(mobileLabel);
        panel.add(mobileField);
        panel.add(locationFromLabel);
        panel.add(locationFromField);
        panel.add(locationToLabel);
        panel.add(locationToField);
        panel.add(distanceLabel);
        panel.add(distanceField);
        panel.add(bookButton);
        panel.add(resetButton);
        panel.add(logoutButton);

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookButton) {
            // Handle booking action
            String name = nameField.getText();
            String carSelection = (String) carSelectionComboBox.getSelectedItem();
            String dateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(dateTimeSpinner.getValue());
            String mobile = mobileField.getText();
            String locationFrom = locationFromField.getText();
            String locationTo = locationToField.getText();
            String distanceText = distanceField.getText();
            double distance = Double.parseDouble(distanceText);

            double price = distance * 10;

            displayBookingDetails(name, carSelection, dateTime, mobile, locationFrom, locationTo, distance, price);

            saveBookingDetailsToFile(name, carSelection, dateTime, mobile, locationFrom, locationTo, distance, price);
        } else if (e.getSource() == resetButton) {
            // Handle reset action
            nameField.setText("");
            carSelectionComboBox.setSelectedIndex(0);
            ((JSpinner.DefaultEditor) dateTimeSpinner.getEditor()).getTextField().setText("");
            mobileField.setText("");
            locationFromField.setText("");
            locationToField.setText("");
            distanceField.setText("");
        } else if (e.getSource() == logoutButton) {
            // Handle logout action
            dispose();
            new LoginPage();
        } else if (e.getSource() == carSelectionComboBox) {
            // Display selected car image
            int index = carSelectionComboBox.getSelectedIndex();
            JOptionPane.showMessageDialog(this, carImages[index], "Car Image", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private JFrame detailsFrame;

    private void displayBookingDetails(String name, String carSelection, String dateTime, String mobile, String locationFrom, String locationTo, double distance, double price) {
        detailsFrame = new JFrame("Booking Details");
        detailsFrame.setSize(400, 300);
        detailsFrame.setLayout(new BorderLayout());
    
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(9, 2, 10, 5));
    
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font valueFont = new Font("Arial", Font.PLAIN, 14);
    
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        JLabel carLabel = new JLabel("Car Selection:");
        carLabel.setFont(labelFont);
        JLabel dateTimeLabel = new JLabel("Date and Time:");
        dateTimeLabel.setFont(labelFont);
        JLabel mobileLabel = new JLabel("Mobile Number:");
        mobileLabel.setFont(labelFont);
        JLabel locationFromLabel = new JLabel("From:");
        locationFromLabel.setFont(labelFont);
        JLabel locationToLabel = new JLabel("To:");
        locationToLabel.setFont(labelFont);
        JLabel distanceLabel = new JLabel("Distance (km):");
        distanceLabel.setFont(labelFont);
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(labelFont);
    
        JLabel nameValueLabel = new JLabel(name);
        nameValueLabel.setFont(valueFont);
        JLabel carValueLabel = new JLabel(carSelection);
        carValueLabel.setFont(valueFont);
        JLabel dateTimeValueLabel = new JLabel(dateTime);
        dateTimeValueLabel.setFont(valueFont);
        JLabel mobileValueLabel = new JLabel(mobile);
        mobileValueLabel.setFont(valueFont);
        JLabel locationFromValueLabel = new JLabel(locationFrom);
        locationFromValueLabel.setFont(valueFont);
        JLabel locationToValueLabel = new JLabel(locationTo);
        locationToValueLabel.setFont(valueFont);
        JTextField distanceValueField = new JTextField(String.valueOf(distance));
        distanceValueField.setEditable(false);
        distanceValueField.setFont(valueFont);
        JLabel priceValueLabel = new JLabel("Rs " + price);
        priceValueLabel.setFont(valueFont);
    
        detailsPanel.add(nameLabel);
        detailsPanel.add(nameValueLabel);
        detailsPanel.add(carLabel);
        detailsPanel.add(carValueLabel);
        detailsPanel.add(dateTimeLabel);
        detailsPanel.add(dateTimeValueLabel);
        detailsPanel.add(mobileLabel);
        detailsPanel.add(mobileValueLabel);
        detailsPanel.add(locationFromLabel);
        detailsPanel.add(locationFromValueLabel);
        detailsPanel.add(locationToLabel);
        detailsPanel.add(locationToValueLabel);
        detailsPanel.add(distanceLabel);
        detailsPanel.add(distanceValueField);
        detailsPanel.add(priceLabel);
        detailsPanel.add(priceValueLabel);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton cancelButton = new JButton("Cancel Booking");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(detailsFrame, "Are you sure you want to cancel the booking?", "Cancel Booking", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(detailsFrame, "Booking cancelled.", "Cancellation", JOptionPane.INFORMATION_MESSAGE);
                    detailsFrame.dispose();
                }
            }
        });
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                detailsFrame.dispose();
            }
        });
    
        buttonPanel.add(cancelButton);
        buttonPanel.add(homeButton);
    
        detailsFrame.add(detailsPanel, BorderLayout.CENTER);
        detailsFrame.add(buttonPanel, BorderLayout.SOUTH);
        detailsFrame.setLocationRelativeTo(null);
        detailsFrame.setVisible(true);
    }

    private void saveBookingDetailsToFile(String name, String carSelection, String dateTime, String mobile,
                                          String locationFrom, String locationTo, double distance, double price) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("booking_details.txt", true));
            writer.write("Name: " + name + "\n");
            writer.write("Car Selection: " + carSelection + "\n");
            writer.write("Date and Time: " + dateTime + "\n");
            writer.write("Mobile Number: " + mobile + "\n");
            writer.write("From: " + locationFrom + "\n");
            writer.write("To: " + locationTo + "\n");
            writer.write("Distance: " + distance + " km\n");
            writer.write("Price: Rs " + price + "\n");
            writer.write("-----------------------------------------\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomePage();
            }
        });
    }
}
