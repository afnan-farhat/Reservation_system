package reservationsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientView {

    private static JLabel welcomeLabel;

    public static void main(String[] args) {
        createInputFrame();
    }

    private static void createInputFrame() {
        JFrame inputFrame = new JFrame("Log in");

        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputFrame.setSize(500, 200);
        inputFrame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JPanel inputPanel_ = new JPanel();

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JButton submitButton = new JButton("Submit");

        inputPanel.add(new JLabel("Enter Student ID:"));
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Enter Student Name:"));
        inputPanel.add(nameField);

        inputPanel_.add(submitButton);

        inputFrame.add(inputPanel, BorderLayout.CENTER);
        inputFrame.add(inputPanel_, BorderLayout.PAGE_END);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSubmission(inputFrame, idField.getText(), nameField.getText());
            }
        });

        inputFrame.setVisible(true);
    }

    private static void handleSubmission(JFrame inputFrame, String id, String name) {
        if (!id.isEmpty() && !name.isEmpty()) {
            welcomeLabel = new JLabel("Welcome, " + name + "!", SwingConstants.CENTER);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            inputFrame.dispose(); // Close the original frame
            createMenuView();
        } else {
            JOptionPane.showMessageDialog(inputFrame, "Please enter both ID and Name.", "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static void createMenuView() {
        JFrame MenuFrame = new JFrame("Menu");
        MenuFrame.setSize(500, 500);
        MenuFrame.setLayout(new BorderLayout());
        MenuFrame.add(welcomeLabel, BorderLayout.NORTH);

        JButton makeReservationButton = new JButton("Make a Reservation");
        JButton cancelReservationButton = new JButton("Cancel My Reservation");
        JButton allReservationsButton = new JButton("All My Reservations");

        JButton ContactUsButton = new JButton("Contact us");

        makeReservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuFrame.dispose();
                createTableView();
            }
        });
        cancelReservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cancel Method
                JOptionPane.showMessageDialog(MenuFrame, "cancelReservationButton");
            }
        });
        allReservationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MenuFrame, "allReservationsButton");
            }
        });
        ContactUsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MenuFrame, "Contact frame for Student");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 0));

        buttonPanel.add(makeReservationButton);
        buttonPanel.add(cancelReservationButton);
        buttonPanel.add(allReservationsButton);
        buttonPanel.add(ContactUsButton);

        MenuFrame.add(buttonPanel, BorderLayout.CENTER);

        JPanel panelContact = new JPanel();
        panelContact.add(ContactUsButton);
        MenuFrame.add(panelContact, BorderLayout.PAGE_END);

        MenuFrame.setVisible(true);
    }

    private static void createTableView() {
        JFrame tableframe = new JFrame("Golf Car System");
        tableframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tableframe.setSize(500, 500);

        String[] columnNames = {"Golf Car Number", "Trip Number", "Time", "Destination", "Available seats"};
        Object[][] data = {
            {"3", "5", "8 AM", "Gate1", "4"},
            {"3", "6", "9 AM", "Gate4", "9"},
            {"5", "7", "10 AM", "Gate7", "7"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        JButton button = new JButton("Reserve this Trip");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Seats Frame
                showSelectedRowData(table);
            }
        });

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableframe.dispose();
                createMenuView();
            }
        });

        JPanel panel = new JPanel();
        panel.add(backButton);
        panel.add(button);

        tableframe.add(scrollPane, BorderLayout.CENTER);
        tableframe.add(panel, BorderLayout.SOUTH);
        tableframe.setVisible(true);
    }

    private static void showSelectedRowData(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String GolfNo = (String) model.getValueAt(selectedRow, 0);
            String TripNum = (String) model.getValueAt(selectedRow, 1);
            String Time = (String) model.getValueAt(selectedRow, 2);

            JOptionPane.showMessageDialog(null,
                    "GolfNo: " + GolfNo + "\nTripNum: " + TripNum + "\nTime: " + Time,
                    "Selected Trip Data", JOptionPane.INFORMATION_MESSAGE);

            // Show seat selection frame
            createSeatSelectionFrame();
        } else {
            JOptionPane.showMessageDialog(table,
                    "Please select a row.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    //Seat with button 
    
    private static void createSeatSelectionFrame() {
        JFrame seatFrame = new JFrame("Select Your Seat");
        seatFrame.setSize(600, 400);
        seatFrame.setLayout(new GridLayout(5, 6)); // 5 rows and 6 seats per row (example layout)

//        // Simulated seat availability (true = available, false = unavailable)
//        boolean[][] seatsAvailability = {
//            {true, true, false, true, false, true},
//            {true, false, false, true, true, true},
//            {true, true, true, true, false, false},
//            {false, false, true, true, true, true},
//            {true, true, true, false, false, true}
//        };

        // Simulated all seats unavailability 
        boolean[][] seatsAvailability = {
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},};

        // Load the images for available and unavailable seats
        ImageIcon availableIcon = new ImageIcon("available_seat.jpg");
        ImageIcon unavailableIcon = new ImageIcon("unavailable_seat.jpg");

        // Create seat buttons based on availability
        for (int row = 0; row < seatsAvailability.length; row++) {
            for (int col = 0; col < seatsAvailability[row].length; col++) {
                JButton seatButton = new JButton("Seat " + (row + 1) + (char) ('A' + col));
                if (seatsAvailability[row][col]) {
                    seatButton.setIcon(availableIcon); // Set the available seat image
                    seatButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(seatFrame, "Booking seccessful\nYou reserved " + seatButton.getText());
                            seatFrame.dispose(); // Close seat selection frame after booking
                        }
                    });
                } else {
                    seatButton.setIcon(unavailableIcon); // Set the unavailable seat image
                    seatButton.setEnabled(false); // Disable unavailable seats
                }
                seatFrame.add(seatButton);
            }
        }

        seatFrame.setVisible(true);
    }
    
