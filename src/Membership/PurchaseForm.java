package Membership;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
public class PurchaseForm extends javax.swing.JFrame {
    private javax.swing.JFormattedTextField cvvField, yearField, numField;
    private javax.swing.JLabel cvvLbl, errorLabel, jLabel1, jLabel2, logoImage, mapleLabel1, monLbl, numLbl, title, yrLbl;
    private javax.swing.JPanel leftPane;
    private javax.swing.JComboBox<String> monthBox;
    private static javax.swing.JButton orderBtn;

    public PurchaseForm() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {

        orderBtn = new javax.swing.JButton();
        numLbl = new javax.swing.JLabel();
        cvvLbl = new javax.swing.JLabel();
        leftPane = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();
        monLbl = new javax.swing.JLabel();
        yrLbl = new javax.swing.JLabel();
        monthBox = new javax.swing.JComboBox<>();

        try {
            MaskFormatter formatter = new MaskFormatter("****************");
            formatter.setValidCharacters("0123456789");
            numField = new javax.swing.JFormattedTextField(formatter);
            numField.setText("5590490221847724");
            numField.setEditable(false);

            try {
                MaskFormatter formatter2 = new MaskFormatter("****");
                formatter2.setValidCharacters("0123456789");
                yearField = new javax.swing.JFormattedTextField(formatter2);
                yearField.setText("2028");
                yearField.setEditable(false);
                try {
                    MaskFormatter formatter3 = new MaskFormatter("***");
                    formatter3.setValidCharacters("0123456789");
                    cvvField = new javax.swing.JFormattedTextField(formatter3);
                    cvvField.setText("308");
                    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                    setTitle("E-Qura'an Application ");
                    setBounds(new java.awt.Rectangle(400, 10, 600, 700));

                    setMinimumSize(new java.awt.Dimension(600, 400));
                    getContentPane().setLayout(null);

                    orderBtn.setBackground(new java.awt.Color(255, 102, 102));
                    orderBtn.setFont(new java.awt.Font("Montserrat SemiBold", 0, 14)); // NOI18N
                    orderBtn.setForeground(new java.awt.Color(255, 255, 255));
                    orderBtn.setText("Place Order");
                    orderBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            orderBtnActionPerformed(evt);
                        }
                    });
                    getContentPane().add(orderBtn);
                    orderBtn.setBounds(200, 260, 150, 36);

                    numLbl.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
                    numLbl.setText("Card Number");
                    getContentPane().add(numLbl);
                    numLbl.setBounds(50, 80, 90, 15);

                    cvvLbl.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
                    cvvLbl.setText("CVV");
                    getContentPane().add(cvvLbl);
                    cvvLbl.setBounds(50, 200, 30, 15);


                    // NOI18N
                    title.setText("Payment Details");
                    getContentPane().add(title);
                    Font obj= title.getFont();
                    Font newobj=obj.deriveFont(42.0f);
                    title.setFont(newobj);
                    title.setBounds(50, 10, 400, 50);

                    errorLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    errorLabel.setForeground(new java.awt.Color(255, 51, 51));
                    getContentPane().add(errorLabel);
                    errorLabel.setBounds(250, 310, 300, 20);
                    errorLabel.setVisible(false);

                    monLbl.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
                    monLbl.setText("Expiry Month");
                    getContentPane().add(monLbl);
                    monLbl.setBounds(50, 140, 90, 15);

                    yrLbl.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
                    yrLbl.setText("Expiry Year");
                    getContentPane().add(yrLbl);
                    yrLbl.setBounds(160, 140, 90, 15);

                    monthBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
                    monthBox.setEditable(false);
                    monthBox.setSelectedItem("11");
                    getContentPane().add(monthBox);
                    monthBox.setBounds(50, 162, 72, 30);

                } catch (Exception e) {
                    System.out.println(e);
                }
                getContentPane().add(numField);
                numField.setBounds(50, 100, 150, 30);

            } catch (Exception e) {
                System.out.println(e);
            }
            getContentPane().add(yearField);
            yearField.setBounds(160, 160, 70, 30);

        } catch (Exception e) {
            System.out.println(e);
        }
        getContentPane().add(cvvField);
        cvvField.setBounds(50, 220, 70, 30);

        pack();
        setVisible(true);
    }

    private void orderBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String cc = numField.getText();
        String month = monthBox.getSelectedItem().toString();
        String year = yearField.getText();
        String cvv = cvvField.getText();

        // Perform payment verification
        String result = Payment.verifyPayment(cc, month, year, cvv);


        if (result.equals("success")) {
            errorLabel.setText("Payment Successful, You can close this window now!");
            errorLabel.setForeground(Color.GREEN);
            errorLabel.setVisible(true);
        } else {
            errorLabel.setText(result);
            errorLabel.setVisible(true);
        }
    }
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(() -> {
//            new PurchaseForm().setVisible(true);
//        });
//    }
}

