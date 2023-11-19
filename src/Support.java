import jdk.jshell.execution.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Support extends JFrame implements ActionListener {
    private JPanel header;
    private JPanel body;
    private JLabel heading;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JLabel l5;
    private JTextField holderName;
    private JTextField crdNumber;
    private JTextField expDate;
    private JTextField cvv;
    private JTextField amount;
    private JButton confirmation;

    //---------------------------------------------------------------------------------------------------------
    public Support() {
        //Header
        heading = new JLabel("Payment Details");
        Font headingFont = heading.getFont();
        Font newHeadingFont = headingFont.deriveFont(18.0f);
        heading.setFont(newHeadingFont);

        header = new JPanel(new FlowLayout());
        header.add(heading);

        //Body
        l1 = new JLabel("Card Holder's Name: ");
        l2 = new JLabel("Credit Card Number: ");
        l3 = new JLabel("Expiry Date (dd-mm-yyyy) : ");
        l4 = new JLabel("CVV: ");
        l5 = new JLabel("Amount: ");

        holderName = new JTextField(20);
        crdNumber = new JTextField(16);
        expDate = new JTextField(10);
        cvv = new JTextField(3);
        amount = new JTextField(10);

        confirmation = new JButton("Confirm Payment");
        confirmation.addActionListener(this);

        JPanel p1 = new JPanel(new FlowLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());
        JPanel p4 = new JPanel(new FlowLayout());
        JPanel p5 = new JPanel(new FlowLayout());
        JPanel p6 = new JPanel(new FlowLayout());
        JPanel p7 = new JPanel(new FlowLayout());

        p1.add(l1);
        p1.add(holderName);
        p2.add(l2);
        p2.add(crdNumber);
        p3.add(l3);
        p3.add(expDate);
        p2.add(l4);
        p2.add(cvv);
        p3.add(l5);
        p3.add(amount);
        p6.add(confirmation);

        body = new JPanel();
        body.setLayout(new GridLayout(7, 1));
        body.add(p1);
        body.add(p4);
        body.add(p2);
        body.add(p5);
        body.add(p3);
        body.add(p7);
        body.add(p6);

        //Frame
        setLayout(new GridLayout(3, 1));
        add(header);
        add(body);
        setTitle("E-Qura'an Application");
        setSize(600, 700);
        addWindowListener(new MyWindowListener());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    //---------------------------------------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        String inputDate = expDate.getText();

        if (isValidDate(inputDate)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            try {
                Date selectedDate = dateFormat.parse(inputDate);
                JOptionPane.showMessageDialog(this, "Selected Date: " + dateFormat.format(selectedDate));
                // You can perform further actions with the selected date here

            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Error parsing date.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid date. Please enter a valid date.");
        }
    }

    //---------------------------------------------------------------------------------------------------------
    private static boolean isValidDate(String inputDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false); // Disable lenient mode for strict date checking

        try {
            Date date = dateFormat.parse(inputDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            int day = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH) + 1; // Month is 0-based
            int year = cal.get(Calendar.YEAR);

            // Perform additional checks based on your requirements
            return isValidDayOfMonth(day, month, year) && isValidMonth(month) && isValidYear(year);
        } catch (ParseException e) {
            return false;
        }
    }

    private static boolean isValidDayOfMonth(int day, int month, int year) {
        // Check if day is within the valid range for the given month and year
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return day >= 1 && day <= maxDay;
    }

    private static boolean isValidMonth(int month) {
        return month >= 1 && month <= 12;
    }

    private static boolean isValidYear(int year) {
        // Perform additional checks if needed
        return true;
    }

    //---------------------------------------------------------------------------------------------------------
    class MyWindowListener implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
//            int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm Close", JOptionPane.YES_NO_OPTION);
//            if (choice == JOptionPane.YES_OPTION) {
//                System.exit(0);
//            }
            dispose();
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    }
    //---------------------------------------------------------------------------------------------------------
}