//    //Seats with images 
//    private static void createSeatSelectionFrame() {
//        JFrame seatFrame = new JFrame("Select Your Seat");
//        seatFrame.setSize(600, 400);
//        seatFrame.setLayout(new GridLayout(5, 6)); // 5 rows and 6 seats per row (example layout)
//
//        // Simulated seat availability (true = available, false = unavailable)
//        boolean[][] seatsAvailability = {
//            {true, true, false, true, false, true},
//            {true, false, false, true, true, true},
//            {true, true, true, true, false, false},
//            {false, false, true, true, true, true},
//            {true, true, true, false, false, true}
//        };
//
//        // Load the original images for available and unavailable seats
//        ImageIcon availableIcon = new ImageIcon("C:\\Users\\Afnan\\Documents\\NetBeansProjects\\CPIT305\\ReservationSystem\\src\\reservationsystem\\availabe.jpg");
//        ImageIcon unavailableIcon = new ImageIcon("C:\\Users\\Afnan\\Documents\\NetBeansProjects\\CPIT305\\ReservationSystem\\src\\reservationsystem\\unavailabe.jpg");
//
//        // Desired size for the buttons and icons
//        int buttonWidth = 100; // Set your desired width
//        int buttonHeight = 100; // Set your desired height
//
//        // Scale the images to the desired size
//        Image scaledAvailableImage = availableIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
//        Image scaledUnavailableImage = unavailableIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
//
//        // Create new ImageIcons with the scaled images
//        availableIcon = new ImageIcon(scaledAvailableImage);
//        unavailableIcon = new ImageIcon(scaledUnavailableImage);
//
//        // Create seat buttons based on availability
//        for (int row = 0; row < seatsAvailability.length; row++) {
//            for (int col = 0; col < seatsAvailability[row].length; col++) {
//                JButton seatButton = new JButton("Seat " + (row + 1) + (char) ('A' + col));
//                if (seatsAvailability[row][col]) {
//                    seatButton.setIcon(availableIcon); // Set the available seat image
//                    seatButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight)); // Set button size
//                    seatButton.addActionListener(new ActionListener() {
//                        public void actionPerformed(ActionEvent e) {
//                            JOptionPane.showMessageDialog(seatFrame, "Booking successful\nYou reserved " + seatButton.getText());
//                            seatFrame.dispose(); // Close seat selection frame after booking
//                        }
//                    });
//                } else {
//                    seatButton.setIcon(unavailableIcon); // Set the unavailable seat image
//                    seatButton.setEnabled(false); // Disable unavailable seats
//                }
//                seatFrame.add(seatButton);
//            }
//        }
//
//        seatFrame.setVisible(true);
//    }

}
