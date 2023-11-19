import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ValidatedDateInputExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Validated Date Input Example");
            frame.setSize(400, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            JLabel instructionLabel = new JLabel("Enter Date (dd-MM-yyyy): ");
            JTextField dateTextField = new JTextField(10);
            JButton submitButton = new JButton("Submit");

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputDate = dateTextField.getText();

                    if (isValidDate(inputDate)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                        try {
                            Date selectedDate = dateFormat.parse(inputDate);
                            JOptionPane.showMessageDialog(frame, "Selected Date: " + dateFormat.format(selectedDate));
                            // You can perform further actions with the selected date here

                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(frame, "Error parsing date.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid date. Please enter a valid date.");
                    }
                }
            });

            panel.add(instructionLabel);
            panel.add(dateTextField);
            panel.add(submitButton);

            frame.getContentPane().add(panel);
            frame.setVisible(true);
        });
    }

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
}