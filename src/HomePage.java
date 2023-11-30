import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;

public class HomePage extends JFrame implements ActionListener {
    private static Connection connection;
    private Read rd;
    private Listen ls;
    private JMenuBar bar;
    private JMenu menu, it1, it2;
    private JMenuItem it3;
    private JMenuItem startReading, continueReading, startListening, continueListening, openSpecificAyahRuku;
    private JButton exit;
    private JPanel footer;

    //---------------------------------------------------------------------------------------------------------
    public void runApplication(Connection conn) throws SQLException {
        connection = conn;
        rd = new Read(connection);
        ls = new Listen(connection);

        //Menu
        bar = new JMenuBar();
        menu = new JMenu("☰");
        Font menuFont = bar.getFont();
        Font newMenuFont = menuFont.deriveFont(30.0f);
        menu.setFont(newMenuFont);

        it1 = new JMenu("Read Qura'an");
        it2 = new JMenu("Listen Recitation");
        it3 = new JMenuItem("Support this App");

        startReading = new JMenuItem("Start Reading");
        continueReading = new JMenuItem("Continue Reading");
        startListening = new JMenuItem("Start Listening");
        continueListening = new JMenuItem("Continue Listening");
        openSpecificAyahRuku = new JMenuItem("Open Specific Ayah/Ruku");

        startReading.addActionListener(this);
        continueReading.addActionListener(this);
        openSpecificAyahRuku.addActionListener(this);
        startListening.addActionListener(this);
        continueListening.addActionListener(this);
        it3.addActionListener(this);

        it1.add(startReading);
        it1.add(continueReading);
        it1.add(openSpecificAyahRuku);
        it2.add(startListening);
        it2.add(continueListening);

        Font menuItemsFont1 = it1.getFont();
        Font newMenuItemsFont1 = menuItemsFont1.deriveFont(18.0f);
        it1.setFont(newMenuItemsFont1);
        it2.setFont(newMenuItemsFont1);
        it3.setFont(newMenuItemsFont1);

        Font menuItemsFont2 = startReading.getFont();
        Font newMenuItemsFont2 = menuItemsFont2.deriveFont(14.0f);
        startReading.setFont(newMenuItemsFont2);
        continueReading.setFont(newMenuItemsFont2);
        openSpecificAyahRuku.setFont(newMenuItemsFont2);
        startListening.setFont(newMenuItemsFont2);
        continueListening.setFont(newMenuItemsFont2);

        menu.add(it1);
        menu.add(it2);
        menu.add(it3);

        bar.add(menu);
        bar.setSize(500, 500);
        bar.setPreferredSize(new Dimension(100, 40));

        //Header
        JLabel heading = new JLabel("\"Welcome to E-Qura'an Application\"");
        Font labelFont1 = heading.getFont();
        Font newLabelFont1 = labelFont1.deriveFont(24.0f);
        heading.setFont(newLabelFont1);
        Font fontStyle = new Font(heading.getFont().getFamily(), Font.ITALIC | Font.BOLD, heading.getFont().getSize());
        heading.setFont(fontStyle);

        JPanel header = new JPanel(new FlowLayout());
        header.add(heading);

        //Body
        BackgroundPanel backgroundPanel = new BackgroundPanel("D:\\FAST-NUCES l215845\\5th Semester\\Software Construction & Development\\Project\\Extras\\Pictures\\CoverPicture.png");

        //Footer
        exit = new JButton("Exit");
        exit.addActionListener(this);
        exit.setFont(newMenuItemsFont1);

        footer = new JPanel(new FlowLayout());
        footer.add(exit);

        //Frame
        setLayout(new BorderLayout());
        setJMenuBar(bar);
        add(header, BorderLayout.NORTH);
        add(backgroundPanel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        setTitle("E-Qura'an Application");
        setBounds(400,10,600,700);
        addWindowListener(new MyWindowListener());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    //---------------------------------------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Support this App":
                Support sp = new Support();
                break;
            case "Start Reading":
                rd.startReading();
                break;
            case "Continue Reading":
                try {
                    rd.continueReading();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Start Listening":
                ls.startListening();
                break;
            case "Continue Listening":
                try {
                    ls.continueListening();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Open Specific Ayah/Ruku":
                OpenSpecificAyahRuku obj = new OpenSpecificAyahRuku(connection);
                break;
            case "Exit":
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm Close", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
        }
    }

    //---------------------------------------------------------------------------------------------------------
    class MyWindowListener implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm Close", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
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
